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
public class Profaktura {
    private int profakturaID;
    private Date datumPredaje;
    private Zaposleni zaposleniID;
    private Prevoznik PIBPrevoznika;
    private Narucilac PIBNarucioca;
    private Racun racunID;
    private double ukupno;
    private List<StavkaProfakture> stavkeProfakture;

    public Profaktura() {
    }

    public Profaktura(int profakturaID, Date datumPredaje, Zaposleni zaposleniID, Prevoznik PIBPrevoznika, Narucilac PIBNarucioca, Racun racunID, double ukupno, List<StavkaProfakture> stavkeProfakture) {
        this.profakturaID = profakturaID;
        this.datumPredaje = datumPredaje;
        this.zaposleniID = zaposleniID;
        this.PIBPrevoznika = PIBPrevoznika;
        this.PIBNarucioca = PIBNarucioca;
        this.racunID = racunID;
        this.ukupno = ukupno;
        this.stavkeProfakture = stavkeProfakture;
    }

    public int getProfakturaID() {
        return profakturaID;
    }

    public void setProfakturaID(int profakturaID) {
        this.profakturaID = profakturaID;
    }

    public Date getDatumPredaje() {
        return datumPredaje;
    }

    public void setDatumPredaje(Date datumPredaje) {
        this.datumPredaje = datumPredaje;
    }

    public Zaposleni getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Zaposleni zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public Prevoznik getPIBPrevoznika() {
        return PIBPrevoznika;
    }

    public void setPIBPrevoznika(Prevoznik PIBPrevoznika) {
        this.PIBPrevoznika = PIBPrevoznika;
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

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    public List<StavkaProfakture> getStavkeProfakture() {
        return stavkeProfakture;
    }

    public void setStavkeProfakture(List<StavkaProfakture> stavkeProfakture) {
        this.stavkeProfakture = stavkeProfakture;
    }

    @Override
    public String toString() {
        return profakturaID+"";
    }

    
    
    
}
