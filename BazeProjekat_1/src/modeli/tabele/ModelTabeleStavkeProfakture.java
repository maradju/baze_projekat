package modeli.tabele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.STATUS;
import model.StavkaProfakture;
import model.StavkaProfakture;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marija
 */
public class ModelTabeleStavkeProfakture extends AbstractTableModel {

    String kolone[] = {"Redni Broj Stavke", "Profaktura ID", "Količina", "Proizvod"};
    List<StavkaProfakture> stavkeProfakture;

    public ModelTabeleStavkeProfakture() {
        stavkeProfakture = new ArrayList<>();
    }

    public ModelTabeleStavkeProfakture(List<StavkaProfakture> stavkeProfakture) {
        this.stavkeProfakture = stavkeProfakture;
    }

    @Override
    public int getRowCount() {
        return stavkeProfakture.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaProfakture StavkaProfakture = stavkeProfakture.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return StavkaProfakture.getStavkaProfaktureID();
            case 1:
                return StavkaProfakture.getProfakturaID();
            case 2:
                return StavkaProfakture.getKolicina();
            case 3:
                return StavkaProfakture.getProizvodID().toString();

            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavkuProfakture(StavkaProfakture sp) {
        stavkeProfakture.add(sp);
        osveziTabeluStavkeProfakture();
    }

    public void obrisiStavkuProfakture(int index) {
        stavkeProfakture.remove(index);
        osveziTabeluStavkeProfakture();
    }

    public StavkaProfakture vratiStavkuProfakture(int index) {
        return stavkeProfakture.get(index);
    }

    public void osveziTabeluStavkeProfakture() {
        fireTableDataChanged();
    }

        public void postaviStatus(STATUS status, int index) {
        stavkeProfakture.get(index).setStatus(status);
        osveziTabeluStavkeProfakture();
    }
    public List<StavkaProfakture> vratiStavke() {
        return stavkeProfakture;
    }

    public void izmeniStavkuProfakture(StavkaProfakture sp, int selectedRow) {
        stavkeProfakture.get(selectedRow).setStavkaProfaktureID(sp.getStavkaProfaktureID());
        stavkeProfakture.get(selectedRow).setProfakturaID(sp.getProfakturaID());
        stavkeProfakture.get(selectedRow).setKolicina(sp.getKolicina());
        stavkeProfakture.get(selectedRow).setProizvodID(sp.getProizvodID());
        
        if(sp.getStatus()!=null) {
            postaviStatus(sp.getStatus(), selectedRow);
        }
        osveziTabeluStavkeProfakture();
    }
    

}
