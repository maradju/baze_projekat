/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import dbb.DBBroker;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Narucilac;
import model.Prevoznik;
import model.Profaktura;
import model.Racun;
import model.STATUS;
import model.StavkaProfakture;
import model.Zaposleni;
import modeli.tabele.ModelTabeleStavkeProfakture;
import modeli.tabele.ModelTabeleStavkeReklamacije;


/**
 *
 * @author Marija
 */
public class FProfaktura extends javax.swing.JFrame {
    
    
    DBBroker dbb = new DBBroker();
    private Operacija operacija;
    private List<StavkaProfakture> stavkeProfakture;
    
    public Operacija getOperacija() {
        return operacija;
    }

    public List<StavkaProfakture> getStavkeProfakture() {
        return stavkeProfakture;
    }

    public JTable getTblStavkaProfakture() {
        return tblStavkaProfakture;
    }
    
    

    public FProfaktura(String flag) {
        initComponents();
        
        tblStavkaProfakture.setModel(new ModelTabeleStavkeProfakture());
        if (flag.equals("kreiraj")) {
            operacija = Operacija.KREIRANJE;
            stavkeProfakture = new ArrayList<>();
            btnPronadji.setVisible(false);
            btnIzmeni.setVisible(false);
            btnObrisi.setVisible(false);
            jtfUkupno.setEnabled(true);
            popuniComboBoxZaposleni();
            popuniComboBoxNarucilac();
            popuniComboBoxPrevoznik();
            popuniComboBoxRacun();
        }
        if (flag.equals("izmeni")) {
            operacija = Operacija.IZMENA;
            btnDodajStavku.setEnabled(false);
            btnIzmeniStavku.setEnabled(false);
            btnObrisiStavku.setEnabled(false);
            btnSacuvaj.setVisible(false);
            btnObrisi.setEnabled(false);
            btnIzmeni.setEnabled(false);
            jtfUkupno.setEnabled(true);
            jtfDatumPredaje.setEnabled(false);
            cmbZaposleni.setEnabled(false);
        }
    }

    private void popuniComboBoxZaposleni() {
        try {
            cmbZaposleni.removeAllItems();
            dbb.uspostaviKonekciju();
            List<Zaposleni> listaZaposleni = dbb.vratiSveZaposlene();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (Zaposleni zaposleni : listaZaposleni) {
                cmbZaposleni.addItem(zaposleni);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }
        private void popuniComboBoxPrevoznik() {
        try {
            cmbPrevoznik.removeAllItems();
            dbb.uspostaviKonekciju();
            List<Prevoznik> lista = dbb.vratiSvePrevoznike();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (Prevoznik p : lista) {
                cmbPrevoznik.addItem(p);
            }
            System.out.println("lala" + lista);
            ComboBoxModel model = new DefaultComboBoxModel(lista.toArray());
            cmbPrevoznik.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }
        private void popuniComboBoxNarucilac() {
        try {
            cmbNarucilac.removeAllItems();
            dbb.uspostaviKonekciju();
            List<Narucilac> lista = dbb.vratiSveNarucioce();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (Narucilac narucilac : lista) {
                cmbNarucilac.addItem(narucilac);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }
        
    private void popuniComboBoxRacun() {
        try {
            cmbRacun.removeAllItems();
            dbb.uspostaviKonekciju();
            
            List<Racun> lista = dbb.vratiSveRacune();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (Racun racuni : lista) {
                cmbZaposleni.addItem(racuni);
                System.out.println(racuni.getRacunID());
            }
            ComboBoxModel model = new DefaultComboBoxModel(lista.toArray());
            cmbRacun.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }

    void izmeniStavkuProfakture(StavkaProfakture sp, int selectedRow) {
        for (StavkaProfakture StavkaProfakture : stavkeProfakture) {
            if (StavkaProfakture.getStavkaProfaktureID()== sp.getStavkaProfaktureID()) {
                StavkaProfakture.setProfakturaID(sp.getProfakturaID());
                StavkaProfakture.setKolicina(sp.getKolicina());
                StavkaProfakture.setProizvodID(sp.getProizvodID());
                if (sp.getStatus() != null) {
                    StavkaProfakture.setStatus(sp.getStatus());
                }
            }
        }
    }

    String vratiProfakturaID() {
        String profakturaID = jtfProfakturaID1.getText();
        return profakturaID;
    }
    
    public enum Operacija {
        KREIRANJE,
        IZMENA
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfProfakturaID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfDatumPredaje = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbZaposleni = new javax.swing.JComboBox<Object>();
        btnPronadji = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavkaProfakture = new javax.swing.JTable();
        btnDodajStavku = new javax.swing.JButton();
        btnIzmeniStavku = new javax.swing.JButton();
        btnObrisiStavku = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmbNarucilac = new javax.swing.JComboBox<Object>();
        jLabel10 = new javax.swing.JLabel();
        cmbPrevoznik = new javax.swing.JComboBox<Object>();
        jLabel11 = new javax.swing.JLabel();
        cmbRacun = new javax.swing.JComboBox<Object>();
        jtfUkupno = new javax.swing.JTextField();
        jtfProfakturaID1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Broj Profakture:");

        jLabel2.setText("Datum predaje");

        jLabel3.setText("Ukupno");

        jLabel7.setText("Zaposleni:");

        btnPronadji.setText("Pronadji");
        btnPronadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiActionPerformed(evt);
            }
        });

        tblStavkaProfakture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStavkaProfakture);

        btnDodajStavku.setText("Dodaj Stavku");
        btnDodajStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajStavkuActionPerformed(evt);
            }
        });

