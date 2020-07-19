/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Marija
 */
public class Cena {
    private int proizvodID;
    private int cenaID;
    private Date datum;
    private double Iznos;

    public Cena() {
    }

    public Cena(int proizvodID, int cenaID, Date datum, double Iznos) {
        this.proizvodID = proizvodID;
        this.cenaID = cenaID;
        this.datum = datum;
        this.Iznos = Iznos;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public int getCenaID() {
        return cenaID;
    }

    public void setCenaID(int cenaID) {
        this.cenaID = cenaID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getIznos() {
        return Iznos;
    }

    public void setIznos(double Iznos) {
        this.Iznos = Iznos;
    }

   
    
    
}
