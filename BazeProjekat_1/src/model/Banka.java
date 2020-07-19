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
public class Banka {
    private int bankaID;
    private String nazivBanke;
    private Mesto mestoID;
    private Drzava drzavaID;

    public Banka() {
    }

    public Banka(int bankaID, String nazivBanke, Mesto mestoID, Drzava drzavaID) {
        this.bankaID = bankaID;
        this.nazivBanke = nazivBanke;
        this.mestoID = mestoID;
        this.drzavaID = drzavaID;
    }

    public int getBankaID() {
        return bankaID;
    }

    public void setBankaID(int bankaID) {
        this.bankaID = bankaID;
    }

    public String getNazivBanke() {
        return nazivBanke;
    }

    public void setNazivBanke(String nazivBanke) {
        this.nazivBanke = nazivBanke;
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
    
   
 
}
