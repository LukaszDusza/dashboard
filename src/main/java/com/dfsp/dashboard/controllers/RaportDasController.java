package com.dfsp.dashboard.controllers;


import com.dfsp.dashboard.entities.RaportDas;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/raportdas")
public class RaportDasController {

    @Autowired
    RaportDasRepository raportDasRepository;

    @GetMapping("/all")
    public List<RaportDas> getRaportDasAll() {

        return raportDasRepository.findAll();
    }

}
