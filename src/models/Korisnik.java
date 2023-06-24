package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Korisnik {
    private int id;
    private String ime;
    private String prezime;
    private String JMBG;
    private String adresa;
    private String brojTelefona;
    private String korisnickoIme;
    private String lozinka;
    private Uloga uloga;
    
    private static int nextId = 1;
    private static List<Korisnik> korisnici = new ArrayList<>();

    public Korisnik(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme, String lozinka, Uloga uloga) {
        this.id = nextId++;
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = jmbg;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = uloga;
    }

    public static void dodajKorisnika(Korisnik korisnik) {
        korisnici.add(korisnik);
    }



    // Getteri i setteri

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
 

    public static List<Korisnik> ucitajKorisnikeIzDatoteke(String nazivDatoteke) {
        List<Korisnik> korisnici = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nazivDatoteke))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                if (!linija.trim().isEmpty()) {
                    String[] delovi = linija.split("\\|");
                    if (delovi.length == 9) {
                        int id = Integer.parseInt(delovi[0]);
                        String ime = delovi[1];
                        String prezime = delovi[2];
                        String jmbg = delovi[3];
                        String adresa = delovi[4];
                        String brojTelefona = delovi[5];
                        String korisnickoIme = delovi[6];
                        String lozinka = delovi[7];
                        Uloga uloga = Uloga.fromString(delovi[8]);

                        Korisnik korisnik = new Korisnik(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
                        korisnici.add(korisnik);
                    } else {
                        System.out.println("Invalid data format at line: " + linija);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return korisnici;
    }
    private static int generateId() {
        int id = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/korisnik.txt"))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                if (!linija.trim().isEmpty()) {
                    String[] delovi = linija.split("\\|");
                    if (delovi.length == 9) {
                        int currentId = Integer.parseInt(delovi[0]);
                        if (currentId > id) {
                            id = currentId;
                        }
                    } else {
                        System.out.println("Invalid data format at line: " + linija);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id + 1;
    }



    public static void sacuvajKorisnike(String nazivDatoteke) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nazivDatoteke))) {
            for (Korisnik korisnik : korisnici) {
                writer.write(korisnik.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override

public String toString() {
    // Formatiranje objekta klase Korisnik kao stringa za čuvanje u datoteku
    return String.format("\n%d|%s|%s|%s|%s|%s|%s|%s|%s\n",
            id, ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka, uloga);

    }
}



