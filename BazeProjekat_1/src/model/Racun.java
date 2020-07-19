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
public class Racun {
    private int racunID;
    private TipRacuna tipRacunaID;
    private int brojRacuna;
    private String tipRacuna;

    public Racun() {
    }

    public Racun(int racunID, TipRacuna tipRacunaID, int brojRacuna, String tipRacuna) {
        this.racunID = racunID;
        this.tipRacunaID = tipRacunaID;
        this.brojRacuna = brojRacuna;
        this.tipRacuna = tipRacuna;
    }

    public String getTipRacuna() {
        return tipRacuna;
    }

    public void setTipRacuna(String tipRacuna) {
        this.tipRacuna = tipRacuna;
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public TipRacuna getTipRacunaID() {
        return tipRacunaID;
    }

    public void setTipRacunaID(TipRacuna tipRacunaID) {
        this.tipRacunaID = tipRacunaID;
    }

    public int getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    @Override
    public String toString() {
        return racunID+"";
    }
    
    
    
            
}
