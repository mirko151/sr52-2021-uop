package models;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Korisnik {
    private List<Turista> turisti;
    private List<TuristickiAgent> turistickiAgenti;
    private List<Aranzman> aranzmani;
    private List<Rezervacija> rezervacije;

    public Administrator(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
                         String korisnickoIme, String lozinka) {
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


    // Ostale metode za manipulaciju turistima, turistickim agentima, aranzmanima, rezervacijama, itd.
}



    

    

	

    

    




