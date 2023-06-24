package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Aranzman;
import models.Rezervacija;
import models.TuristickiAgent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class TuristickiAgentProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton kreirajAranzmanButton;
    private JButton pregledajRezervacijeButton;
    private List<TuristickiAgent> agenti;
    private List<Rezervacija> rezervacije;

    public TuristickiAgentProzor() {
        setTitle("Turistički agent");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        kreirajAranzmanButton = new JButton("Kreiraj aranžman");
        pregledajRezervacijeButton = new JButton("Pregledaj rezervacije");

        kreirajAranzmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajAranzman();
            }
        });

        pregledajRezervacijeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pregledajRezervacije();
            }
        });

        add(kreirajAranzmanButton);
        add(pregledajRezervacijeButton);

        setVisible(true);
    }

    private void kreirajAranzman() {
        // Unesite potrebne informacije za kreiranje aranžmana
        String tipAranzmana = JOptionPane.showInputDialog("Unesite tip aranžmana:");
        String slikaAranzmana = JOptionPane.showInputDialog("Unesite URL slike aranžmana:");

        // Unesite datum polaska u formatu "yyyy-MM-dd"
        String datumPolaskaStr = JOptionPane.showInputDialog("Unesite datum polaska (yyyy-MM-dd):");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date datumPolaska = null;
        try {
            datumPolaska = dateFormat.parse(datumPolaskaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Neispravan format datuma!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int brojNocenja = Integer.parseInt(JOptionPane.showInputDialog("Unesite broj noćenja:"));
        int kapacitet = Integer.parseInt(JOptionPane.showInputDialog("Unesite kapacitet:"));
        String tipSmestaja = JOptionPane.showInputDialog("Unesite tip smeštaja:");
        double cenaPoOsobi = Double.parseDouble(JOptionPane.showInputDialog("Unesite cenu po osobi:"));
        double sajamskiPopust = Double.parseDouble(JOptionPane.showInputDialog("Unesite sajamski popust:"));

        // Kreirajte objekat TuristickiAgent sa odgovarajućim argumentima konstruktora
        TuristickiAgent prodavac = new TuristickiAgent(0, "", "", "", "", "", "", "", null);

        // Kreirajte objekat Aranzman sa unetim podacima
        Aranzman aranzman = new Aranzman(prodavac, tipAranzmana, slikaAranzmana, datumPolaska, brojNocenja, kapacitet, tipSmestaja, cenaPoOsobi, sajamskiPopust);

        // Dodajte logiku za čuvanje aranžmana u datoteku
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/aranzmani.txt", true))) {
            writer.write(aranzman.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greška pri upisivanju u datoteku!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Aranžman uspešno kreiran i upisan u datoteku!", "Kreiranje aranžmana", JOptionPane.INFORMATION_MESSAGE);
    }

    private void pregledajRezervacije() {
        // Učitavanje rezervacija iz datoteke
        String putanjaDatoteke = "src/data/rezervacije.txt";
        try {
            rezervacije = Rezervacija.ucitajRezervacijeIzDatoteke(putanjaDatoteke, agenti);
        } catch (IOException | ParseException ex) {
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju rezervacija!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prikaz rezervacija u dijalognom okviru
        StringBuilder sb = new StringBuilder();
        for (Rezervacija rezervacija : rezervacije) {
            sb.append(rezervacija.toString());
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Rezervacije", JOptionPane.INFORMATION_MESSAGE);
    }
}




