package gui;
import javax.swing.*;

import models.Aranzman;
import models.Korisnik;
import models.Rezervacija;
import models.Turista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.Aranzman;
import models.Rezervacija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TuristaProzor extends JFrame {
    private JButton pregledajAranzmaneButton;
    private JButton pregledajRezervacijeButton;
    private JButton kreirajRezervacijuButton;
    private Turista turista;

    public TuristaProzor(Korisnik korisnik) {
        this.turista = (Turista) korisnik;

        setTitle("Turista");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        pregledajAranzmaneButton = new JButton("Pregledaj aranžmane");
        pregledajRezervacijeButton = new JButton("Pregledaj rezervacije");
        kreirajRezervacijuButton = new JButton("Kreiraj rezervaciju");

        pregledajAranzmaneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pregledajAranzmane();
            }
        });

        pregledajRezervacijeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pregledajRezervacije();
            }
        });

        kreirajRezervacijuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajRezervaciju();
            }
        });

        add(pregledajAranzmaneButton);
        add(pregledajRezervacijeButton);
        add(kreirajRezervacijuButton);

        setVisible(true);
    }

    private void pregledajAranzmane() {
        List<Aranzman> aranzmani = Aranzman.ucitajAranzmaneIzDatoteke("src/data/aranzmani.txt");

        // Ovdje dodajte logiku za prikazivanje dostupnih aranzmana
        // Možete koristiti JOptionPane.showMessageDialog, JTable ili neki drugi prikaz prema potrebama

        // Primer za prikazivanje aranzmana u dijaloškom okviru
        StringBuilder sb = new StringBuilder();
        for (Aranzman aranzman : aranzmani) {
            sb.append(aranzman.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Dostupni aranzmani", JOptionPane.INFORMATION_MESSAGE);
    }

    private void pregledajRezervacije() {
        List<Rezervacija> rezervacije = turista.getRezervacije();

        if (rezervacije.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nemate trenutno rezervacija.", "Rezervacije", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Vaše rezervacije:\n\n");
            for (Rezervacija rezervacija : rezervacije) {
                sb.append("ID rezervacije: ").append(rezervacija.getId()).append("\n");
                sb.append("Aranžman: ").append(rezervacija.getAranzman().getTipAranzmana()).append("\n");
                sb.append("Datum polaska: ").append(rezervacija.getAranzman().getDatumPolaska()).append("\n");
                sb.append("Broj putnika: ").append(rezervacija.getBrojPutnika()).append("\n");
                sb.append("Status: ").append(rezervacija.getStatus()).append("\n");
                sb.append("--------------------------------\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Rezervacije", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void kreirajRezervaciju() {
        // Logika za kreiranje nove rezervacije
    }
}




