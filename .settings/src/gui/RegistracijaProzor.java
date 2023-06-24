package gui;

import javax.swing.*;

import models.Korisnik;
import models.Uloga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistracijaProzor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtIme;
    private JTextField txtPrezime;
    private JTextField txtJMBG;
    private JTextField txtAdresa;
    private JTextField txtBrojTelefona;
    private JTextField txtKorisnickoIme;
    private JPasswordField txtLozinka;
    private JComboBox<String> cmbUloga;
    private JButton btnRegistracija;

    public RegistracijaProzor() {
        setTitle("Registracija");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        setLocationRelativeTo(null);

        JLabel lblIme = new JLabel("Ime:");
        txtIme = new JTextField();
        JLabel lblPrezime = new JLabel("Prezime:");
        txtPrezime = new JTextField();
        JLabel lblJMBG = new JLabel("JMBG:");
        txtJMBG = new JTextField();
        JLabel lblAdresa = new JLabel("Adresa:");
        txtAdresa = new JTextField();
        JLabel lblBrojTelefona = new JLabel("Broj telefona:");
        txtBrojTelefona = new JTextField();
        JLabel lblKorisnickoIme = new JLabel("Korisničko ime:");
        txtKorisnickoIme = new JTextField();
        JLabel lblLozinka = new JLabel("Lozinka:");
        txtLozinka = new JPasswordField();
        JLabel lblUloga = new JLabel("Uloga:");
        cmbUloga = new JComboBox<>(new String[]{"Administrator", "Turistički agent", "Turista"});
        btnRegistracija = new JButton("Registruj se");

        btnRegistracija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = generateId();
                String ime = txtIme.getText();
                String prezime = txtPrezime.getText();
                String jmbg = txtJMBG.getText();
                String adresa = txtAdresa.getText();
                String brojTelefona = txtBrojTelefona.getText();
                String korisnickoIme = txtKorisnickoIme.getText();
                String lozinka = new String(txtLozinka.getPassword());
                Uloga uloga = Uloga.fromString((String) cmbUloga.getSelectedItem());

                Korisnik noviKorisnik = new Korisnik(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
                Korisnik.dodajKorisnika(noviKorisnik);

                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/data/korisnik.txt", true)))) {
                    writer.println(noviKorisnik.toString());
                    JOptionPane.showMessageDialog(null, "Uspešno ste se registrovali!");
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Greška prilikom upisa u datoteku!");
                }
            }
        });

        setLayout(new GridLayout(8, 2));
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblJMBG);
        add(txtJMBG);
        add(lblAdresa);
        add(txtAdresa);
        add(lblBrojTelefona);
        add(txtBrojTelefona);
        add(lblKorisnickoIme);
        add(txtKorisnickoIme);
        add(lblLozinka);
        add(txtLozinka);
        add(lblUloga);
        add(cmbUloga);
        add(btnRegistracija);

        pack();
    }

    private static int generateId() {
        int id = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/korisnik.txt"))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                if (!linija.trim().isEmpty()) {
                    String[] delovi = linija.split("\\|");
                    if (delovi.length == 9) {
                        int currentId = Integer.parseInt(delovi[0]);
                        if (currentId > id) {
                            id = currentId;
                        }
                    } else {
                        System.out.println("Invalid data format at line: " + linija);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    public static void main(String[] args) {
        RegistracijaProzor prozor = new RegistracijaProzor();
        prozor.setVisible(true);
    }
}



