package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Turista;
import models.Uloga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TuristiForm extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Turista> turisti;
    private JTable turistiTable;
    private JButton editButton;
    private JButton deleteButton;

    public TuristiForm(List<Turista> turisti) {
        this.turisti = turisti;

        setTitle("Turisti");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Ime");
        tableModel.addColumn("Prezime");
        tableModel.addColumn("JMBG");
        tableModel.addColumn("Adresa");
        tableModel.addColumn("Broj telefona");

        // Populate table with data from the turisti list
        for (Turista turista : turisti) {
            Object[] rowData = {
                    turista.getId(),
                    turista.getIme(),
                    turista.getPrezime(),
                    turista.getJMBG(),
                    turista.getAdresa(),
                    turista.getBrojTelefona()
            };
            tableModel.addRow(rowData);
        }

        // Create table
        turistiTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(turistiTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create buttons for edit and delete operations
        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Izmeni");
        editButton.setEnabled(false);
        deleteButton = new JButton("Obriši");
        deleteButton.setEnabled(false);

        add(panel);
    

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = turistiTable.getSelectedRow();
                if (selectedRow != -1) {
                    Turista selectedTurista = getSelectedTurista(selectedRow);
                    if (selectedTurista != null) {
                        // Open edit window for the selected turista
                        IzmeniTuristuProzor editWindow = new IzmeniTuristuProzor(selectedTurista);
                        editWindow.setVisible(true);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = turistiTable.getSelectedRow();
                if (selectedRow != -1) {
                    int choice = JOptionPane.showConfirmDialog(TuristiForm.this,
                            "Da li ste sigurni da želite da obrišete turistu?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        Turista selectedTurista = getSelectedTurista(selectedRow);
                        if (selectedTurista != null) {
                            // Remove the selected turista from the list
                            turisti.remove(selectedTurista);
                            tableModel.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(TuristiForm.this, "Turista je uspešno obrisan.");
                            
                            // Update the file
                            sacuvajIzmene();
                        }
                    }
                }
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add list selection listener to enable/disable buttons based on row selection
        turistiTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isRowSelected = turistiTable.getSelectedRow() != -1;
                editButton.setEnabled(isRowSelected);
                deleteButton.setEnabled(isRowSelected);
            }
        });

        add(panel);
    }

    private Turista getSelectedTurista(int selectedRow) {
        int turistaId = (int) turistiTable.getValueAt(selectedRow, 0);
        for (Turista turista : turisti) {
            if (turista.getId() == turistaId) {
                return turista;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Load turisti from file
        String putanjaDoDatoteke = "src/data/korisnik.txt";
        List<Turista> turisti = citajTuristeIzDatoteke(putanjaDoDatoteke);

        // Create and display the form
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TuristiForm(turisti).setVisible(true);
            }
        });
    }
    
    public static List<Turista> citajTuristeIzDatoteke(String putanjaDoDatoteke) {
        List<Turista> turisti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDoDatoteke))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 9 && parts[8].equals("TURISTA")) {
                    int id = Integer.parseInt(parts[0]);
                    String ime = parts[1];
                    String prezime = parts[2];
                    String jmbg = parts[3];
                    String adresa = parts[4];
                    String brojTelefona = parts[5];
                    String korisnickoIme = parts[6];
                    String lozinka = parts[7];
                    Uloga uloga = Uloga.valueOf(parts[8]);

                    Turista turista = new Turista(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
                    turisti.add(turista);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return turisti;
    }
    
    private void sacuvajIzmene() {
        try (FileWriter writer = new FileWriter("src/data/korisnik.txt", false)) {
            for (Turista turista : turisti) {
                String linija = turista.getId() + "|" + turista.getIme() + "|" + turista.getPrezime() + "|" +
                        turista.getJMBG() + "|" + turista.getAdresa() + "|" + turista.getBrojTelefona() + "|" +
                        turista.getKorisnickoIme() + "|" + turista.getLozinka() + "|" + turista.getUloga().toString() + "\n";
                writer.write(linija);
            }
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
