package gui;

import models.TuristickiAgent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TuristickiAgentiForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<TuristickiAgent> listaTuristickihAgenata;
    private JTable tabelaTuristickihAgenata;
    private DefaultTableModel tableModel;

    public TuristickiAgentiForm(List<TuristickiAgent> listaTuristickihAgenata) {
        this.listaTuristickihAgenata = listaTuristickihAgenata;

        setTitle("Turistički agenti");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        populateTable();
    }

    private void initComponents() {
        tabelaTuristickihAgenata = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaTuristickihAgenata);
        add(scrollPane, BorderLayout.CENTER);

        // Define column names for the table
        String[] columnNames = {
                "ID",
                "Ime",
                "Prezime",
                "Email",
                "Broj Telefona"
                // Add names for other columns as needed
        };

        // Create the table model
        tableModel = new DefaultTableModel(columnNames, 0);
        tabelaTuristickihAgenata.setModel(tableModel);

        JButton sacuvajButton = new JButton("Sačuvaj");
        sacuvajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajTuristickeAgenteUDatoteku("src/data/korisnik.txt");
            }
        });

        JButton izmeniButton = new JButton("Izmeni");
        izmeniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaTuristickihAgenata.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the selected TuristickiAgent based on the selected row
                    TuristickiAgent agent = listaTuristickihAgenata.get(selectedRow);

                    // Open the edit window for the TuristickiAgent
                    IzmeniTurisickogProzor izmeniTurisickogProzor = new IzmeniTurisickogProzor(agent);
                    izmeniTurisickogProzor.setVisible(true);
                }
            }
        });


        JButton obrisiButton = new JButton("Obriši");
        obrisiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaTuristickihAgenata.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the TuristickiAgent based on the selected row
                    TuristickiAgent agent = listaTuristickihAgenata.get(selectedRow);

                    // Remove the TuristickiAgent from the list
                    listaTuristickihAgenata.remove(agent);

                    // Remove the row from the table
                    tableModel.removeRow(selectedRow);

                    // Save the updated list of TuristickiAgent to the file
                    sacuvajTuristickeAgenteUDatoteku("src/data/korisnik.txt");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sacuvajButton);
        buttonPanel.add(izmeniButton);
        buttonPanel.add(obrisiButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

	private void populateTable() {
        for (TuristickiAgent agent : listaTuristickihAgenata) {
                Object[] rowData = {
                        agent.getId(),
                        agent.getIme(),
                        agent.getPrezime(),
                        agent.getAdresa(),
                        agent.getBrojTelefona(),
                };
                tableModel.addRow(rowData);
            }
        }
    

    private void sacuvajTuristickeAgenteUDatoteku(String putanja) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(putanja))) {
            for (TuristickiAgent agent : listaTuristickihAgenata) {
                writer.println(agent.getId() + "|" +
                        agent.getIme() + "|" +
                        agent.getPrezime() + "|" +
                        agent.getAdresa() + "|" +
                        agent.getBrojTelefona() + "|" +
                        agent.getKorisnickoIme() + "|" +
                        agent.getLozinka() + "|" +
                        agent.getUloga());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
