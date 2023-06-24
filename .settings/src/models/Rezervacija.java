package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rezervacija {
    private static int brojacID = 0;
    private int ID;
    private Turista kupac;
    private TuristickiAgent prodavac;
    private int brojPutnika;
    private double ukupnaCenaSmestaja;
    private StatusRezervacije status;
    private Date datumKreiranja;

    public Rezervacija(Turista kupac, TuristickiAgent prodavac, int brojPutnika) {
        this.ID = ++brojacID;
        this.kupac = kupac;
        this.prodavac = prodavac;
        this.brojPutnika = brojPutnika;
        this.status = StatusRezervacije.KREIRANA;
        this.datumKreiranja = new Date();
    }

    // Getteri i setteri za sve privatne atribute

    public int getID() {
        return ID;
    }

    public Turista getKupac() {
        return kupac;
    }

    public TuristickiAgent getProdavac() {
        return prodavac;
    }

    public int getBrojPutnika() {
        return brojPutnika;
    }

    public double getUkupnaCenaSmestaja() {
        return ukupnaCenaSmestaja;
    }

    public void setUkupnaCenaSmestaja(double ukupnaCenaSmestaja) {
        this.ukupnaCenaSmestaja = ukupnaCenaSmestaja;
    }

    public StatusRezervacije getStatus() {
        return status;
    }

    public void setStatus(StatusRezervacije status) {
        this.status = status;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public static List<Rezervacija> ucitajRezervacijeIzDatoteke(String putanja, List<TuristickiAgent> agenti) throws IOException, ParseException {
        List<Rezervacija> rezervacije = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(putanja))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] podaci = linija.split(";");

                if (podaci.length < 9) {
                    // Neispravan format podataka, preskoči ovu liniju
                    continue;
                }

                int id = Integer.parseInt(podaci[0]);
                Turista kupac = Turista.getTuristaById(id);

                if (podaci[3].isEmpty() || podaci[5].isEmpty() || podaci[7].isEmpty()) {
                    // Neispravan format podataka, preskoči ovu liniju ili obradi grešku na odgovarajući način
                    continue;
                }

                int prodavacId = Integer.parseInt(podaci[3]);
                TuristickiAgent prodavac = null;

                for (TuristickiAgent agent : agenti) {
                    if (agent.getId() == prodavacId) {
                        prodavac = agent;
                        break;
                    }
                }

                if (prodavac == null) {
                    // TuristickiAgent s navedenim ID-om nije pronađen, obradi grešku na odgovarajući način
                    continue;
                }

                int brojPutnika = Integer.parseInt(podaci[5]);
                double ukupnaCenaSmestaja = Double.parseDouble(podaci[6]);
                StatusRezervacije status = StatusRezervacije.valueOf(podaci[7]);
                Date datumKreiranja = new SimpleDateFormat("dd.MM.yyyy").parse(podaci[8]);

                Rezervacija rezervacija = new Rezervacija(kupac, prodavac, brojPutnika);
                rezervacija.setUkupnaCenaSmestaja(ukupnaCenaSmestaja);
                rezervacija.setStatus(status);
                rezervacija.setDatumKreiranja(datumKreiranja);

                rezervacije.add(rezervacija);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return rezervacije;
    }

    private void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
    }

    public void izmeniRezervaciju(int brojPutnika, double novaUkupnaCenaSmestaja) {
        if (status == StatusRezervacije.KREIRANA) {
            this.brojPutnika = brojPutnika;
            this.ukupnaCenaSmestaja = novaUkupnaCenaSmestaja;
        }
    }

    public void otkaziRezervaciju() {
        if (status == StatusRezervacije.KREIRANA) {
            status = StatusRezervacije.OTKAZANA;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID rezervacije: ").append(ID).append("\n");
        sb.append("Kupac: ").append(kupac.getIme()).append(" ").append(kupac.getPrezime()).append("\n");
        sb.append("Prodavac: ").append(prodavac.getIme()).append(" ").append(prodavac.getPrezime()).append("\n");
        sb.append("Broj putnika: ").append(brojPutnika).append("\n");
        sb.append("Ukupna cijena smještaja: ").append(ukupnaCenaSmestaja).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Datum kreiranja: ").append(datumKreiranja).append("\n");

        return sb.toString();
    }

    public static void sacuvajRezervacijuUDatoteku(Rezervacija rezervacija, String putanja) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(putanja, true))) {
            StringBuilder sb = new StringBuilder();

            sb.append(rezervacija.getID()).append(";");
            sb.append(rezervacija.getBrojPutnika()).append(";");
            sb.append(rezervacija.getUkupnaCenaSmestaja()).append(";");
            sb.append(rezervacija.getProdavac().getId()).append(";");
            sb.append(rezervacija.getStatus().name()).append(";");
            sb.append(new SimpleDateFormat("dd.MM.yyyy").format(rezervacija.getDatumKreiranja())).append("\n");

            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upisiRezervacijuUDatoteku(String putanjaDatoteke) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(putanjaDatoteke, true))) {
            writer.write(this.toString());
            writer.newLine();
        }
    }
}
