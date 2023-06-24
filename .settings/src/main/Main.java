package main;





import models.Korisnik;
import java.util.List;

import javax.swing.SwingUtilities;

import gui.LoginProzor;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Korisnik> korisnici = Korisnik.ucitajKorisnikeIzDatoteke("src/data/korisnik.txt");

            LoginProzor loginProzor = new LoginProzor(korisnici);
            loginProzor.setVisible(true);
        });
    }
}








