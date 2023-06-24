package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends Korisnik {
    private List<Turista> turisti;
    private List<TuristickiAgent> turistickiAgenti;
    private List<Aranzman> aranzmani;
    private List<Rezervacija> rezervacije;

    public Administrator(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, Uloga.ADMINISTRATOR);
        this.turisti = new ArrayList<>();
        this.turistickiAgenti = new ArrayList<>();
        this.aranzmani = new ArrayList<>();
        this.rezervacije = new ArrayList<>();
    }

    public void dodajTuristu(Turista turista) {
        turisti.add(turista);
    }

    public void dodajTuristickogAgenta(TuristickiAgent agent) {
        turistickiAgenti.add(agent);
    }

    public void dodajAranzman(Aranzman aranzman) {
        aranzmani.add(aranzman);
    }

    public void dodajRezervaciju(Rezervacija rezervacija) {
        rezervacije.add(rezervacija);
    }

    public static List<Administrator> ucitajAdministratoreIzDatoteke(String putanja) {
        List<Administrator> listaAdministratora = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(putanja))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 9) {
                    int id = Integer.parseInt(parts[0]);
                    String ime = parts[1];
                    String prezime = parts[2];
                    String jmbg = parts[3];
                    String adresa = parts[4];
                    String brojTelefona = parts[5];
                    String korisnickoIme = parts[6];
                    String lozinka = parts[7];
                    Uloga uloga = Uloga.valueOf(parts[8]);

                    if (uloga == Uloga.ADMINISTRATOR) {
                        Administrator administrator = new Administrator(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
                        listaAdministratora.add(administrator);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAdministratora;
    }





}
