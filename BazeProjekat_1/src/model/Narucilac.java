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
public class Narucilac {
    private int pib;
    private String nazivNarucioca;
    private String email;
    private Drzava drzavaID;
    private Mesto mestoID;

    public Narucilac() {
    }

    public Narucilac(int pib, String nazivNarucioca, String email, Drzava drzavaID, Mesto mestoID) {
        this.pib = pib;
        this.nazivNarucioca = nazivNarucioca;
        this.email = email;
        this.drzavaID = drzavaID;
        this.mestoID = mestoID;
    }

    public Mesto getMestoID() {
        return mestoID;
    }

    public void setMestoID(Mesto mestoID) {
        this.mestoID = mestoID;
    }

    public Drzava getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(Drzava drzavaID) {
        this.drzavaID = drzavaID;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getNazivNarucioca() {
        return nazivNarucioca;
    }

    public void setNazivNarucioca(String nazivNarucioca) {
        this.nazivNarucioca = nazivNarucioca;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nazivNarucioca;
    }
    
    

 
    
}
