package com.dfsp.dashboard.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaportDasDto {

    private Date dataKalkulacji;
    private Date dataZawarcia;
    private String nazwaProduktu;
    private String statusUmowy;
    private BigDecimal skladka;
    private String platnosc;
    private String agent;
    private String uzytkownik;
    private String kanalDystrybucji;
    private String nazwaSektoraSprzedazy;
    private String dyrektorSektora;
    private String segmentSprzedazy;
    private String dyrektorSegmentu;
    private String miasto;
    private String kierownikZespolu;

}
