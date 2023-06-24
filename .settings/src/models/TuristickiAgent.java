package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TuristickiAgent extends Korisnik {
    private List<Aranzman> kreiraniAranzmani;

    public TuristickiAgent(int id, String ime, String prezime, String JMBG, String adresa, String brojTelefona, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
        kreiraniAranzmani = new ArrayList<>();
    }

    public List<Aranzman> getKreiraniAranzmani() {
        return kreiraniAranzmani;
    }

    public void kreirajAranzman(Aranzman aranzman) {
        kreiraniAranzmani.add(aranzman);
    }

    public static TuristickiAgent pronadjiTuristickogAgentaPoId(List<TuristickiAgent> agenti, int prodavacId) {
        for (TuristickiAgent agent : agenti) {
            if (agent.getId() == prodavacId) {
                return agent;
            }
        }
        return null;
    }

    public List<Rezervacija> getRezervacije() {
        return getRezervacije();
    }

    public static void sacuvajTuristickeAgenteUDatoteku(List<TuristickiAgent> listaTuristickihAgenata, String putanja) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(putanja))) {
            for (TuristickiAgent agent : listaTuristickihAgenata) {
                writer.println(agent.getId() + "|" +
                        agent.getIme() + "|" +
                        agent.getPrezime() + "|" +
                        agent.getJMBG() + "|" +
                        agent.getAdresa() + "|" +
                        agent.getBrojTelefona() + "|" +
                        agent.getKorisnickoIme() + "|" +
                        agent.getLozinka() + "|" +
                        agent.getUloga());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TuristickiAgent> ucitajTuristickeAgenteIzDatoteke(String putanja) {
        List<TuristickiAgent> agenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(putanja))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] podaci = linija.split("\\|");

                if (podaci.length < 9) {
                    // Invalid data format, skip this line
                    continue;
                }

                int id = Integer.parseInt(podaci[0]);
                String ime = podaci[1];
                String prezime = podaci[2];
                String JMBG = podaci[3];
                String adresa = podaci[4];
                String brojTelefona = podaci[5];
                String korisnickoIme = podaci[6];
                String lozinka = podaci[7];
                Uloga uloga = Uloga.valueOf(podaci[8]);

                if (uloga == Uloga.TURISTICKI_AGENT) {
                    TuristickiAgent agent = new TuristickiAgent(id, ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
                    agenti.add(agent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return agenti;
    }

}
