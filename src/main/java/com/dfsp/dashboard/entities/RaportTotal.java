package com.dfsp.dashboard.entities;


import com.dfsp.dashboard.config.Constans;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = Constans.TABLE_NAME)
public class RaportTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numer_kalkulacji")
    private String numerKalkulacji;

    @Column(name = "data_kalkulacji")
    private Date dataKalkulacji;

    @Column(name = "numer_umowy")
    private String numerUmowy;

    @Column(name = "data_zawarcia")
    private Date dataZawarcia;

    @Column(name = "wazne_od")
    private Date wazneOd;

    @Column(name = "wazne_do")
    private Date wazneDo;

    @Column(name = "nazwa_produktu")
    private String nazwaProduktu;

    @Column(name = "status")
    private String statusUmowy;

    @Column(name = "skladka")
    private BigDecimal skladka;

    @Column(name = "platnosc")
    private String platnosc;

    @Column(name = "nazwa_agenta")
    private String agent;

    @Column(name = "nr_wewnetrzny_agenta")
    private String nrWewnAgenta;

    @Column(name = "nr_knf_agenta")
    private String nrKnfAgenta;

    @Column(name = "uzytkownik")
    private String uzytkownik;

    @Column(name = "nr_knf_uzytkownika")
    private String nrKnfUzytkownika;

    @Column(name = "email_uzytkownika")
    private String emailUzytkownika;

    @Column(name = "aktywny")
    private String aktywny;

    @Column(name = "zablokowany")
    private String zablokowany;

    @Column(name = "poziom_1_nr_wewnetrzny")
    private String poziom1;

    @Column(name = "kanal_dystrybucji")
    private String kanalDystrybucji;

    @Column(name = "poziom_nr_1_knf")
    private String poziom1KNF;

    @Column(name = "poziom_nr_2_wewnetrzny")
    private String poziom2;

    @Column(name = "nazwa_sektora_sprzedazy")
    private String nazwaSektoraSprzedazy;

    @Column(name = "poziom_nr_2_knf")
    private String poziom2KNF;

    @Column(name = "poziom_nr_3_wewnetrzny")
    private String poziom3;

    @Column(name = "dyrektor_sektora")
    private String dyrektorSektora;

    @Column(name = "poziom_nr_3_knf")
    private String poziom3KNF;

    @Column(name = "poziom_nr_4_wewnetrzny")
    private String poziom4;

    @Column(name = "nazwa_segmentu_sprzedazy")
    private String segmentSprzedazy;

    @Column(name = "poziom_nr_4_knf")
    private String poziom4knf;

    @Column(name = "poziom_nr_5_wewnetrzny")
    private String poziom5;

    @Column(name = "dyrektor_ekspert_segmentu")
    private String dyrektorSegmentu;

    @Column(name = "poziom_nr_5_knf")
    private String poziom5knf;

    @Column(name = "poziom_nr_6_wewnetrzny")
    private String poziom6;

    @Column(name = "nr_zespolu_sprzedazy_miasto")
    private String miasto;

    @Column(name = "poziom_nr_6_knf")
    private String poziom6knf;

    @Column(name = "poziom_nr_7_wewnetrzny")
    private String poziom7;

    @Column(name = "mza_kierownik_zespolu")
    private String mzaKierownikZespolu;

    @Column(name = "poziom_nr_7_knf")
    private String poziom7knf;


    @Column(name = "telefon_uzytkownika")
    private String telefonUzytkownika;

    //   @Column(name = "")
    //   private int numberOfContract;


    public RaportTotal( String numerKalkulacji,
                        Date dataKalkulacji,
                        String numerUmowy,
                        Date dataZawarcia,
                        Date wazneOd,
                        Date wazneDo,
                        String nazwaProduktu,
                        String statusUmowy,
                        BigDecimal skladka,
                        String platnosc,
                        String agent,
                        String nrWewnAgenta,
                        String uzytkownik,
                        String emailUzytkownika,
                        String kanalDystrybucji,
                        String nazwaSektoraSprzedazy,
                        String dyrektorSektora,
                        String segmentSprzedazy,
                        String dyrektorSegmentu,
                        String miasto,
                        String mzaKierownikZespolu,
                        String telefonUzytkownika) {

        this.numerKalkulacji = numerKalkulacji;
        this.dataKalkulacji = dataKalkulacji;
        this.numerUmowy = numerUmowy;
        this.dataZawarcia = dataZawarcia;
        this.wazneOd = wazneOd;
        this.wazneDo = wazneDo;
        this.nazwaProduktu = nazwaProduktu;
        this.statusUmowy = statusUmowy;
        this.skladka = skladka;
        this.platnosc = platnosc;
        this.agent = agent;
        this.nrWewnAgenta = nrWewnAgenta;
        this.uzytkownik = uzytkownik;
        this.emailUzytkownika = emailUzytkownika;
        this.kanalDystrybucji = kanalDystrybucji;
        this.nazwaSektoraSprzedazy = nazwaSektoraSprzedazy;
        this.dyrektorSektora = dyrektorSektora;
        this.segmentSprzedazy = segmentSprzedazy;
        this.dyrektorSegmentu = dyrektorSegmentu;
        this.miasto = miasto;
        this.mzaKierownikZespolu = mzaKierownikZespolu;
        this.telefonUzytkownika = telefonUzytkownika;
    }
}








