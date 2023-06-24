package models;

import java.util.List;

public class Turista extends Korisnik {
    private String brojPasosa;
    private List<Rezervacija> rezervacije;

    public Turista(int id, String ime, String prezime, String brojPasosa, String jmbg, String adresa, String brojTelefona, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
        this.brojPasosa = brojPasosa;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}


 