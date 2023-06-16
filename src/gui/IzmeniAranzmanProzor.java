package gui;

import javax.swing.*;

import models.Aranzman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IzmeniAranzmanProzor extends JFrame {
    private JTextField textFieldDatumPolaska;
    private JTextField textFieldBrojNocenja;
    private JTextField textFieldKapacitet;
    private JTextField textFieldTipSmestaja;
    private JTextField textFieldCenaPoOsobi;
    private JButton sacuvajButton;

    private List<Aranzman> arrangements;
    private Aranzman aranzman;

    public IzmeniAranzmanProzor(List<Aranzman> arrangements, Aranzman aranzman) {
        this.arrangements = arrangements;
        this.aranzman = aranzman;

        setTitle("Izmena aranžmana");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel labelDatumPolaska = new JLabel("Datum polaska (yyyy-MM-dd):");
        textFieldDatumPolaska = new JTextField();
        panel.add(labelDatumPolaska);
        panel.add(textFieldDatumPolaska);

        JLabel labelBrojNocenja = new JLabel("Broj noćenja:");
        textFieldBrojNocenja = new JTextField();
        panel.add(labelBrojNocenja);
        panel.add(textFieldBrojNocenja);

        JLabel labelKapacitet = new JLabel("Kapacitet:");
        textFieldKapacitet = new JTextField();
        panel.add(labelKapacitet);
        panel.add(textFieldKapacitet);

        JLabel labelTipSmestaja = new JLabel("Tip smeštaja:");
        textFieldTipSmestaja = new JTextField();
        panel.add(labelTipSmestaja);
        panel.add(textFieldTipSmestaja);

        JLabel labelCenaPoOsobi = new JLabel("Cena po osobi:");
        textFieldCenaPoOsobi = new JTextField();
        panel.add(labelCenaPoOsobi);
        panel.add(textFieldCenaPoOsobi);

        sacuvajButton = new JButton("Sačuvaj");
        panel.add(new JLabel());
        panel.add(sacuvajButton);

        add(panel);

        // Popunjavanje polja sa trenutnim vrednostima aranžmana
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        textFieldDatumPolaska.setText(dateFormat.format(aranzman.getDatumPolaska()));
        textFieldBrojNocenja.setText(String.valueOf(aranzman.getBrojNocenja()));
        textFieldKapacitet.setText(String.valueOf(aranzman.getKapacitet()));
        textFieldTipSmestaja.setText(aranzman.getTipSmestaja());
        textFieldCenaPoOsobi.setText(String.valueOf(aranzman.getCenaPoOsobi()));

        // Dodavanje akcije na dugme Sačuvaj
        sacuvajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validacija i čitanje unetih vrednosti
                    String datumPolaskaString = textFieldDatumPolaska.getText();
                    Date datumPolaska = dateFormat.parse(datumPolaskaString);
                    int brojNocenja = Integer.parseInt(textFieldBrojNocenja.getText());
                    int kapacitet = Integer.parseInt(textFieldKapacitet.getText());
                    String tipSmestaja = textFieldTipSmestaja.getText();
                    double cenaPoOsobi = Double.parseDouble(textFieldCenaPoOsobi.getText());

                    // Ažuriranje vrednosti aranžmana
                    aranzman.setDatumPolaska(datumPolaska);
                    aranzman.setBrojNocenja(brojNocenja);
                    aranzman.setKapacitet(kapacitet);
                    aranzman.setTipSmestaja(tipSmestaja);
                    aranzman.setCenaPoOsobi(cenaPoOsobi);

                    // Snimanje izmenjenih aranžmana u datoteku
                    String putanjaDoDatoteke = "src/data/aranzmani.txt";
                    StringBuilder sb = new StringBuilder();
                    for (Aranzman a : arrangements) {
                        sb.append(a.toString()).append("\n");
                    }
                    try {
                        Files.writeString(Path.of(putanjaDoDatoteke), sb.toString(), StandardOpenOption.TRUNCATE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(IzmeniAranzmanProzor.this, "Greška prilikom snimanja izmena.");
                    }

                    JOptionPane.showMessageDialog(IzmeniAranzmanProzor.this, "Izmene su uspešno sačuvane.");
                    dispose();  
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(IzmeniAranzmanProzor.this, "Nevalidan format datuma.");
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(IzmeniAranzmanProzor.this, "Nevalidan format broja.");
                }
            }
        });
    }
}
