package models;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rezervacija {
    private static int brojacID = 1;

    private int id;
    private Turista kupac;
    private TuristickiAgent prodavac;
    private int brojPutnika;
    private double ukupnaCenaSmestaja;
    private StatusRezervacije status;
    private LocalDateTime datumKreiranja;
    private Aranzman aranzman;

    public Rezervacija(Turista kupac, TuristickiAgent prodavac, int brojPutnika, Aranzman aranzman) {
        this.id = brojacID++;
        this.kupac = kupac;
        this.prodavac = prodavac;
        this.brojPutnika = brojPutnika;
        this.aranzman = aranzman;
        this.status = StatusRezervacije.KREIRANA;
        this.datumKreiranja = LocalDateTime.now();
        this.ukupnaCenaSmestaja = 0.0;
    }

    public static int getBrojacID() {
        return brojacID;
    }

    public static void setBrojacID(int brojacID) {
        Rezervacija.brojacID = brojacID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Turista getKupac() {
        return kupac;
    }

    public void setKupac(Turista kupac) {
        this.kupac = kupac;
    }

    public TuristickiAgent getProdavac() {
        return prodavac;
    }

    public void setProdavac(TuristickiAgent prodavac) {
        this.prodavac = prodavac;
    }

    public int getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
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

    public LocalDateTime getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDateTime datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public void setAranzman(Aranzman aranzman) {
        this.aranzman = aranzman;
    }

    public static List<Aranzman> ucitajAranzmaneIzDatoteke(String putanjaDoDatoteke) {
        List<Aranzman> aranzmani = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDoDatoteke))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 8) {
                    String tipAranzmana = parts[0];
                    String slikaAranzmana = parts[1];

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date datumPolaska = dateFormat.parse(parts[2]);

                    int brojNocenja = Integer.parseInt(parts[3]);
                    int kapacitet = Integer.parseInt(parts[4]);
                    String tipSmestaja = parts[5];
                    double cenaPoOsobi = Double.parseDouble(parts[6]);
                    double sajamskiPopust = Double.parseDouble(parts[7]);

                    // Kreirajte objekat TuristickiAgent sa odgovarajućim argumentima konstruktora
                    TuristickiAgent prodavac = new TuristickiAgent(0, "", "", "", "", "", "", "", null);

                    Aranzman aranzman = new Aranzman(prodavac, tipAranzmana, slikaAranzmana, datumPolaska, brojNocenja, kapacitet, tipSmestaja, cenaPoOsobi, sajamskiPopust);
                    aranzmani.add(aranzman);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return aranzmani;
    }


    public void sacuvajUDatoteku(String putanja) {
        try {
            File file = new File(putanja);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Format the data for writing to the file
            String data = String.format("%d;%s;%s;%d;%f;%s;%s;%s%n",
                    id, kupac.toString(), prodavac.toString(), brojPutnika, ukupnaCenaSmestaja,
                    status.toString(), datumKreiranja.toString(), aranzman.toString());

            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", kupac=" + kupac +
                ", prodavac=" + prodavac +
                ", brojPutnika=" + brojPutnika +
                ", ukupnaCenaSmestaja=" + ukupnaCenaSmestaja +
                ", status=" + status +
                ", datumKreiranja=" + datumKreiranja +
                ", aranzman=" + aranzman +
                '}';
    }
}
