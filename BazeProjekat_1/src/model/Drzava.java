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
public class Drzava {
    private int drzavaID;
    private String nazivDrzave;

    public Drzava() {
    }

    public Drzava(int drzavaID, String nazivDrzave) {
        this.drzavaID = drzavaID;
        this.nazivDrzave = nazivDrzave;
    }

    public String getNazivDrzave() {
        return nazivDrzave;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
    }

    public int getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(int drzavaID) {
        this.drzavaID = drzavaID;
    }

    @Override
    public String toString() {
        return nazivDrzave;
    }
    
    
}
