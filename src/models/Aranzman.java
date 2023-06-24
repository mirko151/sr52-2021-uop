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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aranzman {
    private static int brojac = 0;
    private int id;
    private TuristickiAgent prodavac;
    private String tipAranzmana;
    private String slikaAranzmana;
    private Date datumPolaska;
    private int brojNocenja;
    private int kapacitet;
    private String tipSmestaja;
    private double cenaPoOsobi;
    private double sajamskiPopust;

    public Aranzman(TuristickiAgent prodavac, String tipAranzmana, String slikaAranzmana, Date datumPolaska, int brojNocenja,
                    int kapacitet, String tipSmestaja, double cenaPoOsobi, double sajamskiPopust) {
        this.id = ++brojac;
        this.prodavac = prodavac;
        this.tipAranzmana = tipAranzmana;
        this.slikaAranzmana = slikaAranzmana;
        this.datumPolaska = datumPolaska;
        this.brojNocenja = brojNocenja;
        this.kapacitet = kapacitet;
        this.tipSmestaja = tipSmestaja;
        this.cenaPoOsobi = cenaPoOsobi;
        this.sajamskiPopust = sajamskiPopust;
    }
    public static void upisiAranzmaneUDatoteku(List<Aranzman> aranzmani, String putanjaDoDatoteke) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(putanjaDoDatoteke))) {
            for (Aranzman aranzman : aranzmani) {
                bw.write(aranzman.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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


        // ...
    


    // Getteri i setteri

    public static int getBrojac() {
        return brojac;
    }

    public static void setBrojac(int brojac) {
        Aranzman.brojac = brojac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TuristickiAgent getProdavac() {
        return prodavac;
    }

    public void setProdavac(TuristickiAgent prodavac) {
        this.prodavac = prodavac;
    }

    public String getTipAranzmana() {
        return tipAranzmana;
    }

    public void setTipAranzmana(String tipAranzmana) {
        this.tipAranzmana = tipAranzmana;
    }

    public String getSlikaAranzmana() {
        return slikaAranzmana;
    }

    public void setSlikaAranzmana(String slikaAranzmana) {
        this.slikaAranzmana = slikaAranzmana;
    }

    public Date getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(Date datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public String getTipSmestaja() {
        return tipSmestaja;
    }

    public void setTipSmestaja(String tipSmestaja) {
        this.tipSmestaja = tipSmestaja;
    }

    public double getCenaPoOsobi() {
        return cenaPoOsobi;
    }

    public void setCenaPoOsobi(double cenaPoOsobi) {
        this.cenaPoOsobi = cenaPoOsobi;
    }

    public double getSajamskiPopust() {
        return sajamskiPopust;
    }

    public void setSajamskiPopust(double sajamskiPopust) {
        this.sajamskiPopust = sajamskiPopust;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  tipAranzmana + "|" + slikaAranzmana + "|" + dateFormat.format(datumPolaska) + "|" +
                brojNocenja + "|" + kapacitet + "|" + tipSmestaja + "|" + cenaPoOsobi + "|" + sajamskiPopust;
    }
}
