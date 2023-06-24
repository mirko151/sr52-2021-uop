package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.Aranzman;
import models.Korisnik;
import models.Rezervacija;
import models.Turista;
import models.TuristickiAgent;
import models.Uloga;

import java.util.ArrayList;
import java.util.List;

public class TuristaProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel lblDobrodoslica;
    private JButton btnPregledAranzmana;
    private JButton btnKreiranjeRezervacije;

    public TuristaProzor() {
        setTitle("Prijavljeni turista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        initComponents();
        addComponents();

        setVisible(true);
    }

    private void initComponents() {
        lblDobrodoslica = new JLabel("Dobrodošli! Izaberite željenu akciju:");

        btnPregledAranzmana = new JButton("Pregled aranžmana");
        btnPregledAranzmana.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logika za prikaz aranžmana
                List<Aranzman> aranzmani = Aranzman.ucitajAranzmaneIzDatoteke("src/data/aranzmani.txt");

                // Priprema stringa za prikaz aranžmana
                StringBuilder sb = new StringBuilder();
                for (Aranzman aranzman : aranzmani) {
                    sb.append("ID: ").append(aranzman.getId()).append("\n");
                    sb.append("Tip aranžmana: ").append(aranzman.getTipAranzmana()).append("\n");
                    sb.append("Datum polaska: ").append(aranzman.getDatumPolaska()).append("\n");
                    sb.append("Broj noćenja: ").append(aranzman.getBrojNocenja()).append("\n");
                    sb.append("Kapacitet: ").append(aranzman.getKapacitet()).append("\n");
                    sb.append("Tip smeštaja: ").append(aranzman.getTipSmestaja()).append("\n");
                    sb.append("Cena po osobi: ").append(aranzman.getCenaPoOsobi()).append("\n");
                    sb.append("Sajamski popust: ").append(aranzman.getSajamskiPopust()).append("\n");
                    sb.append("-----------------------------------\n");
                }

                // Prikaz aranžmana u dijalogu
                JOptionPane.showMessageDialog(null, sb.toString(), "Pregled aranžmana", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnKreiranjeRezervacije = new JButton("Kreiranje rezervacije");
        btnKreiranjeRezervacije.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logika za kreiranje rezervacije
                String brojPutnikaString = JOptionPane.showInputDialog(null, "Unesite broj putnika:", "Kreiranje rezervacije", JOptionPane.QUESTION_MESSAGE);
                try {
                    int brojPutnika = Integer.parseInt(brojPutnikaString);

                    // Odabir turističkog agenta
                    System.out.println("Poziv metode ucitajKorisnikeIzDatoteke");
                    List<Korisnik> korisnici = Korisnik.ucitajKorisnikeIzDatoteke("src/data/korisnik.txt");
                    List<TuristickiAgent> agenti = new ArrayList<>();
                    for (Korisnik korisnik : korisnici) {
                        if (korisnik.getUloga().equals("TURISTICKI_AGENT")) {
                            agenti.add((TuristickiAgent) korisnik);
                        }
                    }
                    System.out.println("Broj učitanih agenata: " + agenti.size());
                    for (TuristickiAgent agent : agenti) {
                        System.out.println("Agent: " + agent.getIme() + " " + agent.getPrezime());
                    }

                    if (!agenti.isEmpty()) {
                        TuristickiAgent izabraniAgent = (TuristickiAgent) JOptionPane.showInputDialog(null, "Izaberite turističkog agenta:", "Kreiranje rezervacije", JOptionPane.QUESTION_MESSAGE, null, agenti.toArray(), agenti.get(0));

                        if (izabraniAgent != null) {
                            // Logika za kreiranje rezervacije sa izabranim agentom
                            List<Rezervacija> rezervacije = izabraniAgent.getRezervacije();
                            // Ovdje možete nastaviti s logikom za kreiranje rezervacije
                        } else {
                            JOptionPane.showMessageDialog(null, "Niste izabrali turističkog agenta.", "Kreiranje rezervacije", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nema dostupnih turističkih agenata.", "Kreiranje rezervacije", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Niste uneli ispravan broj putnika.", "Kreiranje rezervacije", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



    }


    private void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(lblDobrodoslica, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(btnPregledAranzmana, gbc);

        gbc.gridx = 1;
        add(btnKreiranjeRezervacije, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TuristaProzor();
            }
        });
    }
}
