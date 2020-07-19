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
public class StavkaReklamacije {
    private int stavkaReklamacijeID;
    private int reklamacijaID;
    private String opisStavke;
    private Proizvod prozivodID;
    private String opisReklamacije;
    private STATUS status;

    public StavkaReklamacije() {
    }

    public StavkaReklamacije(int stavkaReklamacijeID, int reklamacijaID, String opisStavke, Proizvod prozivodID, String opisReklamacije, STATUS status) {
        this.stavkaReklamacijeID = stavkaReklamacijeID;
        this.reklamacijaID = reklamacijaID;
        this.opisStavke = opisStavke;
        this.prozivodID = prozivodID;
        this.opisReklamacije = opisReklamacije;
        this.status = status;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getStavkaReklamacijeID() {
        return stavkaReklamacijeID;
    }

    public void setStavkaReklamacijeID(int stavkaReklamacijeID) {
        this.stavkaReklamacijeID = stavkaReklamacijeID;
    }

    public int getReklamacijaID() {
        return reklamacijaID;
    }

    public void setReklamacijaID(int reklamacijaID) {
        this.reklamacijaID = reklamacijaID;
    }

    public String getOpisStavke() {
        return opisStavke;
    }

    public void setOpisStavke(String opisStavke) {
        this.opisStavke = opisStavke;
    }

    public Proizvod getProzivodID() {
        return prozivodID;
    }

    public void setProzivodID(Proizvod prozivodID) {
        this.prozivodID = prozivodID;
    }

    public String getOpisReklamacije() {
        return opisReklamacije;
    }

    public void setOpisReklamacije(String opisReklamacije) {
        this.opisReklamacije = opisReklamacije;
    }

    


    
}
