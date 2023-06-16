package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.TuristickiAgent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuristickiAgentProzor extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton kreirajAranzmanButton;
    private JButton pregledajRezervacijeButton;

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
        // Logika za kreiranje aranžmana
    }

    private void pregledajRezervacije() {
        // Logika za pregled rezervacija
    }
}


