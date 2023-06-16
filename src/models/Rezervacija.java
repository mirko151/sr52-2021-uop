package models;

import java.time.LocalDateTime;

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


	/**
	 * @return the brojacID
	 */
	public static int getBrojacID() {
		return brojacID;
	}

	/**
	 * @param brojacID the brojacID to set
	 */
	public static void setBrojacID(int brojacID) {
		Rezervacija.brojacID = brojacID;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the kupac
	 */
	public Turista getKupac() {
		return kupac;
	}

	/**
	 * @param kupac the kupac to set
	 */
	public void setKupac(Turista kupac) {
		this.kupac = kupac;
	}

	/**
	 * @return the prodavac
	 */
	public TuristickiAgent getProdavac() {
		return prodavac;
	}

	/**
	 * @param prodavac the prodavac to set
	 */
	public void setProdavac(TuristickiAgent prodavac) {
		this.prodavac = prodavac;
	}

	/**
	 * @return the brojPutnika
	 */
	public int getBrojPutnika() {
		return brojPutnika;
	}

	/**
	 * @param brojPutnika the brojPutnika to set
	 */
	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	/**
	 * @return the ukupnaCenaSmestaja
	 */
	public double getUkupnaCenaSmestaja() {
		return ukupnaCenaSmestaja;
	}

	/**
	 * @param ukupnaCenaSmestaja the ukupnaCenaSmestaja to set
	 */
	public void setUkupnaCenaSmestaja(double ukupnaCenaSmestaja) {
		this.ukupnaCenaSmestaja = ukupnaCenaSmestaja;
	}

	/**
	 * @return the status
	 */
	public StatusRezervacije getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusRezervacije status) {
		this.status = status;
	}

	/**
	 * @return the datumKreiranja
	 */
	public LocalDateTime getDatumKreiranja() {
		return datumKreiranja;
	}

	/**
	 * @param datumKreiranja the datumKreiranja to set
	 */
	public void setDatumKreiranja(LocalDateTime datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	/**
	 * @return the aranzman
	 */
	public Aranzman getAranzman() {
		return aranzman;
	}

	/**
	 * @param aranzman the aranzman to set
	 */
	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}







}