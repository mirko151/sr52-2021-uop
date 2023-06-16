package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.Aranzman;

public class AdminWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton turistiButton;
    private JButton turistickiAgentiButton;
    private JButton administratoriButton;
    private JButton aranzmaniButton;
    private JButton rezervacijeButton;
    private ArrayList<Aranzman> aranzmani;

    public AdminWindow() {
        setTitle("Administracija");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aranzmani = new ArrayList<>(); 

        turistiButton = new JButton("Turisti");
        turistiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kod za otvaranje forme za pregled, izmenu i brisanje turista
            }
        });

        turistickiAgentiButton = new JButton("Turistički agenti");
        turistickiAgentiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kod za otvaranje forme za pregled, izmenu i brisanje turističkih agenata
            }

        });

        administratoriButton = new JButton("Administratori");
        administratoriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kod za otvaranje forme za pregled, izmenu i brisanje administratora
            }
        });

        aranzmaniButton = new JButton("Aranžmani");
        aranzmaniButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAranzmanProzor(); // Poziv metode za otvaranje AranzmanProzor
            }
        });

        rezervacijeButton = new JButton("Rezervacije");
        rezervacijeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kod za otvaranje forme za pregled, izmenu i brisanje rezervacija
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
    private void openAranzmanProzor() {
        AranzmanProzor aranzmanProzor = new AranzmanProzor(aranzmani);
        aranzmanProzor.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminWindow();
            }
        });
    }
}
