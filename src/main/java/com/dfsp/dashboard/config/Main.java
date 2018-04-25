package com.dfsp.dashboard.config;

import com.dfsp.dashboard.controllers.RaportDasController;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        for (String entry: RaportDasController.headerFilters("value")) {
            System.out.println(entry);
        }
    }
}
