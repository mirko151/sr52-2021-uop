package gui;



import models.Korisnik;
import models.Uloga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnPrijava;

    private static List<Korisnik> korisnici;

    public LoginProzor(List<Korisnik> korisnici) {
        LoginProzor.korisnici = korisnici;

        setTitle("Prijava na sistem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        setLocationRelativeTo(null);

        JLabel lblUsername = new JLabel("Korisničko ime:");
        txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Lozinka:");
        txtPassword = new JPasswordField();
        btnPrijava = new JButton("Prijavi se");
        JButton btnRegistracija = new JButton("Registracija");

        btnPrijava.addActionListener(e -> btnPrijavaActionPerformed(e));

        btnRegistracija.addActionListener(e -> {
            RegistracijaProzor registracijaProzor = new RegistracijaProzor();
            registracijaProzor.setVisible(true);
        });

        setLayout(new GridLayout(4, 2));
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(new JLabel());
        add(btnPrijava);
        add(new JLabel());
        add(btnRegistracija);

        pack();
    }

    private void btnPrijavaActionPerformed(ActionEvent e) {
        String korisnickoIme = txtUsername.getText();
        String lozinka = new String(txtPassword.getPassword());

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnickoIme().equals(korisnickoIme) && korisnik.getLozinka().equals(lozinka)) {
                if (korisnik.getUloga() == Uloga.ADMINISTRATOR) {
                    AdminWindow adminWindow = new AdminWindow();
                    adminWindow.setVisible(true);
                    dispose(); // Zatvaranje LoginProzor samo ako je korisnik administrator
                } else if (korisnik.getUloga() == Uloga.TURISTICKI_AGENT) {
                    TuristickiAgentProzor turistickiAgentProzor = new TuristickiAgentProzor();
                    turistickiAgentProzor.setVisible(true);
                    dispose(); // Zatvaranje LoginProzor samo ako je korisnik turistički agent
                }else if (korisnik.getUloga() == Uloga.TURISTA) {
                    TuristaProzor turistaProzor = new TuristaProzor();
                    turistaProzor.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Nemate privilegije za pristup.");
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Pogrešno korisničko ime ili lozinka!");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Korisnik> korisnici = Korisnik.ucitajKorisnikeIzDatoteke("src/data/korisnik.txt");

            LoginProzor loginProzor = new LoginProzor(korisnici);
            loginProzor.setVisible(true);
        });
    }
}


    

  






            


