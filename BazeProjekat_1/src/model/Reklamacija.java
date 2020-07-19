/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.Date;
import java.util.List;

/**
 *
 * @author Marija
 */
public class Reklamacija {
    private int reklamacijaID;
    private Date datumKupovine;
    private Date datumOdobravanja;
    private Date datumPostavljanja;
    private Zaposleni zaposleniid;
    private String izjava;
    private Narucilac PIBNarucioca;
    private Racun racunID;
    private Profaktura profakturaID;
    private String opisReklamacije;
    private List<StavkaReklamacije> stavkeReklamacije;

    public Reklamacija() {
    }

    public Reklamacija(int reklamacijaID, Date datumKupovine, Date datumOdobravanja, Date datumPostavljanja, Zaposleni zaposleniid, String izjava, Narucilac PIBNarucioca, Racun racunID, Profaktura profakturaID, String opisReklamacije, List<StavkaReklamacije> stavkeReklamacije) {
        this.reklamacijaID = reklamacijaID;
        this.datumKupovine = datumKupovine;
        this.datumOdobravanja = datumOdobravanja;
        this.datumPostavljanja = datumPostavljanja;
        this.zaposleniid = zaposleniid;
        this.izjava = izjava;
        this.PIBNarucioca = PIBNarucioca;
        this.racunID = racunID;
        this.profakturaID = profakturaID;
        this.opisReklamacije = opisReklamacije;
        this.stavkeReklamacije = stavkeReklamacije;
    }

    public int getReklamacijaID() {
        return reklamacijaID;
    }

    public void setReklamacijaID(int reklamacijaID) {
        this.reklamacijaID = reklamacijaID;
    }

    public Date getDatumKupovine() {
        return datumKupovine;
    }

    public void setDatumKupovine(Date datumKupovine) {
        this.datumKupovine = datumKupovine;
    }

    public Date getDatumOdobravanja() {
        return datumOdobravanja;
    }

    public void setDatumOdobravanja(Date datumOdobravanja) {
        this.datumOdobravanja = datumOdobravanja;
    }

    public Date getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja(Date datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }

    public Zaposleni getZaposleniid() {
        return zaposleniid;
    }

    public void setZaposleniid(Zaposleni zaposleniid) {
        this.zaposleniid = zaposleniid;
    }

    public String getIzjava() {
        return izjava;
    }

    public void setIzjava(String izjava) {
        this.izjava = izjava;
    }

    public Narucilac getPIBNarucioca() {
        return PIBNarucioca;
    }

    public void setPIBNarucioca(Narucilac PIBNarucioca) {
        this.PIBNarucioca = PIBNarucioca;
    }

    public Racun getRacunID() {
        return racunID;
    }

    public void setRacunID(Racun racunID) {
        this.racunID = racunID;
    }

    public Profaktura getProfakturaID() {
        return profakturaID;
    }

    public void setProfakturaID(Profaktura profakturaID) {
        this.profakturaID = profakturaID;
    }

    public String getOpisReklamacije() {
        return opisReklamacije;
    }

    public void setOpisReklamacije(String opisReklamacije) {
        this.opisReklamacije = opisReklamacije;
    }

    public List<StavkaReklamacije> getStavkeReklamacije() {
        return stavkeReklamacije;
    }

    public void setStavkeReklamacije(List<StavkaReklamacije> stavkeReklamacije) {
        this.stavkeReklamacije = stavkeReklamacije;
    }
    
    
    
        
         

}
