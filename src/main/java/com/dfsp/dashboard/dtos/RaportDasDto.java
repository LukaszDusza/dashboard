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
    private String status;
    private BigDecimal skladka;
    private String platnosc;
    private String nazwaAgenta;
    private String uzytkownik;
    private String kanalDystrybucji;
    private String nazwaSektoraSprzedazy;
    private String dyrektorSektora;
    private String segmentSprzedazy;
    private String drEkspertSegmentu;
    private String miasto;
    private String mzaKierownikZespolu;

}
