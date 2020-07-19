/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import dbb.DBBroker;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Proizvod;
import model.Reklamacija;
import model.STATUS;
import model.StavkaProfakture;
import model.StavkaReklamacije;
import modeli.tabele.ModelTabeleStavkeReklamacije;

/**
 *
 * @author Marija
 */
public class FStavkaReklamacije extends javax.swing.JFrame {

    DBBroker dbb = new DBBroker();
    FReklamacija fp;

    public FStavkaReklamacije(String flag, FReklamacija fp) {
        initComponents();
        this.fp = fp;
        popuniComboBoxProizvod();

        if (flag.equals("kreiraj")) {
            btnIzmeni.setVisible(false);
            
        }
        if (flag.equals("izmeni")) {
            btnSacuvaj.setVisible(false);
            jtfReklamacijaID.setEnabled(false);
            postaviStavku(((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).vratiStavkuReklamacije(fp.gettabelaStavki().getSelectedRow()));

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

        jtfStavkaID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfOpisStavke = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfOpisReklamacije = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbProizvod = new javax.swing.JComboBox<Object>();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        jtfReklamacijaID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnIzmeniOpis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Broj stavke reklamacije");

        jLabel3.setText("Opis stavke");

        jLabel4.setText("Opis reklamacije");

        jLabel5.setText("Proizvod");

        cmbProizvod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProizvodActionPerformed(evt);
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

        jLabel6.setText("Broj reklamacije");

        btnIzmeniOpis.setText("Izmeni Opis");
        btnIzmeniOpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniOpisActionPerformed(evt);
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
                        .addComponent(btnSacuvaj)
                        .addGap(124, 124, 124)
                        .addComponent(btnIzmeni)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbProizvod, 0, 171, Short.MAX_VALUE)
                                    .addComponent(jtfStavkaID)
                                    .addComponent(jtfReklamacijaID))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfOpisReklamacije, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfOpisStavke, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(btnIzmeniOpis)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfReklamacijaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfStavkaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIzmeniOpis)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtfOpisStavke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jtfOpisReklamacije, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProizvod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSacuvaj)
                    .addComponent(btnIzmeni))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        if (jtfOpisStavke.getText().isEmpty() || jtfOpisReklamacije.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Nisu popunjena sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rb = jtfStavkaID.getText().trim();

        if (!rb.matches("[1-9][0-9]*")) {
            JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean postojiRB = false;
        for (StavkaReklamacije sp : fp.getstavkeReklamacije()) {
            if (sp.getStatus() == null || sp.getStatus() != STATUS.OBRISI) { 
                if (sp.getStavkaReklamacijeID()== Integer.parseInt(jtfStavkaID.getText())) {
                    postojiRB = true;
                    break;
                }
            }
        }
        if (postojiRB) {
            JOptionPane.showMessageDialog(this, "Već postoji stavka sa datim Rednim Brojem", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StavkaReklamacije sr = new StavkaReklamacije();
        sr.setStavkaReklamacijeID(Integer.parseInt(jtfStavkaID.getText()));
        sr.setReklamacijaID(Integer.parseInt(jtfReklamacijaID.getText()));
        sr.setOpisReklamacije(jtfOpisReklamacije.getText());
        sr.setOpisStavke(jtfOpisStavke.getText());
        sr.setProzivodID((Proizvod) cmbProizvod.getSelectedItem());

        if (fp.getOperacija() == FReklamacija.Operacija.KREIRANJE) {

            boolean postojiUTabeliID = false;
            for (StavkaReklamacije stavp : ((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).vratiStavke()) {
                if (stavp.getStavkaReklamacijeID()== sr.getStavkaReklamacijeID()) {
                    postojiUTabeliID = true;
                    break;
                }
            }
            if (postojiUTabeliID) {
                JOptionPane.showMessageDialog(this, "Već postoji stavka sa datim rednim brojem", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).dodajStavkuReklamacije(sr);
            fp.getstavkeReklamacije().add(sr);
            dispose();
        }
        if (fp.getOperacija() == FReklamacija.Operacija.IZMENA) {
            sr.setStatus(STATUS.DODAJ);
            fp.getstavkeReklamacije().add(sr);
            ((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).dodajStavkuReklamacije(sr);
            dispose();
        }
        fp.getstavkeReklamacije().forEach((sp1) -> {
            System.out.println(sp1.getStavkaReklamacijeID()+ " " + sp1.getStatus());
        });
       
        
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        if (jtfOpisStavke.getText().isEmpty() || jtfOpisReklamacije.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Nisu popunjena sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StavkaReklamacije sr = new StavkaReklamacije();
        sr.setStavkaReklamacijeID(Integer.parseInt(jtfStavkaID.getText()));
        sr.setReklamacijaID(Integer.parseInt(jtfReklamacijaID.getText()));
        sr.setOpisReklamacije(jtfOpisReklamacije.getText());
        sr.setOpisStavke(jtfOpisStavke.getText());
        sr.setProzivodID((Proizvod) cmbProizvod.getSelectedItem());


        if (fp.getOperacija() == FReklamacija.Operacija.IZMENA) {
            if (((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).vratiStavkuReklamacije(fp.gettabelaStavki().getSelectedRow()).getStatus() == null) {
                sr.setStatus(STATUS.IZMENI);
            }
        }

        for (int i = 0; i < fp.getstavkeReklamacije().size(); i++) {
            if (fp.getstavkeReklamacije().get(i).getStavkaReklamacijeID()== ((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).vratiStavkuReklamacije(fp.gettabelaStavki().getSelectedRow()).getStavkaReklamacijeID()) {
                fp.izmeniStavkuReklamacije(sr, fp.gettabelaStavki().getSelectedRow());
            }
        }

        ((ModelTabeleStavkeReklamacije) fp.gettabelaStavki().getModel()).izmeniStavkuReklamacije(sr, fp.gettabelaStavki().getSelectedRow());

        dispose();

        fp.getstavkeReklamacije().forEach((sp1) -> {
            System.out.println(sp1.getStavkaReklamacijeID()+ " " + sp1.getStatus());
        });

    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void cmbProizvodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProizvodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProizvodActionPerformed

    private void btnIzmeniOpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniOpisActionPerformed
       fp.izmeniOpisDaSeMenjaIzStavke();
    }//GEN-LAST:event_btnIzmeniOpisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniOpis;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox<Object> cmbProizvod;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jtfOpisReklamacije;
    private javax.swing.JTextField jtfOpisStavke;
    private javax.swing.JTextField jtfReklamacijaID;
    private javax.swing.JTextField jtfStavkaID;
    // End of variables declaration//GEN-END:variables

    private void popuniComboBoxProizvod() {
        try {
            cmbProizvod.removeAllItems();
            dbb.uspostaviKonekciju();
            List<Proizvod> lista = dbb.vratiSveProizvode();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (Proizvod p : lista) {
                cmbProizvod.addItem(p);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FStavkaProfakture.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }

    private void postaviStavku(StavkaReklamacije sp) {
        jtfReklamacijaID.setText(sp.getReklamacijaID()+ "");
        jtfStavkaID.setText(sp.getStavkaReklamacijeID()+"");
        jtfOpisReklamacije.setText(sp.getOpisReklamacije());
        jtfOpisStavke.setText(sp.getOpisStavke());


        for (int i = 0; i < cmbProizvod.getItemCount(); i++) {
            if (cmbProizvod.getItemAt(i).toString().equals(sp.getProzivodID().getNaziv())) {
                System.out.println("pronasao proizvod");
                cmbProizvod.setSelectedItem(cmbProizvod.getItemAt(i));
            }
        }
    } 
}