package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.Administrator;
import models.Aranzman;
import models.Turista;
import models.TuristickiAgent;

public class AdminWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton turistiButton;
    private JButton turistickiAgentiButton;
    private JButton administratoriButton;
    private JButton aranzmaniButton;
    private JButton rezervacijeButton;
    private List<Aranzman> aranzmani;

    public AdminWindow() {
        setTitle("Administracija");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aranzmani = new ArrayList<>();

        turistiButton = new JButton("Turisti");
        turistiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTuristiForm();
            }
        });

        turistickiAgentiButton = new JButton("Turistički agenti");
        turistickiAgentiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openTuristickiAgentiFormProzor();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        administratoriButton = new JButton("Administratori");
        administratoriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAdministratoriProzor();
            }
        });

        aranzmaniButton = new JButton("Aranžmani");
        aranzmaniButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAranzmanProzor();
            }
        });

        rezervacijeButton = new JButton("Rezervacije");
        rezervacijeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code for opening reservation form
            }
        });

        JPanel panel = new JPanel();
        panel.add(turistiButton);
        panel.add(turistickiAgentiButton);
        panel.add(administratoriButton);
        panel.add(aranzmaniButton);
        panel.add(rezervacijeButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void openTuristiForm() {
        String putanjaDoDatoteke = "src/data/korisnik.txt";
        List<Turista> turisti = TuristiForm.citajTuristeIzDatoteke(putanjaDoDatoteke);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TuristiForm(turisti).setVisible(true);
            }
        });
    }

    private void openTuristickiAgentiFormProzor() throws IOException {
        String putanjaDoDatoteke = "src/data/korisnik.txt";
        List<TuristickiAgent> listaTuristickihAgenata = TuristickiAgent.ucitajTuristickeAgenteIzDatoteke(putanjaDoDatoteke);

        TuristickiAgentiForm agentiForm = new TuristickiAgentiForm(listaTuristickihAgenata);
        agentiForm.setVisible(true);
    }

    private void openAdministratoriProzor() {
        String putanjaDoDatoteke = "src/data/korisnik.txt";
        List<Administrator> listaAdministratora = Administrator.ucitajAdministratoreIzDatoteke(putanjaDoDatoteke);

        AdministratoriProzor administratoriProzor = new AdministratoriProzor(listaAdministratora);
        administratoriProzor.setVisible(true);
    }

    private void openAranzmanProzor() {
        String putanjaDoDatoteke = "src/data/aranzmani.txt";
        List<Aranzman> aranzmani = Aranzman.ucitajAranzmaneIzDatoteke(putanjaDoDatoteke);

        AranzmanProzor prozor = new AranzmanProzor(aranzmani);
        prozor.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminWindow().setVisible(true);
            }
        });
    }
}
