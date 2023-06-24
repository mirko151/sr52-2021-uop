package models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Turista extends Korisnik {
    private List<Rezervacija> rezervacije;
    private static List<Turista> turisti = new ArrayList<>();
    private static Turista prijavljeniTurista;

    public Turista(int id, String ime, String prezime,  String jmbg, String adresa, String brojTelefona, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);

        this.rezervacije = new ArrayList<>();
        turisti.add(this);
    }


    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void dodajRezervaciju(Rezervacija rezervacija) {
        rezervacije.add(rezervacija);
    }

    public void izmeniRezervaciju(Rezervacija rezervacija, int brojPutnika) {
        if (rezervacije.contains(rezervacija) && rezervacija.getStatus() == StatusRezervacije.KREIRANA) {
            rezervacija.setBrojPutnika(brojPutnika);
        }
    }

    public void otkaziRezervaciju(Rezervacija rezervacija) {
        if (rezervacije.contains(rezervacija) && rezervacija.getStatus() == StatusRezervacije.KREIRANA) {
            rezervacija.setStatus(StatusRezervacije.OTKAZANA);
        }
    }

    public static Turista getTuristaById(int id) {
        for (Turista turista : turisti) {
            if (turista.getId() == id) {
                return turista;
            }
        }
        return null;
    }

    public static void prijaviTuristu(Turista turista) {
        prijavljeniTurista = turista;
    }

    public static void odjaviTuristu() {
        prijavljeniTurista = null;
    }

    public static Turista getPrijavljeniTurista() {
        return prijavljeniTurista;
    }

    public static List<Turista> getTuristi() {
        return turisti;
    }

    public static List<Turista> citajTuristeIzDatoteke(String putanjaDoDatoteke) {
        List<Turista> turisti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDoDatoteke))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 10 && parts[9].equals("TURISTA")) {
                    int id = Integer.parseInt(parts[0]);
                    String ime = parts[1];
                    String prezime = parts[2];
                    String jmbg = parts[4];
                    String adresa = parts[5];
                    String brojTelefona = parts[6];
                    String korisnickoIme = parts[7];
                    String lozinka = parts[8];
                    Uloga uloga = Uloga.valueOf(parts[9]);

                    Turista turista = new Turista(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
                    turisti.add(turista);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return turisti;
    }
}















