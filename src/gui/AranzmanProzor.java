package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Aranzman;

public class AranzmanProzor extends JFrame {
    private JTable arrangementTable;
    private JButton editButton;
    private JButton deleteButton;

    private List<Aranzman> arrangements;

    public AranzmanProzor(List<Aranzman> arrangements) {
        this.arrangements = arrangements;

        setTitle("Aranžmani");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Kreiranje modela tabele
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tip aranžmana");
        tableModel.addColumn("Dostupan datum");
        tableModel.addColumn("Broj noćenja");
        tableModel.addColumn("Kapacitet");
        tableModel.addColumn("Tip smeštaja");
        tableModel.addColumn("Cena po osobi");

        // Popunjavanje podacima iz liste aranžmana
        for (Aranzman arrangement : arrangements) {
            Object[] rowData = {
                    arrangement.getId(),
                    arrangement.getTipAranzmana(),
                    arrangement.getDatumPolaska(),
                    arrangement.getBrojNocenja(),
                    arrangement.getKapacitet(),
                    arrangement.getTipSmestaja(),
                    arrangement.getCenaPoOsobi()
            };
            tableModel.addRow(rowData);
        }

        // Kreiranje tabele
        arrangementTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(arrangementTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Kreiranje dugmadi za izmenu i brisanje
        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Izmeni");
        editButton.setEnabled(false);
        deleteButton = new JButton("Obriši");
        deleteButton.setEnabled(false);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = arrangementTable.getSelectedRow();
                if (selectedRow != -1) {
                    Aranzman selectedArrangement = arrangements.get(selectedRow);
                    // Otvaranje prozora za izmenu aranžmana i prosleđivanje selektovanog aranžmana
                    IzmeniAranzmanProzor editWindow = new IzmeniAranzmanProzor(arrangements, selectedArrangement);
                    editWindow.setVisible(true);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = arrangementTable.getSelectedRow();
                if (selectedRow != -1) {
                    int choice = JOptionPane.showConfirmDialog(AranzmanProzor.this, "Da li ste sigurni da želite da obrišete aranžman?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        Aranzman selectedArrangement = arrangements.get(selectedRow);
                        // Brisanje selektovanog aranžmana
                        arrangements.remove(selectedArrangement);
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(AranzmanProzor.this, "Aranžman je uspešno obrisan.");
                    }
                }
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Postavljanje slušača selekcije reda u tabeli
        arrangementTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = arrangementTable.getSelectedRow();
                editButton.setEnabled(selectedRow != -1);
                deleteButton.setEnabled(selectedRow != -1);
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        // Učitavanje aranžmana iz datoteke
        String putanjaDoDatoteke = "src/data/aranzmani.txt";
        List<Aranzman> aranzmani = Aranzman.ucitajAranzmaneIzDatoteke(putanjaDoDatoteke);

        // Kreiranje prozora i prikazivanje
        AranzmanProzor prozor = new AranzmanProzor(aranzmani);
        prozor.setVisible(true);
    }
}
