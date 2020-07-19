/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import dbb.DBBroker;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Narucilac;
import model.Profaktura;
import model.Racun;
import model.Reklamacija;
import model.STATUS;
import model.StavkaProfakture;
import model.StavkaReklamacije;
import model.Zaposleni;
import modeli.tabele.ModelTabeleStavkeReklamacije;

/**
 *
 * @author Marija
 */
public class FReklamacija extends javax.swing.JFrame {

    DBBroker dbb = new DBBroker();
    private Operacija operacija;
    private List<StavkaReklamacije> stavkeReklamacije;
    

    public Operacija getOperacija() {
        return operacija;
    }

    public List<StavkaReklamacije> getstavkeReklamacije() {
        return stavkeReklamacije;
    }

    public JTable gettabelaStavki() {
        return tabelaStavki;
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
            }
            ComboBoxModel model = new DefaultComboBoxModel(lista.toArray());
            cmbRacun.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }
    private void popuniComboBoxProfaktura() {
        try {
            cmbProfaktura.removeAllItems();
            dbb.uspostaviKonekciju();
            List<Profaktura> listaZaposleni = dbb.vratiSveProfakture();
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();
            for (Profaktura zaposleni : listaZaposleni) {
                cmbProfaktura.addItem(zaposleni);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
        }
    }
    void izmeniStavkuReklamacije(StavkaReklamacije sn, int selectedRow) {
        for (StavkaReklamacije StavkaReklamacije : stavkeReklamacije) {
            if (StavkaReklamacije.getStavkaReklamacijeID()== sn.getStavkaReklamacijeID()) {
                StavkaReklamacije.setReklamacijaID(sn.getReklamacijaID());
                StavkaReklamacije.setOpisStavke(sn.getOpisStavke());
                StavkaReklamacije.setProzivodID(sn.getProzivodID());
                StavkaReklamacije.setOpisReklamacije(sn.getOpisReklamacije());
                
                if (sn.getStatus() != null) {
                    StavkaReklamacije.setStatus(sn.getStatus());
                }
            }
        }

    }


    void izmeniOpisDaSeMenjaIzStavke() {
        try {
            String brojReklamacije = jtfReklamacijaID.getText().trim();
            if (brojReklamacije.isEmpty() || jtfDatumKupovine.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Reklamacija r = new Reklamacija();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            r.setReklamacijaID(Integer.parseInt(brojReklamacije));
            r.setDatumKupovine(sdf.parse(jtfDatumKupovine.getText()));
            r.setDatumOdobravanja(sdf.parse(jtfDatumOdobravanja.getText()));
            r.setDatumPostavljanja(sdf.parse(jtfDatumPostavljanja.getText()));
            r.setStavkeReklamacije(getstavkeReklamacije());
            r.setZaposleniid((Zaposleni) cmbZaposleni.getSelectedItem());
            r.setProfakturaID((Profaktura) cmbProfaktura.getSelectedItem());
            r.setPIBNarucioca((Narucilac) cmbNarucilac.getSelectedItem());
            r.setRacunID((Racun) cmbRacun.getSelectedItem());
            try {
                dbb.uspostaviKonekciju();
                dbb.izmeniReklamacijuDaMenjasStavku(r);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjena reklamacija", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešna izmena reklamacije i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public enum Operacija {
        KREIRANJE,
        IZMENA
    }

    /**
     * Creates new form FNarudzbenica
     */
    public FReklamacija(String flag) {
        initComponents();

        tabelaStavki.setModel(new ModelTabeleStavkeReklamacije());
        if (flag.equals("kreiraj")) {
            operacija = Operacija.KREIRANJE;
            stavkeReklamacije = new ArrayList<>();
            btnPronadji.setVisible(false);
            btnIzmeni.setVisible(false);
            btnObrisi.setVisible(false);
            popuniComboBoxNarucilac();
            popuniComboBoxProfaktura();
            popuniComboBoxRacun();
            popuniComboBoxZaposleni();

        }
        if (flag.equals("izmeni")) {
            operacija = Operacija.IZMENA;
            btnDodajStavku.setEnabled(false);
            btnIzmeniStavku.setEnabled(false);
            btnObrisiStavku.setEnabled(false);
            btnSacuvaj.setVisible(false);
            jtfDatumKupovine.setEnabled(false);
            btnIzmeni.setEnabled(false);
            btnObrisi.setEnabled(false);
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
        jtfReklamacijaID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfDatumKupovine = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaStavki = new javax.swing.JTable();
        btnDodajStavku = new javax.swing.JButton();
        btnIzmeniStavku = new javax.swing.JButton();
        btnObrisiStavku = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnPronadji = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfDatumPostavljanja = new javax.swing.JTextField();
        jtfDatumOdobravanja = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfIzjava = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbZaposleni = new javax.swing.JComboBox();
        cmbNarucilac = new javax.swing.JComboBox();
        cmbRacun = new javax.swing.JComboBox();
        cmbProfaktura = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jtfOpisReklamacije = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Broj Reklamacije:");

        jLabel2.setText("Datum kupovine");

        jtfDatumKupovine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDatumKupovineActionPerformed(evt);
            }
        });

        tabelaStavki.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaStavki);

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

        btnPronadji.setText("Pronadji");
        btnPronadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiActionPerformed(evt);
            }
        });

        jLabel3.setText("Datum odobravanja:");

        jLabel4.setText("Datum postavljanja");

        jtfDatumPostavljanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDatumPostavljanjaActionPerformed(evt);
            }
        });

        jtfDatumOdobravanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDatumOdobravanjaActionPerformed(evt);
            }
        });

        jLabel5.setText("Izjava");

        jtfIzjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIzjavaActionPerformed(evt);
            }
        });

        jLabel6.setText("Zaposleni");

        jLabel7.setText("Narucilac");

        jLabel8.setText("Racun");

        jLabel9.setText("Profaktura");

        cmbZaposleni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbNarucilac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbRacun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbProfaktura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Opis");

        jtfOpisReklamacije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfOpisReklamacijeActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDodajStavku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnObrisiStavku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIzmeniStavku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSacuvaj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIzmeni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel1))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfReklamacijaID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jtfDatumOdobravanja, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfDatumKupovine, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfDatumPostavljanja, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbProfaktura, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbRacun, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbNarucilac, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPronadji, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfOpisReklamacije, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtfIzjava))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfReklamacijaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPronadji))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfDatumKupovine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfDatumOdobravanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmbNarucilac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfDatumPostavljanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cmbRacun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbProfaktura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jtfIzjava, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jtfOpisReklamacije, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDodajStavku)
                        .addGap(24, 24, 24)
                        .addComponent(btnIzmeniStavku)
                        .addGap(18, 18, 18)
                        .addComponent(btnObrisiStavku)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSacuvaj)
                    .addComponent(btnIzmeni)
                    .addComponent(btnObrisi))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajStavkuActionPerformed
        FStavkaReklamacije forma = new FStavkaReklamacije("kreiraj", this);
        forma.setLocationRelativeTo(null);
        forma.setVisible(true);

    }//GEN-LAST:event_btnDodajStavkuActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        try {
            String brojReklamacije = jtfReklamacijaID.getText().trim();
            if (brojReklamacije.isEmpty() || jtfDatumKupovine.getText().isEmpty()
                    || jtfDatumOdobravanja.getText().isEmpty() || jtfDatumPostavljanja.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!brojReklamacije.matches("[1-9][0-9]*")) {
                JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Reklamacija n = new Reklamacija();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            n.setReklamacijaID(Integer.parseInt(brojReklamacije));
            n.setDatumKupovine(sdf.parse((jtfDatumKupovine.getText())));
            n.setDatumOdobravanja(sdf.parse(jtfDatumOdobravanja.getText()));
            n.setDatumPostavljanja(sdf.parse(jtfDatumPostavljanja.getText()));
            n.setStavkeReklamacije(((ModelTabeleStavkeReklamacije) tabelaStavki.getModel()).vratiStavke());
            n.setIzjava(jtfIzjava.getText());
            n.setOpisReklamacije(jtfOpisReklamacije.getText());
            n.setZaposleniid((Zaposleni) cmbZaposleni.getSelectedItem());
            n.setProfakturaID((Profaktura) cmbProfaktura.getSelectedItem());
            n.setPIBNarucioca((Narucilac) cmbNarucilac.getSelectedItem());
            n.setRacunID((Racun) cmbRacun.getSelectedItem());
            try {
                dbb.uspostaviKonekciju();
                dbb.ubaciReklamaciju(n);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno ubačena reklamacija i njene stavke", "", JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno ubacivanje reklamacije i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiActionPerformed
        try {
            if (jtfReklamacijaID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!jtfReklamacijaID.getText().matches("[1-9][0-9]*")) {
                JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dbb.uspostaviKonekciju();

            Reklamacija r = dbb.ucitajReklamaciju(Integer.parseInt(jtfReklamacijaID.getText()));
            if (r == null) {
                JOptionPane.showMessageDialog(null, "Nije pronadjena narudzbenica sa datim rb", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dbb.potvrdiTransakciju();
            dbb.raskiniKonekciju();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            jtfDatumKupovine.setText(sdf.format(r.getDatumKupovine()));
            jtfDatumOdobravanja.setText(sdf.format(r.getDatumOdobravanja()));
            jtfDatumPostavljanja.setText(sdf.format(r.getDatumPostavljanja()));
            jtfOpisReklamacije.setText(r.getOpisReklamacije());
            jtfIzjava.setText(r.getIzjava());
            popuniComboBoxZaposleni();
            for (int i = 0; i < cmbZaposleni.getItemCount(); i++) {
                if (cmbZaposleni.getItemAt(i).toString().equals(r.getZaposleniid().getNazivZap())) {
                    System.out.println("pronasao zahtev");
                    cmbZaposleni.setSelectedItem(cmbZaposleni.getItemAt(i));
                }
            }
            popuniComboBoxRacun();
            for (int i = 0; i < cmbRacun.getItemCount(); i++) {
                if (cmbRacun.getItemAt(i).toString().equals(r.getRacunID().getRacunID())) {
                    System.out.println("pronasao zahtev");
                    cmbRacun.setSelectedItem(cmbRacun.getItemAt(i));
                }
            }
            popuniComboBoxNarucilac();
            for (int i = 0; i < cmbNarucilac.getItemCount(); i++) {
                if (cmbNarucilac.getItemAt(i).toString().equals(r.getPIBNarucioca().getNazivNarucioca())) {
                    System.out.println("pronasao zahtev");
                    cmbNarucilac.setSelectedItem(cmbNarucilac.getItemAt(i));
                }
            }
            popuniComboBoxProfaktura();
            for (int i = 0; i < cmbProfaktura.getItemCount(); i++) {
                if (cmbProfaktura.getItemAt(i).toString().equals(r.getProfakturaID().getProfakturaID())) {
                    System.out.println("pronasao zahtev");
                    cmbProfaktura.setSelectedItem(cmbProfaktura.getItemAt(i));
                }
            }

            stavkeReklamacije = new ArrayList<>();
            for (StavkaReklamacije sn : r.getStavkeReklamacije()) {
                stavkeReklamacije.add(sn);
            }

            tabelaStavki.setModel(new ModelTabeleStavkeReklamacije(r.getStavkeReklamacije()));
            jtfReklamacijaID.setEnabled(false);
            btnPronadji.setEnabled(false);
            jtfDatumKupovine.setEnabled(true);
            btnIzmeni.setEnabled(true);
            btnObrisi.setEnabled(true);
            btnIzmeniStavku.setEnabled(true);
            btnObrisiStavku.setEnabled(true);
            btnDodajStavku.setEnabled(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno pretrazivanje narudžbenice i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPronadjiActionPerformed

    private void btnIzmeniStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniStavkuActionPerformed
        if (tabelaStavki.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nije selektovana stavka za izmenu", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FStavkaReklamacije forma = new FStavkaReklamacije("izmeni", this);
        forma.setLocationRelativeTo(null);
        forma.setVisible(true);
    }//GEN-LAST:event_btnIzmeniStavkuActionPerformed

    private void jtfDatumKupovineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDatumKupovineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDatumKupovineActionPerformed

    private void btnObrisiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiStavkuActionPerformed
        if (tabelaStavki.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nije selektovana stavka za brisanje", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (operacija == Operacija.IZMENA) {
            int indeks = -1;
            for (int i = 0; i < stavkeReklamacije.size(); i++) {
                if (stavkeReklamacije.get(i).getStavkaReklamacijeID()== ((ModelTabeleStavkeReklamacije) tabelaStavki.getModel()).vratiStavkuReklamacije(tabelaStavki.getSelectedRow()).getStavkaReklamacijeID()) {
                    if (stavkeReklamacije.get(i).getStatus() == null || stavkeReklamacije.get(i).getStatus() == STATUS.IZMENI) {

                        stavkeReklamacije.get(i).setStatus(STATUS.OBRISI);
                        ((ModelTabeleStavkeReklamacije) tabelaStavki.getModel()).obrisiStavkuReklamacije(tabelaStavki.getSelectedRow());
                        break;
                    } else {
                        if (stavkeReklamacije.get(i).getStatus() != STATUS.OBRISI) {
                            indeks = i;
                            break;
                        }
                    }
                }
            }
            if (indeks > -1) {
                stavkeReklamacije.remove(indeks);
                ((ModelTabeleStavkeReklamacije) tabelaStavki.getModel()).obrisiStavkuReklamacije(tabelaStavki.getSelectedRow());
            }
        }

        if (operacija == Operacija.KREIRANJE) {
            stavkeReklamacije.remove(tabelaStavki.getSelectedRow());
            ((ModelTabeleStavkeReklamacije) tabelaStavki.getModel()).obrisiStavkuReklamacije(tabelaStavki.getSelectedRow());
        }

        stavkeReklamacije.forEach((sp) -> {
            System.out.println(sp.getStavkaReklamacijeID()+ " " + sp.getStatus());
        });
    }//GEN-LAST:event_btnObrisiStavkuActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        try {
            String brojReklamacije = jtfReklamacijaID.getText().trim();
            if (brojReklamacije.isEmpty() || jtfDatumKupovine.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Niste popunili sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Reklamacija r = new Reklamacija();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            r.setReklamacijaID(Integer.parseInt(brojReklamacije));
            r.setDatumKupovine(sdf.parse(jtfDatumKupovine.getText()));
            r.setDatumOdobravanja(sdf.parse(jtfDatumOdobravanja.getText()));
            r.setDatumPostavljanja(sdf.parse(jtfDatumPostavljanja.getText()));
            r.setStavkeReklamacije(getstavkeReklamacije());
            r.setZaposleniid((Zaposleni) cmbZaposleni.getSelectedItem());
            r.setProfakturaID((Profaktura) cmbProfaktura.getSelectedItem());
            r.setPIBNarucioca((Narucilac) cmbNarucilac.getSelectedItem());
            r.setRacunID((Racun) cmbRacun.getSelectedItem());
            r.setOpisReklamacije(jtfOpisReklamacije.getText());
            r.setIzjava(jtfIzjava.getText());
            try {
                dbb.uspostaviKonekciju();
                dbb.izmeniReklamaciju(r);
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno izmenjena reklamacija", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešna izmena reklamacije i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        String brojReklamacije = jtfReklamacijaID.getText().trim();

        if (brojReklamacije.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Niste popunili broj Narudzbenice", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!brojReklamacije.matches("[1-9][0-9]*")) {
            JOptionPane.showMessageDialog(null, "ID mora da bude broj", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            dbb.uspostaviKonekciju();
            boolean uspesno = dbb.obrisiReklamaciju(Integer.parseInt(brojReklamacije));
            if (uspesno) {
                dbb.potvrdiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Uspešno obrisana narudžbenica i njene stavke", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                dbb.ponistiTransakciju();
                dbb.raskiniKonekciju();
                JOptionPane.showMessageDialog(null, "Neuspešno brisanje narudžbenice i njenih stavki", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            dbb.ponistiTransakciju();
            dbb.raskiniKonekciju();
            JOptionPane.showMessageDialog(null, "Neuspešno brisanje narudžbenice i njenih stavki\n"+ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FReklamacija.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnObrisiActionPerformed

    private void jtfDatumPostavljanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDatumPostavljanjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDatumPostavljanjaActionPerformed

    private void jtfDatumOdobravanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDatumOdobravanjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDatumOdobravanjaActionPerformed

    private void jtfIzjavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIzjavaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIzjavaActionPerformed

    private void jtfOpisReklamacijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfOpisReklamacijeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfOpisReklamacijeActionPerformed
        
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniStavku;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiStavku;
    private javax.swing.JButton btnPronadji;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox cmbNarucilac;
    private javax.swing.JComboBox cmbProfaktura;
    private javax.swing.JComboBox cmbRacun;
    private javax.swing.JComboBox cmbZaposleni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtfDatumKupovine;
    private javax.swing.JTextField jtfDatumOdobravanja;
    private javax.swing.JTextField jtfDatumPostavljanja;
    private javax.swing.JTextField jtfIzjava;
    private javax.swing.JTextField jtfOpisReklamacije;
    private javax.swing.JTextField jtfReklamacijaID;
    private javax.swing.JTable tabelaStavki;
    // End of variables declaration//GEN-END:variables
}
