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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Banka;
import model.Drzava;
import model.Mesto;

/**
 *
 * @author Marija
 */
public class FDrzava extends javax.swing.JFrame {

    
    DBBroker dbb = new DBBroker();
    private Operacija operacija;

    public Operacija getOperacija() {
        return operacija;
    }

    
    /**
     * Creates new form FIzvod
     */
    public enum Operacija {
        KREIRANJE,
        IZMENA
    }

    /**
     * Creates new form FNarudzbenica
     */
    public FDrzava(String flag) {
        initComponents();

        if (flag.equals("kreiraj")) {
            operacija = Operacija.KREIRANJE;
            btnPronadji.setVisible(false);
            btnIzmeni.setVisible(false);
            btnObrisi.setVisible(false);

        }
        if (flag.equals("izmeni")) {
            operacija = Operacija.IZMENA;
            btnSacuvaj.setVisible(false);
            btnIzmeni.setEnabled(false);
            btnObrisi.setEnabled(false);
            jtfNazivBanke.setEnabled(false);

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
        jtfBankaID = new javax.swing.JTextField();
        jtfNazivBanke = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPronadji = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("DrzavaID");

        jLabel2.setText("Naziv drzave");

        btnPronadji.setText("Pronadji");
        btnPronadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNazivBanke, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jtfBankaID, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(btnPronadji, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 147, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfBankaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPronadji)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfNazivBanke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSacuvaj, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnIzmeni)
                        .addComponent(btnObrisi)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
            String bankaID = jtfBankaID.getText().trim();

            System.out.println("AJDE");
            Drzava b = new Drzava();

  
            b.setDrzavaID(Integer.parseInt(bankaID));
            b.setNazivDrzave(jtfNazivBanke.getText());
 
            System.out.println(b);

            try {
                dbb.uspostaviKonekciju();
                dbb.ubaciDrzavu(b);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno ubacena drzava", "Banka", JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno ubacivanje drzave\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiActionPerformed
        try {     
            if (jtfBankaID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!jtfBankaID.getText().matches("[1-9][0-9]*")) {
                JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
              
            dbb.uspostaviKonekciju();
      
            Drzava b = dbb.ucitajDrzave(Integer.parseInt(jtfBankaID.getText()));
            if (b == null) {
                JOptionPane.showMessageDialog(null, "Nije pronadjen drzava sa datim rb", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            jtfBankaID.setText(b.getDrzavaID()+"");
            jtfNazivBanke.setText(b.getNazivDrzave());


            
            jtfBankaID.setEnabled(false);
            btnPronadji.setEnabled(false);
            jtfNazivBanke.setEnabled(true);

            btnIzmeni.setEnabled(true);
            btnObrisi.setEnabled(true);
            } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FDrzava.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno pretrazivanje izvoda\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPronadjiActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed

            String bankaID = jtfBankaID.getText().trim();
            if (bankaID.isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Drzava b = new Drzava();

  
            b.setDrzavaID(Integer.parseInt(bankaID));
            b.setNazivDrzave(jtfNazivBanke.getText());
  
            
            try {
                dbb.uspostaviKonekciju();
                dbb.izmeniDrzavu(b);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjen izvod", "Izmena", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Nije uspela izmena\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FDrzava.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
            }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        String bankaID = jtfBankaID.getText().trim();

        if (bankaID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Niste popunili broj izvoda", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            dbb.uspostaviKonekciju();
            boolean uspesno = dbb.obrisiDrzavu(Integer.parseInt(bankaID));
            if (uspesno) {
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno obrisana banka", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno obrisana banka", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno brisanje banke\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FDrzava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnObrisiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPronadji;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jtfBankaID;
    private javax.swing.JTextField jtfNazivBanke;
    // End of variables declaration//GEN-END:variables
}