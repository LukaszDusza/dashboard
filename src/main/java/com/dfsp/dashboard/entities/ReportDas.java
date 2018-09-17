package com.dfsp.dashboard.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "raport_das")
public class ReportDas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long numerKalkulacji;
    //@Type(type="date")
    private Date dataKalkulacji;
    private int numerUmowy;
    // @Type(type="date")
    private Date dataZawarcia;
    private Date wazneOd;
    private Date wazneDo;
    private String nazwaProduktu;

    @Column(name = "status")
    private String statusUmowy;

    private BigDecimal skladka;
    private String platnosc;

    @Column(name = "nazwaagenta")
    private String agent;

    private String nrWewnAgenta;
    private String nrKnfAgenta;
    private String uzytkownik;
    private String nrKnfUzytkownika;
    private String emailUzytkownika;
    private byte aktywny;
    private byte zablokowany;
    private int poziom1;
    private String kanalDystrybucji;
    private String poziom1KNF;
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

    @Column(name = "drekspertsegmentu")
    private String dyrektorSegmentu;

    private String poziom5knf;
    private int poziom6;
    private String miasto;
    private String poziom6knf;
    private int poziom7;
    private String mzaKierownikZespolu;
    private String poziom7knf;
    private int numberOfContract;


}








