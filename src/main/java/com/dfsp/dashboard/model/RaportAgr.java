package com.dfsp.dashboard.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaportAgr {

    private String agent;
    private String kanalDystrybucji;
    private String nazwaSektoraSprzedazy;
    private String dyrektorSektora;
    private String segmentSprzedazy;
    private String dyrektorSegmentu;
    private String mzaKierownikZespolu;
    private String miasto;
    private String nrWewAgenta;
    private int quantity;
    private int amount;

}
