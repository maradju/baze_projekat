/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

/**
 *
 * @author Marija
 */
public class Proizvod {
    private int proizvodID;
    private String opis;
    private String naziv;
    private ModelProizvoda modelID;
    private double AktuelnaCena;
    private Cena cena;

    public Proizvod() {
    }

    public Proizvod(int proizvodID, String opis, String naziv, ModelProizvoda modelID, double AktuelnaCena, Cena cena) {
        this.proizvodID = proizvodID;
        this.opis = opis;
        this.naziv = naziv;
        this.modelID = modelID;
        this.AktuelnaCena = AktuelnaCena;
        this.cena = cena;
    }

 
    
   
    @Override
    public String toString() {
        return naziv ;
    }

    public Cena getCena() {
        return cena;
    }

    public void setCena(Cena cena) {
        this.cena = cena;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ModelProizvoda getModelID() {
        return modelID;
    }

    public void setModelID(ModelProizvoda modelID) {
        this.modelID = modelID;
    }

    public double getAktuelnaCena() {
        return AktuelnaCena;
    }

    public void setAktuelnaCena(double AktuelnaCena) {
        this.AktuelnaCena = AktuelnaCena;
    }
    
        
    
    
    
}
