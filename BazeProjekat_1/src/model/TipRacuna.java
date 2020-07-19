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
public class TipRacuna {
    private int tipRacunaID;
    private String nazivTipaRac;

    public TipRacuna() {
    }

    public TipRacuna(int tipRacunaID, String nazivTipaRac) {
        this.tipRacunaID = tipRacunaID;
        this.nazivTipaRac = nazivTipaRac;
    }

    public String getNazivTipaRac() {
        return nazivTipaRac;
    }

    public void setNazivTipaRac(String nazivTipaRac) {
        this.nazivTipaRac = nazivTipaRac;
    }

    public int getTipRacunaID() {
        return tipRacunaID;
    }

    public void setTipRacunaID(int tipRacunaID) {
        this.tipRacunaID = tipRacunaID;
    }
    
    
}
