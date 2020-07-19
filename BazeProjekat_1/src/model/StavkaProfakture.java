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
public class StavkaProfakture {
    private int stavkaProfaktureID;
    private int profakturaID;
    private int kolicina;
    private Proizvod proizvodID;
    private STATUS status;

    public StavkaProfakture() {
    }

    public StavkaProfakture(int stavkaProfaktureID, int profakturaID, int kolicina, Proizvod proizvodID, STATUS status) {
        this.stavkaProfaktureID = stavkaProfaktureID;
        this.profakturaID = profakturaID;
        this.kolicina = kolicina;
        this.proizvodID = proizvodID;
        this.status = status;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getStavkaProfaktureID() {
        return stavkaProfaktureID;
    }

    public void setStavkaProfaktureID(int stavkaProfaktureID) {
        this.stavkaProfaktureID = stavkaProfaktureID;
    }

    public int getProfakturaID() {
        return profakturaID;
    }

    public void setProfakturaID(int profakturaID) {
        this.profakturaID = profakturaID;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Proizvod getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(Proizvod proizvodID) {
        this.proizvodID = proizvodID;
    }

    

    
    
}
