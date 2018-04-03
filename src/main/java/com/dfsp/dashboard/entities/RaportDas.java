package com.dfsp.dashboard.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "raport_das")
public class RaportDas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numerKalkulacji;
    private Date dataKalkulacji;
    private  int numerUmowy;
    private  Date dataZawarcia;
    private  Date wazneOd;
    private  Date wazneDo;
    private String nazwaProduktu;
    private String status;
    private BigDecimal skladka;
    private String platnosc;
    private String nazwaAgenta;
    private String nrWewnAgenta;
    private String nrKnfAgenta;
    private String uzytkownik;
    private String nrKnfUzytkownika;
    private String emailUzytkownika;
    private byte aktywny;
    private byte zablokowany;
    private int poziom1;
    private String kanalDystrybucji;
    private String  poziom1KNF;
    private int poziom2;
    private String nazwaSektoraSprzedazy;
    private String poziom2KNF;
    private int poziom3;
    private String dyrektorSektora;
    private String poziom3KNF;
    private int poziom4;
    private String segmentSprzedazy;
    private String poziom4knf;
    private int poziom5;
    private String drEkspertSegmentu;
    private String poziom5knf;
    private int poziom6;
    private String miasto;
    private String poziom6knf;
    private int poziom7;
    private String mzaKierownikZespolu;
    private String poziom7knf;
    private int numberOfContract;

    public RaportDas() {

    }

    public RaportDas(String nazwaAgenta, BigDecimal skladka) {
        this.nazwaAgenta = nazwaAgenta;
        this.skladka = skladka;
    }

    public RaportDas(String nazwaAgenta,BigDecimal skladka, String nrWewnAgenta, String nrKnfAgenta, String uzytkownik, String nrKnfUzytkownika, String kanalDystrybucji, String poziom1KNF, int poziom2, String nazwaSektoraSprzedazy, String poziom2KNF, int poziom3, String dyrektorSektora, String poziom3KNF, int poziom4, String segmentSprzedazy, String poziom4knf, int poziom5, String drEkspertSegmentu, String poziom5knf, int poziom6, String miasto, String poziom6knf, int poziom7, String mzaKierownikZespolu, String poziom7knf, int numberOfContract) {
        this.nazwaAgenta = nazwaAgenta;
        this.skladka = skladka;
        this.nrWewnAgenta = nrWewnAgenta;
        this.nrKnfAgenta = nrKnfAgenta;
        this.uzytkownik = uzytkownik;
        this.nrKnfUzytkownika = nrKnfUzytkownika;
        this.kanalDystrybucji = kanalDystrybucji;
        this.poziom1KNF = poziom1KNF;
        this.poziom2 = poziom2;
        this.nazwaSektoraSprzedazy = nazwaSektoraSprzedazy;
        this.poziom2KNF = poziom2KNF;
        this.poziom3 = poziom3;
        this.dyrektorSektora = dyrektorSektora;
        this.poziom3KNF = poziom3KNF;
        this.poziom4 = poziom4;
        this.segmentSprzedazy = segmentSprzedazy;
        this.poziom4knf = poziom4knf;
        this.poziom5 = poziom5;
        this.drEkspertSegmentu = drEkspertSegmentu;
        this.poziom5knf = poziom5knf;
        this.poziom6 = poziom6;
        this.miasto = miasto;
        this.poziom6knf = poziom6knf;
        this.poziom7 = poziom7;
        this.mzaKierownikZespolu = mzaKierownikZespolu;
        this.poziom7knf = poziom7knf;
        this.numberOfContract = numberOfContract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumerKalkulacji() {
        return numerKalkulacji;
    }

    public void setNumerKalkulacji(Long numerKalkulacji) {
        this.numerKalkulacji = numerKalkulacji;
    }

    public Date getDataKalkulacji() {
        return dataKalkulacji;
    }

    public void setDataKalkulacji(Date dataKalkulacji) {
        this.dataKalkulacji = dataKalkulacji;
    }

    public int getNumerUmowy() {
        return numerUmowy;
    }

    public void setNumerUmowy(int numerUmowy) {
        this.numerUmowy = numerUmowy;
    }

    public Date getDataZawarcia() {
        return dataZawarcia;
    }

    public void setDataZawarcia(Date dataZawarcia) {
        this.dataZawarcia = dataZawarcia;
    }

    public Date getWazneOd() {
        return wazneOd;
    }

    public void setWazneOd(Date wazneOd) {
        this.wazneOd = wazneOd;
    }

    public Date getWazneDo() {
        return wazneDo;
    }

    public void setWazneDo(Date wazneDo) {
        this.wazneDo = wazneDo;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSkladka() {
        return skladka;
    }

    public void setSkladka(BigDecimal skladka) {
        this.skladka = skladka;
    }

    public String getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(String platnosc) {
        this.platnosc = platnosc;
    }

    public String getNazwaAgenta() {
        return nazwaAgenta;
    }

    public void setNazwaAgenta(String nazwaAgenta) {
        this.nazwaAgenta = nazwaAgenta;
    }

    public String getNrWewnAgenta() {
        return nrWewnAgenta;
    }

    public void setNrWewnAgenta(String nrWewnAgenta) {
        this.nrWewnAgenta = nrWewnAgenta;
    }

    public String getNrKnfAgenta() {
        return nrKnfAgenta;
    }

    public void setNrKnfAgenta(String nrKnfAgenta) {
        this.nrKnfAgenta = nrKnfAgenta;
    }

    public String getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(String uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public String getNrKnfUzytkownika() {
        return nrKnfUzytkownika;
    }

    public void setNrKnfUzytkownika(String nrKnfUzytkownika) {
        this.nrKnfUzytkownika = nrKnfUzytkownika;
    }

    public String getEmailUzytkownika() {
        return emailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        this.emailUzytkownika = emailUzytkownika;
    }

    public byte getAktywny() {
        return aktywny;
    }

    public void setAktywny(byte aktywny) {
        this.aktywny = aktywny;
    }

    public byte getZablokowany() {
        return zablokowany;
    }

    public void setZablokowany(byte zablokowany) {
        this.zablokowany = zablokowany;
    }

    public int getPoziom1() {
        return poziom1;
    }

    public void setPoziom1(int poziom1) {
        this.poziom1 = poziom1;
    }

    public String getKanalDystrybucji() {
        return kanalDystrybucji;
    }

    public void setKanalDystrybucji(String kanalDystrybucji) {
        this.kanalDystrybucji = kanalDystrybucji;
    }

    public String getPoziom1KNF() {
        return poziom1KNF;
    }

    public void setPoziom1KNF(String poziom1KNF) {
        this.poziom1KNF = poziom1KNF;
    }

    public int getPoziom2() {
        return poziom2;
    }

    public void setPoziom2(int poziom2) {
        this.poziom2 = poziom2;
    }

    public String getNazwaSektoraSprzedazy() {
        return nazwaSektoraSprzedazy;
    }

    public void setNazwaSektoraSprzedazy(String nazwaSektoraSprzedazy) {
        this.nazwaSektoraSprzedazy = nazwaSektoraSprzedazy;
    }

    public String getPoziom2KNF() {
        return poziom2KNF;
    }

    public void setPoziom2KNF(String poziom2KNF) {
        this.poziom2KNF = poziom2KNF;
    }

    public int getPoziom3() {
        return poziom3;
    }

    public void setPoziom3(int poziom3) {
        this.poziom3 = poziom3;
    }

    public String getDyrektorSektora() {
        return dyrektorSektora;
    }

    public void setDyrektorSektora(String dyrektorSektora) {
        this.dyrektorSektora = dyrektorSektora;
    }

    public String getPoziom3KNF() {
        return poziom3KNF;
    }

    public void setPoziom3KNF(String poziom3KNF) {
        this.poziom3KNF = poziom3KNF;
    }

    public int getPoziom4() {
        return poziom4;
    }

    public void setPoziom4(int poziom4) {
        this.poziom4 = poziom4;
    }

    public String getSegmentSprzedazy() {
        return segmentSprzedazy;
    }

    public void setSegmentSprzedazy(String segmentSprzedazy) {
        this.segmentSprzedazy = segmentSprzedazy;
    }

    public String getPoziom4knf() {
        return poziom4knf;
    }

    public void setPoziom4knf(String poziom4knf) {
        this.poziom4knf = poziom4knf;
    }

    public int getPoziom5() {
        return poziom5;
    }

    public void setPoziom5(int poziom5) {
        this.poziom5 = poziom5;
    }

    public String getDrEkspertSegmentu() {
        return drEkspertSegmentu;
    }

    public void setDrEkspertSegmentu(String drEkspertSegmentu) {
        this.drEkspertSegmentu = drEkspertSegmentu;
    }

    public String getPoziom5knf() {
        return poziom5knf;
    }

    public void setPoziom5knf(String poziom5knf) {
        this.poziom5knf = poziom5knf;
    }

    public int getPoziom6() {
        return poziom6;
    }

    public void setPoziom6(int poziom6) {
        this.poziom6 = poziom6;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getPoziom6knf() {
        return poziom6knf;
    }

    public void setPoziom6knf(String poziom6knf) {
        this.poziom6knf = poziom6knf;
    }

    public int getPoziom7() {
        return poziom7;
    }

    public void setPoziom7(int poziom7) {
        this.poziom7 = poziom7;
    }

    public String getMzaKierownikZespolu() {
        return mzaKierownikZespolu;
    }

    public void setMzaKierownikZespolu(String mzaKierownikZespolu) {
        this.mzaKierownikZespolu = mzaKierownikZespolu;
    }

    public String getPoziom7knf() {
        return poziom7knf;
    }

    public void setPoziom7knf(String poziom7knf) {
        this.poziom7knf = poziom7knf;
    }

    public int getNumberOfContract() {
        return numberOfContract;
    }

    public void setNumberOfContract(int numberOfContract) {
        this.numberOfContract = numberOfContract;
    }
}



