package gui;

import javax.swing.*;

import models.Turista;
import models.TuristickiAgent;

import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class IzmeniTuristuProzor extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Turista turista;
    private JTextField imeTextField;
    private JTextField prezimeTextField;
    private JTextField jmbgTextField;
    private JTextField adresaTextField;
    private JTextField brojTelefonaTextField;

    public IzmeniTuristuProzor(Turista selectedTurista) {
        this.turista = selectedTurista;

        setTitle("Izmena turiste");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create components for editing turista
        JLabel imeLabel = new JLabel("Ime:");
        imeTextField = new JTextField(selectedTurista.getIme(), 20);

        JLabel prezimeLabel = new JLabel("Prezime:");
        prezimeTextField = new JTextField(selectedTurista.getPrezime(), 20);

        JLabel jmbgLabel = new JLabel("JMBG:");
        jmbgTextField = new JTextField(selectedTurista.getJMBG(), 20);

        JLabel adresaLabel = new JLabel("Adresa:");
        adresaTextField = new JTextField(selectedTurista.getAdresa(), 20);

        JLabel brojTelefonaLabel = new JLabel("Broj telefona:");
        brojTelefonaTextField = new JTextField(selectedTurista.getBrojTelefona(), 20);

        JButton sacuvajButton = new JButton("Sačuvaj");
        sacuvajButton.addActionListener(e -> sacuvajIzmene());

        // Add components to the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(imeLabel);
        panel.add(imeTextField);
        panel.add(prezimeLabel);
        panel.add(prezimeTextField);
        panel.add(jmbgLabel);
        panel.add(jmbgTextField);
        panel.add(adresaLabel);
        panel.add(adresaTextField);
        panel.add(brojTelefonaLabel);
        panel.add(brojTelefonaTextField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(sacuvajButton);

        add(panel);
    }

    private void sacuvajIzmene() {
        // Get the updated values from the text fields
        String novoIme = imeTextField.getText();
        String novoPrezime = prezimeTextField.getText();
        String noviJmbg = jmbgTextField.getText();
        String novaAdresa = adresaTextField.getText();
        String noviBrojTelefona = brojTelefonaTextField.getText();

        // Update the turista object with the new values
        turista.setIme(novoIme);
        turista.setPrezime(novoPrezime);
        turista.setJMBG(noviJmbg);
        turista.setAdresa(novaAdresa);
        turista.setBrojTelefona(noviBrojTelefona);

        // Perform any other necessary operations to save the changes

        // Close the edit window
        dispose();
    }
}


