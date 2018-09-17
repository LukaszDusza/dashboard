package com.dfsp.dashboard.dtos;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Properties {

    String dateFrom;
    String dateTo;
    String agent;
    String dyrektorSegmentu;
    String dyrektorSektora;
    String kanalDystrybucji;
    String kierownikZespolu;
    String miasto;
    String nazwaProduktu;
    String nazwaSektoraSprzedazy;
    String platnosc;
    String segmentSprzedazy;
    BigDecimal fromAmount;
    BigDecimal toAmount;
    String statusUmowy;
    String uzytkownik;
}
