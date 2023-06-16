package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuristaProzor extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton pregledajAranzmaneButton;
    private JButton pregledajRezervacijeButton;
    private JButton kreirajRezervacijuButton;

    public TuristaProzor() {
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
        // Logic for displaying available aranzmani
    }

    private void pregledajRezervacije() {
        // Logic for displaying turista's rezervacije
    }

    private void kreirajRezervaciju() {
        // Logic for creating a new rezervacija
    }
}
