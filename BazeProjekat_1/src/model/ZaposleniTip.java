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
public class ZaposleniTip {
    private Zaposleni zaposleniID;
    private String podaciOZaposlenom;

    public ZaposleniTip() {
    }

    public ZaposleniTip(Zaposleni zaposleniID, String podaciOZaposlenom) {
        this.zaposleniID = zaposleniID;
        this.podaciOZaposlenom = podaciOZaposlenom;
    }

    public String getPodaciOZaposlenom() {
        return podaciOZaposlenom;
    }

    public void setPodaciOZaposlenom(String podaciOZaposlenom) {
        this.podaciOZaposlenom = podaciOZaposlenom;
    }

    public Zaposleni getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Zaposleni zaposleniID) {
        this.zaposleniID = zaposleniID;
    }
    
}
