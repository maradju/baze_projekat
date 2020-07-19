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
public class Mesto {
    private int mestoID;
    private String nazivMesta;
    private Drzava drzavaID;


    public Mesto() {
    }

    public Mesto(int mestoID, String nazivMesta, Drzava drzavaID, String nazivDrzave) {
        this.mestoID = mestoID;
        this.nazivMesta = nazivMesta;
        this.drzavaID = drzavaID;

    }


    public int getMestoID() {
        return mestoID;
    }

    public void setMestoID(int mestoID) {
        this.mestoID = mestoID;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public Drzava getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(Drzava drzavaID) {
        this.drzavaID = drzavaID;
    }

    @Override
    public String toString() {
        return  nazivMesta;
    }
    
    
    
 
    
         
}
