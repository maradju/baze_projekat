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
public class ModelProizvoda {
    private int modelID;
    private String memorija;
    private String boja;
    private String opis;
    private String nazivModelaProizvoda;

    public ModelProizvoda() {
    }

    public ModelProizvoda(int modelID, String memorija, String boja, String opis, String nazivModelaProizvoda) {
        this.modelID = modelID;
        this.memorija = memorija;
        this.boja = boja;
        this.opis = opis;
        this.nazivModelaProizvoda = nazivModelaProizvoda;
    }

    public String getNazivModelaProizvoda() {
        return nazivModelaProizvoda;
    }

    public void setNazivModelaProizvoda(String nazivModelaProizvoda) {
        this.nazivModelaProizvoda = nazivModelaProizvoda;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getMemorija() {
        return memorija;
    }

    public void setMemorija(String memorija) {
        this.memorija = memorija;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return  nazivModelaProizvoda;
    }
    
    
    
           
}
