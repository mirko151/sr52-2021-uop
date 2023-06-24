package gui;
import models.Administrator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdministratoriProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<Administrator> listaAdministratora;
    private JTable tabelaAdministratora;
    private DefaultTableModel tableModel;

    public AdministratoriProzor(List<Administrator> administratori) {
        this.listaAdministratora = administratori;

        setTitle("Administratori");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        populateTable();
    }

    private void initComponents() {
        tabelaAdministratora = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaAdministratora);
        add(scrollPane, BorderLayout.CENTER);

        // Definišemo nazive kolona tabele
        String[] naziviKolona = {
                "ID",
                "Ime",
                "Prezime",
                "Adresa",
                "Broj Telefona"
                // Dodajte nazive za ostale kolone po potrebi
        };

        // Kreiramo model tabele
        tableModel = new DefaultTableModel(naziviKolona, 0);
        tabelaAdministratora.setModel(tableModel);

        JButton sacuvajButton = new JButton("Sačuvaj");
        sacuvajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajAdministratoreUDatoteku("src/data/korisnik.txt");
            }
        });

        JButton izmeniButton = new JButton("Izmeni");
        izmeniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = tabelaAdministratora.getSelectedRow();
                if (selektovaniRed != -1) {
                    // Uzimamo selektovanog administratora na osnovu selektovanog reda
                    Administrator administrator = listaAdministratora.get(selektovaniRed);

                    // Otvaramo prozor za izmenu administratora
                    IzmeniAdministratoraProzor izmeniAdministratoraProzor = new IzmeniAdministratoraProzor(administrator);
                    izmeniAdministratoraProzor.setVisible(true);
                }
            }
        });

        JButton obrisiButton = new JButton("Obriši");
        obrisiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = tabelaAdministratora.getSelectedRow();
                if (selektovaniRed != -1) {
                    // Uzimamo administratora na osnovu selektovanog reda
                    Administrator administrator = listaAdministratora.get(selektovaniRed);

                    // Uklanjamo administratora iz liste
                    listaAdministratora.remove(administrator);

                    // Uklanjamo red iz tabele
                    tableModel.removeRow(selektovaniRed);

                    // Čuvamo ažuriranu listu administratora u datoteku
                    sacuvajAdministratoreUDatoteku("src/data/korisnik.txt");
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
        for (Administrator administrator : listaAdministratora) {
            Object[] redPodaci = {
                    administrator.getId(),
                    administrator.getIme(),
                    administrator.getPrezime(),
                    administrator.getAdresa(),
                    administrator.getBrojTelefona()
                    // Dodajte vrednosti za ostale kolone po potrebi
            };
            tableModel.addRow(redPodaci);
        }
    }

    private void sacuvajAdministratoreUDatoteku(String putanja) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(putanja))) {
            for (Administrator administrator : listaAdministratora) {
                writer.println(administrator.getId() + "|" +
                        administrator.getIme() + "|" +
                        administrator.getPrezime() + "|" +
                        administrator.getJMBG() + "|" +
                        administrator.getAdresa() + "|" +
                        administrator.getBrojTelefona() + "|" +
                        administrator.getKorisnickoIme() + "|" +
                        administrator.getLozinka() + "|" +
                        administrator.getUloga()
                        // Dodajte ostale osobine po potrebi
                );
            }
            JOptionPane.showMessageDialog(this, "Administratori su uspešno sačuvani.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Greška prilikom čuvanja administratora.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}

