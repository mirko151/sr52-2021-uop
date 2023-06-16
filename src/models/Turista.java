package models;
import java.util.ArrayList;
import java.util.List;

public class Turista extends Korisnik {
    private String brojPasosa;
    private List<Rezervacija> rezervacije;

    public Turista(int id, String ime, String prezime, String brojPasosa, String jmbg, String adresa, String brojTelefona, String korisnickoIme, String lozinka,Uloga uloga) {
        super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
        this.brojPasosa = brojPasosa;
        this.rezervacije = new ArrayList<>();
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
}
