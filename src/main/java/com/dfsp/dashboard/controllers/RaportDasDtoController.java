package com.dfsp.dashboard.controllers;


import com.dfsp.dashboard.app.DateParser;
import com.dfsp.dashboard.dtos.RaportDasDto;
import com.dfsp.dashboard.entities.ReportDas;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class RaportDasDtoController {

    @Autowired
    RaportDasRepository raportDasRepository;

  //  @GetMapping("all")
    public List<RaportDasDto> getRaportDasDtoAll() {
        List<ReportDas> results = raportDasRepository.findAll();
   //     List<ReportDas> results = byDate(dateFrom, dateTo);
        ModelMapper modelMapper = new ModelMapper();
        List<RaportDasDto> dtos = new ArrayList<>();
        for (ReportDas r: results) {
            RaportDasDto raportDasDto = new RaportDasDto();
            modelMapper.map(r, raportDasDto);
            dtos.add(raportDasDto);
        }
        return dtos;
    }

    @GetMapping("{dateFrom}/{dateTo}/agrement/{agrementStatus}")
    public List<RaportDasDto> agrementStatus(@PathVariable String agrementStatus) {

        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filterList = dtos.stream()
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filterList;
    }

    @GetMapping("agrement/{agrementStatus}/sum")
    public Map<String, BigDecimal> agrementStatusSum(@PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = agrementStatus(agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    @GetMapping("city/{prop}/{agrementStatus}")
    public List<RaportDasDto> cityName(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filteredList = dtos.stream()
                .filter(r -> r.getMiasto().equals(prop))
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filteredList;
    }

    @GetMapping("city/{prop}/{agrementStatus}/sum")
    public Map<String, BigDecimal> cityNameSum(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = cityName(prop, agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    @GetMapping("distibution/{prop}/{agrementStatus}")
    public List<RaportDasDto> distribution(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filteredList = dtos.stream()
                .filter(r -> r.getKanalDystrybucji().equals(prop))
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filteredList;
    }

    @GetMapping("distibution/{prop}/{agrementStatus}/sum")
    public Map<String, BigDecimal> distributionSum(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = distribution(prop, agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    @GetMapping("sales/{prop}/{agrementStatus}")
    public List<RaportDasDto> salesSector(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filteredList = dtos.stream()
                .filter(r -> r.getNazwaSektoraSprzedazy().equals(prop))
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filteredList;
    }

    @GetMapping("sales/{prop}/{agrementStatus}/sum")
    public Map<String, BigDecimal> salesSectorSum(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = salesSector(prop, agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    @GetMapping("segment/{prop}/{agrementStatus}")
    public List<RaportDasDto> segment(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filteredList = dtos.stream()
                .filter(r -> r.getSegmentSprzedazy().equals(prop))
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filteredList;
    }

    @GetMapping("segment/{prop}/{agrementStatus}/sum")
    public Map<String, BigDecimal> segmentSum(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = segment(prop, agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    @GetMapping("manager/{prop}/{agrementStatus}")
    public List<RaportDasDto> manager(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filteredList = dtos.stream()
                .filter(r -> r.getMzaKierownikZespolu().equals(prop))
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filteredList;
    }

    @GetMapping("manager/{prop}/{agrementStatus}/sum")
    public Map<String, BigDecimal> managerSum(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = manager(prop, agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    @GetMapping("agent/{prop}/{agrementStatus}")
    public List<RaportDasDto> agent(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filteredList = dtos.stream()
                .filter(r -> r.getNazwaAgenta().equals(prop))
                .filter(r -> r.getStatus().equals(agrementStatus))
                .collect(Collectors.toList());
        return filteredList;
    }

    @GetMapping("agent/{prop}/{agrementStatus}/sum")
    public Map<String, BigDecimal> agentSum(@PathVariable String prop, @PathVariable String agrementStatus) {
        List<RaportDasDto> dtos = agent(prop, agrementStatus);
        List<BigDecimal> amount = dtos.stream()
                .map(r -> r.getSkladka())
                .collect(Collectors.toList());
        BigDecimal bd = new BigDecimal(0);
        for (BigDecimal a: amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    public List<ReportDas> byDate(String dateFrom, String dateTo) {
        List<ReportDas> listByDates = raportDasRepository.findByDate(
                DateParser.toSqlDate(dateFrom),
                DateParser.toSqlDate(dateTo));
        return listByDates;
    }


}