        btnIzmeniStavku.setText("Izmeni Stavku");
        btnIzmeniStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniStavkuActionPerformed(evt);
            }
        });

        btnObrisiStavku.setText("Obrisi Stavku");
        btnObrisiStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiStavkuActionPerformed(evt);
            }
        });

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        jLabel9.setText("Narucilac");

        jLabel10.setText("Prevoznik");

        jLabel11.setText("Racun");

        jtfUkupno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfUkupnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbNarucilac, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cmbRacun, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jtfUkupno, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(166, 166, 166)
                                    .addComponent(cmbPrevoznik, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jtfProfakturaID1)
                                        .addComponent(jtfDatumPredaje, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnPronadji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(32, 32, 32)
                                            .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166)
                                .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDodajStavku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnObrisiStavku, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(btnIzmeniStavku, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(btnObrisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfProfakturaID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfDatumPredaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jtfUkupno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbNarucilac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(cmbRacun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbPrevoznik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPronadji)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDodajStavku)
                        .addGap(34, 34, 34)
                        .addComponent(btnIzmeniStavku)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnObrisiStavku))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSacuvaj)
                    .addComponent(btnIzmeni)
                    .addComponent(btnObrisi))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnObrisiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiStavkuActionPerformed
                if (tblStavkaProfakture.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nije selektovana stavka za brisanje", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (operacija == Operacija.IZMENA) {
            int indeks = -1;
            for (int i = 0; i < stavkeProfakture.size(); i++) {
                if (stavkeProfakture.get(i).getStavkaProfaktureID()== ((ModelTabeleStavkeProfakture) tblStavkaProfakture.getModel()).vratiStavkuProfakture(tblStavkaProfakture.getSelectedRow()).getStavkaProfaktureID()) {
                    if (stavkeProfakture.get(i).getStatus() == null || stavkeProfakture.get(i).getStatus() == STATUS.IZMENI) {

                        stavkeProfakture.get(i).setStatus(STATUS.OBRISI);
                        ((ModelTabeleStavkeProfakture) tblStavkaProfakture.getModel()).obrisiStavkuProfakture(tblStavkaProfakture.getSelectedRow());
                        break;
                    } else {
                        if (stavkeProfakture.get(i).getStatus() != STATUS.OBRISI) {
                            indeks = i;
                            break;
                        }
                    }
                }
            }
            if (indeks > -1) {
                stavkeProfakture.remove(indeks);
                ((ModelTabeleStavkeProfakture) tblStavkaProfakture.getModel()).obrisiStavkuProfakture(tblStavkaProfakture.getSelectedRow());
            }
        }

        if (operacija == Operacija.KREIRANJE) {
            stavkeProfakture.remove(tblStavkaProfakture.getSelectedRow());
            ((ModelTabeleStavkeProfakture) tblStavkaProfakture.getModel()).obrisiStavkuProfakture(tblStavkaProfakture.getSelectedRow());
        }

        stavkeProfakture.forEach((sp) -> {
            System.out.println(sp.getStavkaProfaktureID()+ " " + sp.getStatus());
        });
        


    }//GEN-LAST:event_btnObrisiStavkuActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        try {
            String ProfakturaID = jtfProfakturaID1.getText().trim();
            if (ProfakturaID.isEmpty() || jtfDatumPredaje.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!ProfakturaID.matches("[1-9][0-9]*")) {
                JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Profaktura p = new Profaktura();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            p.setProfakturaID(Integer.parseInt(ProfakturaID));
            p.setDatumPredaje(sdf.parse(jtfDatumPredaje.getText()));
            p.setZaposleniID((Zaposleni) cmbZaposleni.getSelectedItem());
            p.setPIBPrevoznika((Prevoznik) cmbPrevoznik.getSelectedItem());
            p.setPIBNarucioca((Narucilac) cmbNarucilac.getSelectedItem());
            p.setStavkeProfakture(((ModelTabeleStavkeProfakture) tblStavkaProfakture.getModel()).vratiStavke());
            p.setRacunID((Racun) cmbRacun.getSelectedItem());
           
            try {
                dbb.uspostaviKonekciju();
                dbb.ubaciProfakturu(p);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno ubacena profaktura i njene stavke", "", JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno ubacivanje profakture i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnDodajStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajStavkuActionPerformed
        FStavkaProfakture forma;
        forma = new FStavkaProfakture("kreiraj", this);
        forma.setLocationRelativeTo(null);
        forma.setVisible(true);
    }//GEN-LAST:event_btnDodajStavkuActionPerformed

    private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiActionPerformed
        try {
            if (jtfProfakturaID1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!jtfProfakturaID1.getText().matches("[1-9][0-9]*")) {
                JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            dbb.uspostaviKonekciju();
            
            Profaktura p = dbb.ucitajProfakturu(Integer.parseInt(jtfProfakturaID1.getText()));
            if (p == null) {
                JOptionPane.showMessageDialog(null, "Nije pronadjena profaktura sa datim rb", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            jtfDatumPredaje.setText(sdf.format(p.getDatumPredaje()));
            jtfUkupno.setText(p.getUkupno() + "");
            
            popuniComboBoxZaposleni();
            for (int i = 0; i < cmbZaposleni.getItemCount(); i++) {
                if (cmbZaposleni.getItemAt(i).toString().equals(p.getZaposleniID().getNazivZap())) {
                    System.out.println("pronasao zaposlenog");
                    cmbZaposleni.setSelectedItem(cmbZaposleni.getItemAt(i));
                }
            }
            popuniComboBoxNarucilac();
            for (int i = 0; i < cmbNarucilac.getItemCount(); i++) {
                if (cmbNarucilac.getItemAt(i).toString().equals(p.getPIBNarucioca().getNazivNarucioca())) {
                    System.out.println("pronasao zahtev");
                    cmbNarucilac.setSelectedItem(cmbNarucilac.getItemAt(i));
                }
            }
            popuniComboBoxPrevoznik();
            for (int i = 0; i < cmbPrevoznik.getItemCount(); i++) {
                if (cmbPrevoznik.getItemAt(i).toString().equals(p.getPIBPrevoznika().getNazivPrevoznika())) {
                    System.out.println("pronasao zahtev");
                    cmbPrevoznik.setSelectedItem(cmbPrevoznik.getItemAt(i));
                }
            }
            popuniComboBoxRacun();
            for (int i = 0; i < cmbRacun.getItemCount(); i++) {
                if (cmbRacun.getItemAt(i).toString().equals(p.getRacunID().getTipRacuna())) {
                    System.out.println("pronasao zahtev");
                    cmbRacun.setSelectedItem(cmbRacun.getItemAt(i));
                }
            }
            stavkeProfakture = new ArrayList<>();
            for (StavkaProfakture sp : p.getStavkeProfakture()) {
                stavkeProfakture.add(sp);
            }
            
            tblStavkaProfakture.setModel(new ModelTabeleStavkeProfakture(p.getStavkeProfakture()));
            jtfProfakturaID1.setEnabled(false);
            btnPronadji.setEnabled(false);
            jtfDatumPredaje.setEnabled(true);
            jtfUkupno.setEnabled(true);
            cmbZaposleni.setEnabled(true);
            btnIzmeni.setEnabled(true);
            btnObrisi.setEnabled(true);
            btnIzmeniStavku.setEnabled(true);
            btnObrisiStavku.setEnabled(true);
            btnDodajStavku.setEnabled(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešna pretraga profakture i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPronadjiActionPerformed

    private void btnIzmeniStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniStavkuActionPerformed
        if (tblStavkaProfakture.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nije selektovana stavka za brisanje", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FStavkaProfakture forma = new FStavkaProfakture("izmeni", this);
        forma.setLocationRelativeTo(null);
        forma.setVisible(true);
    }//GEN-LAST:event_btnIzmeniStavkuActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        try {
            String profakturaID = jtfProfakturaID1.getText().trim();
            if (profakturaID.isEmpty() || jtfDatumPredaje.getText().isEmpty() || jtfUkupno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Profaktura p = new Profaktura();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            p.setProfakturaID(Integer.parseInt(profakturaID));
            p.setDatumPredaje(sdf.parse(jtfDatumPredaje.getText()));
            p.setZaposleniID((Zaposleni) cmbZaposleni.getSelectedItem());
            p.setPIBPrevoznika((Prevoznik) cmbPrevoznik.getSelectedItem());
            p.setPIBNarucioca((Narucilac) cmbNarucilac.getSelectedItem());
            p.setRacunID((Racun) cmbRacun.getSelectedItem());
            p.setStavkeProfakture(getStavkeProfakture());

            try {
                dbb.uspostaviKonekciju();
                dbb.izmeniProfakturu(p);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjena profaktura", "Izmena", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Greska pri izmeni profakture\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        String brojProfakture = jtfProfakturaID1.getText().trim();

        if (brojProfakture.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Niste popunili broj profakture", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            dbb.uspostaviKonekciju();
            boolean uspesno = dbb.obrisiProfakturu(Integer.parseInt(brojProfakture));
            if (uspesno) {
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno obrisana profaktura i njene stavke", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno brisanje profakture i njenih stavki", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno brisanje profakture i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FProfaktura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void jtfUkupnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfUkupnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfUkupnoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniStavku;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiStavku;
    private javax.swing.JButton btnPronadji;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox<Object> cmbNarucilac;
    private javax.swing.JComboBox<Object> cmbPrevoznik;
    private javax.swing.JComboBox<Object> cmbRacun;
    private javax.swing.JComboBox<Object> cmbZaposleni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtfDatumPredaje;
    private javax.swing.JTextField jtfProfakturaID;
    private javax.swing.JTextField jtfProfakturaID1;
    private javax.swing.JTextField jtfUkupno;
    private javax.swing.JTable tblStavkaProfakture;
    // End of variables declaration//GEN-END:variables
}
