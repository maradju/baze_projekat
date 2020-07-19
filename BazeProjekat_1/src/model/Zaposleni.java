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
public class Zaposleni {
    private int zaposleniID;
    private String nazivZap;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniID, String nazivZap) {
        this.zaposleniID = zaposleniID;
        this.nazivZap = nazivZap;
    }

    public String getNazivZap() {
        return nazivZap;
    }

    public void setNazivZap(String nazivZap) {
        this.nazivZap = nazivZap;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    @Override
    public String toString() {
        return nazivZap;//To change body of generated methods, choose Tools | Templates.
    }
    
    
  
}
