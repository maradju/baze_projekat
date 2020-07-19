/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import dbb.DBBroker;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cena;
import model.ModelProizvoda;
import model.Proizvod;


/**
 *
 * @author Marija
 */
public class FProizvod extends javax.swing.JFrame {

    DBBroker dbb = new DBBroker();
    private Operacija operacija;

    public Operacija getOperacija() {
        return operacija;
    }


    public enum Operacija {
        KREIRANJE,
        IZMENA
    }

    public FProizvod(String flag) {
        initComponents();

        if (flag.equals("kreiraj")) {
            operacija = Operacija.KREIRANJE;
            btnPronadji.setVisible(false);
            btnIzmeni.setVisible(false);
            btnObrisi.setVisible(false);
            popuniComboBoxModel();
        }
        if (flag.equals("izmeni")) {
            operacija = Operacija.IZMENA;
            btnSacuvaj.setVisible(false);
            btnIzmeni.setEnabled(false);
            btnObrisi.setEnabled(false);
            jtfOpis.setEnabled(false);
            cmbModel.setEnabled(false);
        }
    }
    private void popuniComboBoxModel() {
        try {
            cmbModel.removeAllItems();
            dbb.uspostaviKonekciju();
            List<ModelProizvoda> lista = dbb.vratiSveModele();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (ModelProizvoda model : lista) {
                cmbModel.addItem(model);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FProizvod.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfOpis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSacuvaj = new javax.swing.JButton();
        btnPronadji = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jtfNaziv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfAktuelnaCena = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jtfCenaID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfDatumID = new javax.swing.JTextField();
        jtfIznos = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnIzmeniCenu = new javax.swing.JButton();
        jtfProizvod1 = new javax.swing.JTextField();
        cmbModel = new javax.swing.JComboBox();
        btnIzmeniLepo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ProizvodID");

        jLabel2.setText("Opis");

        jLabel3.setText("Model");

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        btnPronadji.setText("Pronadji");
        btnPronadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni aktuelnu cenu");
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

        jLabel4.setText("Naziv");

        jLabel5.setText("Aktuelna cena");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cena"));

        jLabel6.setText("CenaID");

        jLabel7.setText("Datum");

        jLabel8.setText("Iznos");

        btnIzmeniCenu.setText("Izmeni cenu");
        btnIzmeniCenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniCenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDatumID, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jtfCenaID)
                    .addComponent(jtfIznos))
                .addGap(44, 44, 44)
                .addComponent(btnIzmeniCenu, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCenaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfDatumID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIzmeniCenu))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfIznos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        cmbModel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnIzmeniLepo.setText("Izmeni");
        btnIzmeniLepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniLepoActionPerformed(evt);
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSacuvaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(btnIzmeniLepo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtfProizvod1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(btnPronadji)
                                .addGap(65, 65, 65))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfAktuelnaCena)
                                    .addComponent(jtfNaziv, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(jtfOpis)
                                    .addComponent(cmbModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPronadji, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jtfProizvod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jtfNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnIzmeni)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtfAktuelnaCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSacuvaj)
                        .addComponent(btnIzmeniLepo))
                    .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed

        String proizvodID = jtfProizvod1.getText().trim();
        if (proizvodID.isEmpty() || jtfOpis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!proizvodID.matches("[1-9][0-9]*")) {
            JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Proizvod p = new Proizvod();
        p.setProizvodID(Integer.parseInt(proizvodID));
        p.setOpis(jtfOpis.getText());
        p.setNaziv(jtfNaziv.getText());
        p.setModelID((ModelProizvoda) cmbModel.getSelectedItem());
        
        
        Cena c = new Cena();
        c.setCenaID(Integer.parseInt(jtfCenaID.getText()));
        c.setDatum(new Date());
        c.setIznos(Double.parseDouble(jtfIznos.getText()));
        c.setProizvodID(p.getProizvodID());
        p.setAktuelnaCena(Double.parseDouble(jtfIznos.getText()));
        p.setCena(c);
        
        try {
            dbb.uspostaviKonekciju();
            dbb.ubaciProizvod(p);
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Uspešno ubacen proizvod", "Proizvod", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FProizvod.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno ubacivanje proizvoda\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
            String proizvodID = jtfProizvod1.getText().trim();
            if (proizvodID.isEmpty() || jtfCenaID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Proizvod p = new Proizvod();
            
            p.setProizvodID(Integer.parseInt(proizvodID));
            p.setOpis(jtfOpis.getText());
            p.setNaziv(jtfNaziv.getText());
            p.setModelID((ModelProizvoda) cmbModel.getSelectedItem());
            p.setAktuelnaCena(Double.parseDouble(jtfIznos.getText()));
   
        
            Cena c = new Cena();
            c.setCenaID(Integer.parseInt(jtfCenaID.getText()));
            c.setDatum(new Date());
            c.setIznos(Double.parseDouble(jtfIznos.getText()));
            c.setProizvodID(p.getProizvodID());
            p.setCena(c);
            try {
                dbb.uspostaviKonekciju();
                dbb.izmeniProizvod(p);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjen proizvod", "Izmena", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FProizvod.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešna izmena proizvoda\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        String proizvodID = jtfProizvod1.getText().trim();

        if (proizvodID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Niste popunili id proizvoda", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            dbb.uspostaviKonekciju();
            boolean uspesno = dbb.obrisiProizvod(Integer.parseInt(proizvodID));
            if (uspesno) {
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno obrisan proizvod", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno brisanje proizvoda", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno brisanje proizvoda\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FProizvod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiActionPerformed
        try {
            if (jtfProizvod1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili polje za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!jtfProizvod1.getText().matches("[1-9][0-9]*")) {
                JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dbb.uspostaviKonekciju();

            Proizvod p = dbb.ucitajProizvod(Integer.parseInt(jtfProizvod1.getText()));
            if (p == null) {
                JOptionPane.showMessageDialog(null, "Nije pronadjen proizvod sa datim id-em", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();

            jtfOpis.setText(p.getOpis());
            jtfNaziv.setText(p.getNaziv());
            jtfAktuelnaCena.setText(p.getAktuelnaCena() + "");
            
            popuniComboBoxModel();
            for (int i = 0; i < cmbModel.getItemCount(); i++) {
                if (cmbModel.getItemAt(i).toString().equals(p.getModelID().getNazivModelaProizvoda())) {
                    System.out.println("pronasao zahtev");
                    cmbModel.setSelectedItem(cmbModel.getItemAt(i));
                }
            }
            jtfCenaID.setText(p.getCena().getCenaID() + "");
            jtfDatumID.setText(p.getCena().getDatum() + "");
            jtfIznos.setText(p.getCena().getIznos() + "");


            jtfProizvod1.setEnabled(false);
            btnPronadji.setEnabled(false);
            jtfOpis.setEnabled(true);
            cmbModel.setEnabled(true);
            btnIzmeni.setEnabled(true);
            btnObrisi.setEnabled(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FBanka.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešna pretraga proizvoda\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnPronadjiActionPerformed

    private void btnIzmeniCenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniCenuActionPerformed
        Cena c = new Cena();
        c.setCenaID(Integer.parseInt(jtfCenaID.getText()));
        c.setDatum(new Date());
        c.setIznos(Double.parseDouble(jtfIznos.getText()));
        c.setProizvodID(Integer.parseInt(jtfProizvod1.getText()));
        try {
                dbb.uspostaviKonekciju();
                dbb.izmeniCenu(c);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjena cena", "Izmena", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FProizvod.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešna izmena cene\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnIzmeniCenuActionPerformed

    private void btnIzmeniLepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniLepoActionPerformed
        String proizvodID = jtfProizvod1.getText().trim();
            if (proizvodID.isEmpty() || jtfCenaID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Proizvod p = new Proizvod();
            
            p.setProizvodID(Integer.parseInt(proizvodID));
            p.setOpis(jtfOpis.getText());
            p.setNaziv(jtfNaziv.getText());
            p.setModelID((ModelProizvoda) cmbModel.getSelectedItem());
            p.setAktuelnaCena(Double.parseDouble(jtfIznos.getText()));
            
        
            Cena c = new Cena();
            c.setCenaID(Integer.parseInt(jtfCenaID.getText()));
            c.setDatum(new Date());
            c.setIznos(Double.parseDouble(jtfIznos.getText()));
            c.setProizvodID(p.getProizvodID());
            p.setCena(c);
            try {
                dbb.uspostaviKonekciju();
                dbb.izmeniProizvodLepo(p);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjen proizvod", "Izmena", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FProizvod.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešna izmena proizvoda\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnIzmeniLepoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniCenu;
    private javax.swing.JButton btnIzmeniLepo;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPronadji;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox cmbModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jtfAktuelnaCena;
    private javax.swing.JTextField jtfCenaID;
    private javax.swing.JTextField jtfDatumID;
    private javax.swing.JTextField jtfIznos;
    private javax.swing.JTextField jtfNaziv;
    private javax.swing.JTextField jtfOpis;
    private javax.swing.JTextField jtfProizvod1;
    // End of variables declaration//GEN-END:variables
}
