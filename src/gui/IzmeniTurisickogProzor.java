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
import java.util.ArrayList;
import java.util.List;

public class IzmeniTurisickogProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private TuristickiAgent turistickiAgent;
    private JTextField imeTextField;
    private JTextField prezimeTextField;
    private JTextField adresaTextField;
    private JTextField telefonTextField;

    public IzmeniTurisickogProzor(TuristickiAgent turistickiAgent) {
        this.turistickiAgent = turistickiAgent;

        setTitle("Izmeni turističkog agenta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel imeLabel = new JLabel("Ime:");
        imeTextField = new JTextField(turistickiAgent.getIme());
        JLabel prezimeLabel = new JLabel("Prezime:");
        prezimeTextField = new JTextField(turistickiAgent.getPrezime());
        JLabel adresaLabel = new JLabel("Adresa:");
        adresaTextField = new JTextField(turistickiAgent.getAdresa());
        JLabel telefonLabel = new JLabel("Broj Telefona:");
        telefonTextField = new JTextField(turistickiAgent.getBrojTelefona());

        panel.add(imeLabel);
        panel.add(imeTextField);
        panel.add(prezimeLabel);
        panel.add(prezimeTextField);
        panel.add(adresaLabel);
        panel.add(adresaTextField);
        panel.add(telefonLabel);
        panel.add(telefonTextField);

        JButton sacuvajButton = new JButton("Sačuvaj");
        sacuvajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmene();
                dispose();
            }
        });

        JButton otkaziButton = new JButton("Otkaži");
        otkaziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sacuvajButton);
        buttonPanel.add(otkaziButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void sacuvajIzmene() {
        turistickiAgent.setIme(imeTextField.getText());
        turistickiAgent.setPrezime(prezimeTextField.getText());
        turistickiAgent.setAdresa(adresaTextField.getText());
        turistickiAgent.setBrojTelefona(telefonTextField.getText());

        // Save the updated list of TuristickiAgent to the file
        sacuvajTuristickeAgenteUDatoteku("src/data/korisnik.txt");
    }

    private void sacuvajTuristickeAgenteUDatoteku(String putanja) {
        List<TuristickiAgent> listaTuristickihAgenata = new ArrayList<>();
        listaTuristickihAgenata.add(turistickiAgent);

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
