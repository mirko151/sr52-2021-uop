package gui;

import models.Administrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmeniAdministratoraProzor extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Administrator administrator;

    private JTextField imeTextField;
    private JTextField prezimeTextField;
    private JTextField emailTextField;
    private JTextField brojTelefonaTextField;

    public IzmeniAdministratoraProzor(Administrator administrator) {
        this.administrator = administrator;

        setTitle("Izmeni Administratora");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        populateFields();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel imeLabel = new JLabel("Ime:");
        imeTextField = new JTextField();

        JLabel prezimeLabel = new JLabel("Prezime:");
        prezimeTextField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        JLabel brojTelefonaLabel = new JLabel("Broj Telefona:");
        brojTelefonaTextField = new JTextField();

        JButton sacuvajButton = new JButton("Sačuvaj");
        sacuvajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmene();
            }
        });

        panel.add(imeLabel);
        panel.add(imeTextField);
        panel.add(prezimeLabel);
        panel.add(prezimeTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(brojTelefonaLabel);
        panel.add(brojTelefonaTextField);
        panel.add(new JLabel());
        panel.add(sacuvajButton);

        add(panel, BorderLayout.CENTER);
    }

    private void populateFields() {
        imeTextField.setText(administrator.getIme());
        prezimeTextField.setText(administrator.getPrezime());
        emailTextField.setText(administrator.getAdresa());
        brojTelefonaTextField.setText(administrator.getBrojTelefona());
    }

    private void sacuvajIzmene() {
        String ime = imeTextField.getText().trim();
        String prezime = prezimeTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String brojTelefona = brojTelefonaTextField.getText().trim();

        // Perform validation on the input fields if needed

        administrator.setIme(ime);
        administrator.setPrezime(prezime);
        administrator.setAdresa(email);
        administrator.setBrojTelefona(brojTelefona);

        // Save the changes to the file or database

        JOptionPane.showMessageDialog(this, "Izmene su uspešno sačuvane.");
        dispose();
    }
}

