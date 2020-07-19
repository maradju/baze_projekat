/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marija
 */
public class Prevoznik {
    private int pib;
    private String nazivPrevoznika;
    private Drzava drzavaID;
    private Mesto mestoID;

    public Prevoznik() {
    }

    public Prevoznik(int pib, String nazivPrevoznika, Drzava drzavaID, Mesto mestoID) {
        this.pib = pib;
        this.nazivPrevoznika = nazivPrevoznika;
        this.drzavaID = drzavaID;
        this.mestoID = mestoID;
    }

    public Mesto getMestoID() {
        return mestoID;
    }

    public void setMestoID(Mesto mestoID) {
        this.mestoID = mestoID;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getNazivPrevoznika() {
        return nazivPrevoznika;
    }

    public void setNazivPrevoznika(String nazivPrevoznika) {
        this.nazivPrevoznika = nazivPrevoznika;
    }

    public Drzava getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(Drzava drzavaID) {
        this.drzavaID = drzavaID;
    }

    @Override
    public String toString() {
        return nazivPrevoznika;
    }

    
 
    
}
