package modeli.tabele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.STATUS;
import model.StavkaReklamacije;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marija
 */
public class ModelTabeleStavkeReklamacije extends AbstractTableModel {
    String kolone[] = {"Stavka ID", "ReklamacijaID", "OpisStavke", "ProzivodID", "OpisReklamacije"};
    List<StavkaReklamacije> stavkeReklamacije;

    public ModelTabeleStavkeReklamacije() {
        stavkeReklamacije = new ArrayList<>();
    }

    public ModelTabeleStavkeReklamacije(List<StavkaReklamacije> stavkeReklamacije) {
        this.stavkeReklamacije = stavkeReklamacije;
    }

    @Override
    public int getRowCount() {
        return stavkeReklamacije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaReklamacije stavkaReklamacije = stavkeReklamacije.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stavkaReklamacije.getStavkaReklamacijeID();
            case 1:
                return stavkaReklamacije.getReklamacijaID();                
            case 2:
                return stavkaReklamacije.getOpisStavke();
            case 3:
                return stavkaReklamacije.getProzivodID();
            case 4:
                return stavkaReklamacije.getOpisReklamacije();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavkuReklamacije(StavkaReklamacije sr) {
        stavkeReklamacije.add(sr);
        osveziTabelustavkeReklamacije();
    }

    public void obrisiStavkuReklamacije(int index) {
        stavkeReklamacije.remove(index);
        osveziTabelustavkeReklamacije();
    }

    public StavkaReklamacije vratiStavkuReklamacije(int index) {
        return stavkeReklamacije.get(index);
    }

    public StavkaReklamacije obrisiStavkaReklamacije(int index){
        return stavkeReklamacije.remove(index);
    }
    public void osveziTabelustavkeReklamacije() {
        fireTableDataChanged();
    }


    public List<StavkaReklamacije> vratiStavke() {
        return stavkeReklamacije;
    }
        public void postaviStatus(STATUS status, int index) {
        stavkeReklamacije.get(index).setStatus(status);
        osveziTabelustavkeReklamacije();
    }
    public void izmeniStavkuReklamacije(StavkaReklamacije sr, int selectedRow) {
        stavkeReklamacije.get(selectedRow).setStavkaReklamacijeID(sr.getStavkaReklamacijeID());
        stavkeReklamacije.get(selectedRow).setReklamacijaID(sr.getReklamacijaID());
        stavkeReklamacije.get(selectedRow).setOpisStavke(sr.getOpisStavke());
        stavkeReklamacije.get(selectedRow).setProzivodID(sr.getProzivodID());
        stavkeReklamacije.get(selectedRow).setOpisReklamacije(sr.getOpisReklamacije());
        
        if(sr.getStatus()!=null) {
            postaviStatus(sr.getStatus(), selectedRow);
        }
        osveziTabelustavkeReklamacije();
    }
    

}
