package models;

import java.util.ArrayList;
import java.util.List;

public class TuristickiAgent extends Korisnik {
    private List<Aranzman> kreiraniAranzmani;

    public TuristickiAgent(int id, String ime, String prezime, String JMBG,  String adresa, String brojTelefona, String korisnickoIme, String lozinka,Uloga uloga) {
        super(id, ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
        kreiraniAranzmani = new ArrayList<>();
    }

    public List<Aranzman> getKreiraniAranzmani() {
        return kreiraniAranzmani;
    }

    public void kreirajAranzman(Aranzman aranzman) {
        kreiraniAranzmani.add(aranzman);
    }
}



    




