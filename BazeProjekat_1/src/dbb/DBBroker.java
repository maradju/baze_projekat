/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Banka;
import model.Cena;
import model.Drzava;
import model.Mesto;
import model.ModelProizvoda;
import model.Narucilac;
import model.Prevoznik;
import model.Profaktura;
import model.Proizvod;
import model.Racun;
import model.Reklamacija;
import model.StavkaProfakture;
import model.StavkaReklamacije;
import model.TipRacuna;
import model.Zaposleni;

/**
 *
 * @author Marija
 */
public class DBBroker {

    private Connection con;

    public void uspostaviKonekciju() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
        con.setAutoCommit(false);
        System.out.println("Konekcija uspostevaljena");
    }

    public void raskiniKonekciju() {
        try {
            con.close();
            System.out.println("Konekcija je raskinuta!");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void potvrdiTransakciju() {
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ponistiTransakciju() {
        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ubaciReklamaciju(Reklamacija r) throws SQLException {
        
        String upit = "INSERT INTO REKLAMACIJA VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, r.getReklamacijaID());
        ps.setDate(2,  new Date(r.getDatumKupovine().getTime()));
        ps.setDate(3,  new Date(r.getDatumOdobravanja().getTime()));
        ps.setDate(4,  new Date(r.getDatumPostavljanja().getTime()));
        ps.setInt(5, r.getZaposleniid().getZaposleniID());
        ps.setString(6, r.getIzjava());
        ps.setInt(7, r.getPIBNarucioca().getPib());
        ps.setInt(8, r.getRacunID().getRacunID());
        ps.setInt(9, r.getProfakturaID().getProfakturaID());
        ps.setString(10, r.getOpisReklamacije());
        System.out.println("uspesno ubaceno");
        int uspesno = ps.executeUpdate();
        ps.close();
        if (uspesno != 0) {
            for (StavkaReklamacije sn : r.getStavkeReklamacije()) {
                upit = "INSERT INTO STAVKA_REKLAMACIJE VALUES(?,?,?,?,?)";
                ps = con.prepareStatement(upit);
                ps.setInt(1, sn.getStavkaReklamacijeID());
                ps.setInt(2, sn.getReklamacijaID());
                ps.setString(3, sn.getOpisStavke());
                ps.setInt(4, sn.getProzivodID().getProizvodID());
                ps.setString(5, sn.getOpisReklamacije());
                ps.executeUpdate();
            }
            ps.close();
        }
    }
        public List<Zaposleni> vratiSveZaposlene() throws SQLException {
        List<Zaposleni> listaZaposleni = new ArrayList<>();
        String upit = "SELECT * FROM ZAPOSLENI";
        PreparedStatement ps = con.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setZaposleniID(rs.getInt(1));
            z.setNazivZap(rs.getString(2));
            listaZaposleni.add(z);
        }

        return listaZaposleni;
    }

  public Zaposleni ucitajZaposlenog(int id) throws SQLException {
        Zaposleni z = null;
        String upit = "SELECT * FROM zaposleni z WHERE z.ZAPOSLENIID=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            z = new Zaposleni();
            z.setZaposleniID(rs.getInt(1));
            z.setNazivZap(rs.getString(2));
        }
        rs.close();
        ps.close();
        return z;
    }

  public Narucilac ucitajNarucioca(int id) throws SQLException {
        Narucilac z = null;
        String upit = "SELECT * FROM narucilac n WHERE n.PIB=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            z = new Narucilac();
            z.setPib(rs.getInt(1));
            z.setNazivNarucioca(rs.getString(2));
            z.setEmail(rs.getString(3));
            Drzava d = new Drzava();
            Mesto m= new Mesto();
            d.setDrzavaID(rs.getInt(4));
            m.setMestoID(rs.getInt(5));
            z.setDrzavaID(d);
            z.setMestoID(m);
        }
        rs.close();
        ps.close();
        return z;
    }

  public Racun ucitajRacun(int id) throws SQLException {
        Racun z = null;
        String upit = "SELECT * FROM racun n WHERE n.racunid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            z = new Racun();
            z.setRacunID(rs.getInt(1));
            TipRacuna tr = new TipRacuna();
            tr.setTipRacunaID(rs.getInt(2));
            z.setTipRacunaID(tr);
            z.setBrojRacuna(rs.getInt(3));
            z.setTipRacuna(rs.getString(4));
        }
        rs.close();
        ps.close();
        return z;
    }

    public List<Narucilac> vratiSveNarucioce() throws SQLException  {
        List<Narucilac> listaZaposleni = new ArrayList<>();
        String upit = "SELECT * FROM NARUCILAC";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Narucilac n = new Narucilac();
            n.setPib(rs.getInt(1));
            n.setNazivNarucioca(rs.getString(2));
            n.setEmail(rs.getString(3));
            Drzava d = new Drzava();
            d.setDrzavaID(rs.getInt(4));
            Mesto m = new Mesto();
            m.setMestoID(rs.getInt(5));
            n.setDrzavaID(d);
            n.setMestoID(m);
            listaZaposleni.add(n);
        }

        return listaZaposleni;
    }

    public List<Racun> vratiSveRacune() throws SQLException {
        List<Racun> lista = new ArrayList<>();
        String upit = "SELECT * FROM RACUN";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Racun r = new Racun();
            r.setRacunID(rs.getInt(1));
            TipRacuna tr = new TipRacuna();
            tr.setNazivTipaRac("devizni");
            tr.setTipRacunaID(3);
            tr.setTipRacunaID(rs.getInt(2));
            r.setTipRacunaID(tr);
            r.setBrojRacuna(rs.getInt(3));
            r.setTipRacuna(rs.getString(4));
            lista.add(r);
        }
        return lista;
    }

    public List<Profaktura> vratiSveProfakture() throws SQLException {
        List<Profaktura> listaZaposleni = new ArrayList<>();
        String upit = "SELECT * FROM PROFAKTURA";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Profaktura p = new Profaktura();
            p.setProfakturaID(rs.getInt(1));
            p.setDatumPredaje(rs.getDate(2));
            Zaposleni z = ucitajZaposlenog(rs.getInt(3));
            p.setZaposleniID(z);
            Prevoznik pr = ucitajPrevoznika(rs.getInt(4));
            p.setPIBPrevoznika(pr);
            Narucilac n = ucitajNarucioca(rs.getInt(5));
            p.setPIBNarucioca(n);
            Racun r = ucitajRacun(rs.getInt(6));
            p.setRacunID(r);
            p.setUkupno(rs.getDouble(7));
            
            listaZaposleni.add(p);
        }
        return listaZaposleni;
    }

    public boolean obrisiReklamaciju(int parseInt) throws SQLException {
        String upit = "DELETE FROM reklamacija WHERE reklamacijaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, parseInt);
        int uspesno = ps.executeUpdate();
        if (uspesno == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Reklamacija ucitajReklamaciju(int reklamacijaID) throws SQLException {
        Reklamacija r = null;
        String upit = "select * from reklamacija where reklamacijaid = ?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, reklamacijaID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            r = new Reklamacija();
            r.setReklamacijaID(rs.getInt(1));
            r.setDatumKupovine(rs.getDate(2));
            r.setDatumOdobravanja(rs.getDate(3));
            r.setDatumPostavljanja(rs.getDate(4));
            Zaposleni z = ucitajZaposlenog(rs.getInt(5));
            r.setZaposleniid(z);
            r.setIzjava(rs.getString(6));
            Narucilac n = ucitajNarucioca(rs.getInt(7));
            r.setPIBNarucioca(n);
            Racun rac = ucitajRacun(rs.getInt(8));
            r.setRacunID(rac);
            Profaktura p = ucitajProfakturu(rs.getInt(9));
            r.setProfakturaID(p);
            r.setOpisReklamacije(rs.getString(10));
            
            

            List<StavkaReklamacije> listaStavki = new ArrayList<>();
            String upit1 = "select * from STAVKA_REKLAMACIJE sn where reklamacijaID = ?";
            PreparedStatement ps1 = con.prepareStatement(upit1);
            ps1.setInt(1, reklamacijaID);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                StavkaReklamacije sr = new StavkaReklamacije();
                sr.setStavkaReklamacijeID(rs1.getInt(1));
                sr.setReklamacijaID(reklamacijaID);
                sr.setOpisStavke(rs1.getString(3));
                Proizvod proiz = ucitajProizvod(rs1.getInt(4));
                sr.setProzivodID(proiz);
                sr.setOpisReklamacije(rs1.getString(5));
                
                listaStavki.add(sr);
            }
            ps1.close();
            rs1.close();
            r.setStavkeReklamacije(listaStavki);
        }
        ps.close();
        rs.close();
        return r;
    }

    public void izmeniReklamaciju(Reklamacija r) throws SQLException {
        String upit = "UPDATE reklamacija SET DATUMKUPOVINE=?, DATUMODOBRAVANJA=?, DATUMPOSTAVLJANJA=?,"
                + "ZAPOSLENIID=?, IZJAVA=?, PIBNARUCIOCA=?, RACUNID=?, PROFAKTURAID=?, OPISREKLAMACIJE=?"
                + " WHERE REKLAMACIJAID=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setDate(1, (java.sql.Date) new Date(r.getDatumKupovine().getTime()));
        ps.setDate(2, (java.sql.Date) new Date(r.getDatumOdobravanja().getTime()));
        ps.setDate(3, (java.sql.Date) new Date(r.getDatumPostavljanja().getTime()));
        ps.setInt(4, r.getZaposleniid().getZaposleniID());
        ps.setString(5, r.getIzjava());
        ps.setInt(6, r.getPIBNarucioca().getPib());
        ps.setInt(7, r.getRacunID().getRacunID());
        ps.setInt(8, r.getProfakturaID().getProfakturaID());
        ps.setString(9, r.getOpisReklamacije());       
        ps.setInt(10, r.getReklamacijaID());
        ps.executeUpdate();
        for (StavkaReklamacije sr : r.getStavkeReklamacije()) {
            if (sr.getStatus() != null) {
                switch (sr.getStatus()) {
                    case OBRISI:
                        upit = "DELETE FROM stavka_reklamacije WHERE reklamacijaid=? AND stavkareklamacijeid=?";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getReklamacijaID());
                        ps.setInt(2, sr.getStavkaReklamacijeID());
                        ps.executeUpdate();
                        break;
                    case IZMENI:
                        upit = "UPDATE stavka_reklamacije SET opisstavke=?, proizvodid= ? WHERE reklamacijaid=? AND stavkareklamacijeid=?";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getStavkaReklamacijeID());
                        ps.setInt(2, sr.getReklamacijaID());
                        ps.setString(3, sr.getOpisStavke());
                        ps.setInt(4, sr.getProzivodID().getProizvodID());
                        ps.executeUpdate();
                        break;
                    case DODAJ:
                        upit = "INSERT INTO stavka_reklamacije (stavkareklamacijeid,reklamacijaid,opisstavke,proizvodid,opisreklamacije) VALUES(?,?,?,?,?)";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getStavkaReklamacijeID());
                        ps.setInt(2, sr.getReklamacijaID());
                        ps.setString(3, sr.getOpisStavke());
                        ps.setInt(4, sr.getProzivodID().getProizvodID());
                        ps.setString(5, sr.getOpisReklamacije());
                        ps.executeUpdate();
                        break;
                    default:
                        break;
                }
            }
        }
        ps.close();
    }

    public List<Prevoznik> vratiSvePrevoznike() throws SQLException {
       List<Prevoznik> listaZaposleni = new ArrayList<>();
        String upit = "SELECT * FROM Prevoznik";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Prevoznik p = new Prevoznik();
            p.setPib(rs.getInt(1));
            p.setNazivPrevoznika(rs.getString(2));
            Drzava d = ucitajDrzave(rs.getInt(3));
            Mesto m =uitajMesta(rs.getInt(4));
            p.setDrzavaID(d);
            p.setMestoID(m);
            listaZaposleni.add(p);
        }
        return listaZaposleni;
    }

    public void ubaciProfakturu(Profaktura p) throws SQLException {
        String upit = "INSERT INTO profaktura VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, p.getProfakturaID());
        ps.setDate(2,  new Date(p.getDatumPredaje().getTime()));
        ps.setInt(3, p.getZaposleniID().getZaposleniID());
        ps.setInt(4, p.getPIBPrevoznika().getPib());
        ps.setInt(5, p.getPIBNarucioca().getPib());
        ps.setInt(6, p.getRacunID().getRacunID());
        ps.setDouble(7, p.getUkupno());
        System.out.println("do ovde moze");
        int uspesno = ps.executeUpdate();
        ps.close();
        if (uspesno != 0) {
            for (StavkaProfakture sn : p.getStavkeProfakture()) {
                upit = "INSERT INTO stavka_profakture VALUES(?,?,?,?)";
                ps = con.prepareStatement(upit);
                ps.setInt(1, sn.getStavkaProfaktureID());
                ps.setInt(2, sn.getProfakturaID());
                ps.setInt(3, sn.getKolicina());
                ps.setInt(4, sn.getProizvodID().getProizvodID());
                ps.executeUpdate();
            }
            ps.close();
        }
    }

    public Profaktura ucitajProfakturu(int parseInt) throws SQLException {
        Profaktura p = null;
        String upit = "SELECT * FROM profaktura n WHERE n.profakturaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, parseInt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            p = new Profaktura();
            p.setProfakturaID(rs.getInt(1));
            p.setDatumPredaje(rs.getDate(2));
            Zaposleni z = ucitajZaposlenog(rs.getInt(3));
            p.setZaposleniID(z);
            Prevoznik prevoznik = ucitajPrevoznika(rs.getInt(4));
            Narucilac narucilac = ucitajNarucioca(rs.getInt(5));
            p.setPIBPrevoznika(prevoznik);
            p.setPIBNarucioca(narucilac);
            Racun r = ucitajRacun(rs.getInt(6));
            p.setRacunID(r);
            p.setUkupno(rs.getDouble(7));
            
            List<StavkaProfakture> listaStavki = new ArrayList<>();
            String upit1 = "select * from stavka_profakture sp where profakturaid = ?";
            PreparedStatement ps1 = con.prepareStatement(upit1);
            ps1.setInt(1, parseInt);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                StavkaProfakture sn = new StavkaProfakture();
                sn.setStavkaProfaktureID(rs1.getInt(1));
                sn.setProfakturaID(parseInt);
                sn.setKolicina(rs1.getInt(3));
                Proizvod proizvod = ucitajProizvod(rs1.getInt(4));
                sn.setProizvodID(proizvod);
                listaStavki.add(sn);
                
            }
            ps1.close();
            rs1.close();
            p.setStavkeProfakture(listaStavki);
        }
        rs.close();
        ps.close();
        return p;
    }

    public boolean obrisiProfakturu(int parseInt) throws SQLException {
        String upit = "DELETE FROM profaktura WHERE profakturaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, parseInt);
        int uspesno = ps.executeUpdate();
        if (uspesno == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void izmeniProfakturu(Profaktura p) throws SQLException {
        String upit = "UPDATE profaktura SET datumpredaje=?, zaposleniid=?,"
                + "pibprevoznika=?, pibnarucioca=?, racunid=?, ukupno=?"
                + " WHERE profakturaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setDate(1, (java.sql.Date) new Date(p.getDatumPredaje().getTime()));
        ps.setInt(2, p.getZaposleniID().getZaposleniID());
        ps.setInt(3, p.getPIBPrevoznika().getPib());
        ps.setInt(4, p.getPIBNarucioca().getPib());
        ps.setInt(5, p.getRacunID().getRacunID());
        ps.setDouble(6, p.getUkupno());
        ps.setInt(7, p.getProfakturaID());
        ps.executeUpdate();
        for (StavkaProfakture sr : p.getStavkeProfakture()) {
            if (sr.getStatus() != null) {
                switch (sr.getStatus()) {
                    case OBRISI:
                        upit = "DELETE FROM stavka_profakture WHERE profakturaid=? AND stavkaprofaktureid=?";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getProfakturaID());
                        ps.setInt(2, sr.getStavkaProfaktureID());
                        ps.executeUpdate();
                        break;
                    case IZMENI:
                        upit = "UPDATE stavka_profakture SET kolicina=?, proizvodid= ? WHERE profakturaid=? AND stavkaprofaktureid=?";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getKolicina());
                        ps.setInt(2, sr.getProizvodID().getProizvodID());
                        ps.setInt(3, sr.getProfakturaID());
                        ps.setInt(4, sr.getStavkaProfaktureID());
                        ps.executeUpdate();
                        break;
                    case DODAJ:
                        upit = "INSERT INTO stavka_profakture (stavkaprofaktureid,profakturaid,kolicina,proizvodid) VALUES(?,?,?,?)";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getStavkaProfaktureID());
                        ps.setInt(2, sr.getProfakturaID());
                        ps.setInt(3, sr.getKolicina());
                        ps.setInt(4, sr.getProizvodID().getProizvodID());
                        ps.executeUpdate();
                        break;
                    default:
                        break;
                }
            }
        }
        ps.close();
    }

    public List<Proizvod> vratiSveProizvode() throws SQLException {
        List<Proizvod> lista = new ArrayList<>();
        String upit = "SELECT * FROM Proizvod";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Proizvod p = new Proizvod();
            p.setProizvodID(rs.getInt(1));
            p.setOpis(rs.getString(2));
            p.setNaziv(rs.getString(3));
            ModelProizvoda m = ucitajModele(rs.getInt(4));
            p.setModelID(m);
            p.setAktuelnaCena(rs.getDouble(5));
            lista.add(p);
        }
        return lista;
    }

    public List<Drzava> vratiSveDrzave() throws SQLException {
        List<Drzava> lista = new ArrayList<>();
        String upit = "SELECT * FROM Drzava";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Drzava d = new Drzava();
            d.setDrzavaID(rs.getInt(1));
            d.setNazivDrzave(rs.getString(2));
            lista.add(d);
        }
        return lista;
    }

    public List<Mesto> vratiSvaMesta() throws SQLException {
        List<Mesto> lista = new ArrayList<>();
        String upit = "SELECT * FROM Mesto";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Mesto m = new Mesto();
            m.setMestoID(rs.getInt(1));
            m.setNazivMesta(rs.getString(2));
            Drzava d = new Drzava();
            d.setDrzavaID(rs.getInt(3));
            d.setNazivDrzave(rs.getString(4));
            m.setDrzavaID(d);
            lista.add(m);
           
        }
        return lista;
    }

    public void ubaciBanku(Banka b) throws SQLException {
        String upit = "INSERT INTO Banka VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, b.getBankaID());
        ps.setString(2, b.getNazivBanke());
        ps.setInt(3, b.getMestoID().getMestoID());
        ps.setInt(4, b.getDrzavaID().getDrzavaID());
        ps.setString(5, b.getDrzavaID().getNazivDrzave());
        ps.setString(6, b.getMestoID().getNazivMesta());
        ps.executeUpdate();
        ps.close();
    }

    public Banka ucitajBanku(int id) throws SQLException {
        Banka b = null;
        String upit = "SELECT * FROM banka b WHERE b.bankaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            b = new Banka();
            Drzava d = new Drzava();
            Mesto m = new Mesto();
                    
            b.setBankaID(rs.getInt(1));
            b.setNazivBanke(rs.getString(2));
            m.setMestoID(rs.getInt(3));
            d.setDrzavaID(rs.getInt(4));
            d.setNazivDrzave(rs.getString(5));
            m.setNazivMesta(rs.getString(6));
            b.setDrzavaID(d);
            b.setMestoID(m);
        }
        rs.close();
        ps.close();
        return b;
    }

    public void izmeniBanku(Banka b) throws SQLException {
        String upit = "UPDATE banka SET nazivBanke=?, mestoid=?, drzavaid=?, nazivDrzave=?, nazivMesta=? WHERE bankaID=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setString(1, b.getNazivBanke());
        ps.setInt(2, b.getMestoID().getMestoID());
        ps.setInt(3, b.getDrzavaID().getDrzavaID());
        ps.setString(4, b.getDrzavaID().getNazivDrzave());
        ps.setString(5, b.getMestoID().getNazivMesta());
        ps.setInt(6, b.getBankaID());
        ps.executeUpdate();
        ps.close();
     }

    public boolean obrisiBanku(int id) throws SQLException {
        String upit = "DELETE FROM banka WHERE bankaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, id);
        int uspesno = ps.executeUpdate();
        if (uspesno == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void ubaciProizvod(Proizvod p) throws SQLException {
        String upit = "INSERT INTO PROIZVOD VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, p.getProizvodID());
        ps.setString(2, p.getOpis());
        ps.setString(3, p.getNaziv());
        ps.setInt(4, p.getModelID().getModelID());
        ps.setDouble(5, p.getAktuelnaCena());
        int uspesno = ps.executeUpdate();
        ps.close();
        if (uspesno != 0) {
            Cena c = p.getCena();
            upit = "INSERT INTO CENA VALUES(?,?,?,?)";
            ps = con.prepareStatement(upit);
            ps.setInt(1, p.getProizvodID());
            ps.setInt(2, c.getCenaID());
            ps.setDate(3, new Date(c.getDatum().getTime()) );
            ps.setDouble(4, c.getIznos());
            ps.executeUpdate();
        
            ps.close();
        }
    }

    public void izmeniProizvod(Proizvod p) throws SQLException {
        String upit = "UPDATE proizvod SET opis=?, naziv=?, modelid=?, aktuelnacena=? WHERE proizvodid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setString(1, p.getOpis());
        ps.setString(2, p.getNaziv());
        ps.setInt(3, p.getModelID().getModelID());
        ps.setDouble(4, p.getAktuelnaCena());
        ps.setInt(5, p.getProizvodID());
        
        ps.executeUpdate();
        
        String upit2 = "UPDATE cena SET proizvodid=?, cenaid=?, datum=?, iznos=?";
        PreparedStatement ps2 = con.prepareStatement(upit2);
        ps2.setInt(1, p.getProizvodID());
        ps2.setInt(2, p.getCena().getCenaID());
        ps2.setDate(3, new Date(p.getCena().getDatum().getTime()));
        ps2.setDouble(4, p.getCena().getIznos());
        ps2.executeUpdate();
              
    }

    public boolean obrisiProizvod(int proizvodID) throws SQLException {
        String upit = "DELETE FROM proizvod WHERE proizvodid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, proizvodID);
        int uspesno = ps.executeUpdate();
        if (uspesno == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Proizvod ucitajProizvod(int id) throws SQLException {
        Proizvod p = null;
        String upit = "select * from proizvod where proizvodid = ?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            p = new Proizvod();
            p.setProizvodID(rs.getInt(1));
            p.setOpis(rs.getString(2));
            p.setNaziv(rs.getString(3));
            ModelProizvoda m = ucitajModele(rs.getInt(4));
            p.setModelID(m);
            p.setAktuelnaCena(rs.getInt(5));
            
            String upit1 = "select * from CENA c where c.proizvodid = ?";
            PreparedStatement ps1 = con.prepareStatement(upit1);
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
            Cena c = new Cena();
            c.setProizvodID(rs1.getInt(1));
            c.setCenaID(rs1.getInt(2));
            c.setDatum(rs1.getDate(3));
            c.setIznos(rs1.getDouble(4));
            p.setCena(c);
            }
            ps1.close();
            rs1.close();
            
        }
        ps.close();
        rs.close();
        return p;
    }

    public List<ModelProizvoda> vratiSveModele() throws SQLException {
        List<ModelProizvoda> lista = new ArrayList<>();
        String upit = "SELECT * FROM MODELPROIZVODA";
        PreparedStatement ps = con.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ModelProizvoda p = new ModelProizvoda();
            p.setModelID(rs.getInt(1));
            p.setMemorija(rs.getString(2));
            p.setBoja(rs.getString(3));
            p.setOpis(rs.getString(4));
            p.setNazivModelaProizvoda(rs.getString(5));
            
            lista.add(p);
        }
        return lista;
    }

    public void izmeniCenu(Cena c) throws SQLException {
        String upit = "UPDATE cena SET datum=?, iznos=? WHERE proizvodid=? and cenaid =?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setDate(1, new Date(c.getDatum().getTime()));
        ps.setDouble(2, c.getIznos());
        ps.setInt(3, c.getProizvodID());
        ps.setInt(4, c.getCenaID());
        ps.executeUpdate();
        ps.close();
    }

    private ModelProizvoda ucitajModele(int aInt) throws SQLException {
        ModelProizvoda p = new ModelProizvoda();
        String upit = "SELECT * FROM MODELPROIZVODA where modelid = ?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, aInt);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            
            p.setModelID(rs.getInt(1));
            p.setMemorija(rs.getString(2));
            p.setBoja(rs.getString(3));
            p.setOpis(rs.getString(4));
            p.setNazivModelaProizvoda(rs.getString(5));

        }
        return p;
    }

    public void izmeniProizvodLepo(Proizvod p) throws SQLException {
        String upit = "UPDATE proizvod SET opis=?, naziv=?, modelid=? WHERE proizvodid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setString(1, p.getOpis());
        ps.setString(2, p.getNaziv());
        ps.setInt(3, p.getModelID().getModelID());
        ps.setInt(4, p.getProizvodID());
        
        ps.executeUpdate();
        
        String upit2 = "UPDATE cena SET  datum=?, iznos=? where proizvodid=? and cenaid=? ";
        PreparedStatement ps2 = con.prepareStatement(upit2);
        ps2.setDate(1, new Date(p.getCena().getDatum().getTime()));
        ps2.setDouble(2, p.getCena().getIznos());
        ps2.setInt(3, p.getProizvodID());
        ps2.setInt(4, p.getCena().getCenaID());
        
        ps2.executeUpdate();
    }

    private Prevoznik ucitajPrevoznika(int aInt) throws SQLException {
        Prevoznik z = null;
        String upit = "SELECT * FROM prevoznik z WHERE z.pib=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, aInt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            z = new Prevoznik();
            z.setPib(rs.getInt(1));
            z.setNazivPrevoznika(rs.getString(2));
            Drzava d= ucitajDrzave(rs.getInt(3));
            z.setDrzavaID(d);
            Mesto m = uitajMesta(rs.getInt(4));
            z.setMestoID(m);
        }
        rs.close();
        ps.close();
        return z;
    }

    public Drzava ucitajDrzave(int aInt) throws SQLException {
        Drzava z = null;
        String upit = "SELECT * FROM drzava WHERE drzavaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, aInt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            z = new Drzava();
            z.setDrzavaID(rs.getInt(1));
            z.setNazivDrzave(rs.getString(2));
 
        }
        rs.close();
        ps.close();
        return z;
    }

    public Mesto uitajMesta(int aInt) throws SQLException {
        Mesto z = null;
        String upit = "SELECT * FROM mesto z WHERE z.mestoid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, aInt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            z = new Mesto();
            z.setMestoID(rs.getInt(1));
            z.setNazivMesta(rs.getString(2));
            int id = rs.getInt(3);
            String naziv = rs.getString(4);
            Drzava d = new Drzava();
            d.setDrzavaID(id);
            d.setNazivDrzave(naziv);
            z.setDrzavaID(d);
        }
        rs.close();
        ps.close();
        return z;
    }

    public void izmeniReklamacijuDaMenjasStavku(Reklamacija r) throws SQLException {
        String upit = "UPDATE reklamacija SET DATUMKUPOVINE=?, DATUMODOBRAVANJA=?, DATUMPOSTAVLJANJA=?,"
                + "ZAPOSLENIID=?, IZJAVA=?, PIBNARUCIOCA=?, RACUNID=?, PROFAKTURAID=?, OPISREKLAMACIJE=?"
                + " WHERE REKLAMACIJAID=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setDate(1, (java.sql.Date) new Date(r.getDatumKupovine().getTime()));
        ps.setDate(2, (java.sql.Date) new Date(r.getDatumOdobravanja().getTime()));
        ps.setDate(3, (java.sql.Date) new Date(r.getDatumPostavljanja().getTime()));
        ps.setInt(4, r.getZaposleniid().getZaposleniID());
        ps.setString(5, r.getIzjava());
        ps.setInt(6, r.getPIBNarucioca().getPib());
        ps.setInt(7, r.getRacunID().getRacunID());
        ps.setInt(8, r.getProfakturaID().getProfakturaID());
        ps.setString(9, r.getOpisReklamacije());       
        ps.setInt(10, r.getReklamacijaID());
        ps.executeUpdate();
        for (StavkaReklamacije sr : r.getStavkeReklamacije()) {
                        upit = "UPDATE stavka_reklamacije SET opisstavke=?, proizvodid= ?, opisreklamacije=? WHERE reklamacijaid=? AND stavkareklamacijeid=?";
                        ps = con.prepareStatement(upit);
                        ps.setInt(1, sr.getStavkaReklamacijeID());
                        ps.setInt(2, sr.getReklamacijaID());
                        ps.setString(3, sr.getOpisStavke());
                        ps.setInt(4, sr.getProzivodID().getProizvodID());
                        ps.setString(5, sr.getOpisReklamacije());
                        ps.executeUpdate();

                }
       
        ps.close();
    }

    public void ubaciMesto(Mesto m) throws SQLException {
        String upit = "INSERT INTO Mesto VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, m.getMestoID());
        ps.setString(2, m.getNazivMesta());
        ps.setInt(3, m.getDrzavaID().getDrzavaID());
        ps.setString(4, m.getDrzavaID().getNazivDrzave());
        ps.executeUpdate();

        ps.close();
    }

     public void ubaciDrzavu(Drzava d) throws SQLException {
        System.out.println("stignes li");
        String upit = "INSERT INTO Drzava VALUES(?,?)";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, d.getDrzavaID());
        ps.setString(2, d.getNazivDrzave());
        ps.executeUpdate();
        ps.close();
    }

    public void izmeniMesto(Mesto m) throws SQLException {
        String upit = "UPDATE mesto SET  nazivmesta=?, drzavaid=?, nazivdrzave=? WHERE mestoid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setString(1, m.getNazivMesta());
        ps.setInt(2, m.getDrzavaID().getDrzavaID());
        ps.setString(3, m.getDrzavaID().getNazivDrzave());
        ps.setInt(4, m.getMestoID());
       
        ps.executeUpdate();
        ps.close();
    }
    public void izmeniDrzavu(Drzava m) throws SQLException {
        String upit = "UPDATE drzava SET nazivdrzave=? WHERE drzavaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setString(1, m.getNazivDrzave());
        ps.setInt(2, m.getDrzavaID());
       
        ps.executeUpdate();
        ps.close();
    }

    public boolean obrisiMesto(int parseInt) throws SQLException {
        String upit = "DELETE FROM mesto WHERE mestoid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, parseInt);
        int uspesno = ps.executeUpdate();
        if (uspesno == 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean obrisiDrzavu(int parseInt) throws SQLException {
        String upit = "DELETE FROM drzava WHERE drzavaid=?";
        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, parseInt);
        int uspesno = ps.executeUpdate();
        if (uspesno == 0) {
            return false;
        } else {
            return true;
        }
    }


}



