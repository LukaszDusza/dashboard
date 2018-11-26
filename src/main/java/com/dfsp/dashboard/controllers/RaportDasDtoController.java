package com.dfsp.dashboard.controllers;


import com.dfsp.dashboard.app.DateParser;
import com.dfsp.dashboard.dtos.Properties;
import com.dfsp.dashboard.dtos.RaportDasDto;
import com.dfsp.dashboard.entities.RaportTotal;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class RaportDasDtoController {


    private RaportDasRepository raportDasRepository;

    public RaportDasDtoController(RaportDasRepository raportDasRepository) {
        this.raportDasRepository = raportDasRepository;
    }


    //  @GetMapping("all")
    public List<RaportDasDto> getRaportDasDtoAll() {
        List<RaportTotal> results = raportDasRepository.findAll();
        //     List<RaportTotal> results = byDate(dateFrom, dateTo);
        ModelMapper modelMapper = new ModelMapper();
        List<RaportDasDto> dtos = new ArrayList<>();
        for (RaportTotal r : results) {
            RaportDasDto raportDasDto = new RaportDasDto();
            modelMapper.map(r, raportDasDto);
            dtos.add(raportDasDto);
        }
        return dtos;
    }


//    @GetMapping("user")
//    public Principal user(Principal user) {
//        return user;
//    }

//    @Configuration
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .httpBasic()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/index.html", "/", "/home", "/login").permitAll()
//                    .anyRequest().authenticated();
//        }
//    }


    @GetMapping("{dateFrom}/{dateTo}/agrement/{agrementStatus}")
    public List<RaportDasDto> agrementStatus(@PathVariable String agrementStatus) {

        List<RaportDasDto> dtos = getRaportDasDtoAll();
        List<RaportDasDto> filterList = dtos.stream()
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
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
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
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
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
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
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
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
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
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
                .filter(r -> r.getKierownikZespolu().equals(prop))
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
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
                .filter(r -> r.getAgent().equals(prop))
                .filter(r -> r.getStatusUmowy().equals(agrementStatus))
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
        for (BigDecimal a : amount) {
            bd = bd.add(a);
        }
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("summary", bd);
        return result;
    }

    public List<RaportTotal> byDate(String dateFrom, String dateTo) throws ParseException {
        List<RaportTotal> listByDates = raportDasRepository.findByDate(
                DateParser.toSqlDate(dateFrom),
                DateParser.toSqlDate(dateTo));
        return listByDates;
    }

    @GetMapping("raport")
    public List<RaportDasDto> findByDateRaportDasDto(@RequestParam(value = "dateFrom", required = false) String dateFrom, @RequestParam(value = "dateTo", required = false) String dateTo) throws ParseException {

        if ((dateTo == null) || (dateTo.equals("")) || (dateTo.equals(" "))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            dateTo = dateFormat.format(date);
        }

        if ((dateFrom == null) || (dateFrom.equals("")) || (dateFrom.equals(" "))) {
            dateFrom = "1900-01-01";
        }

        List<RaportTotal> results = raportDasRepository.findByDate(
                DateParser.toSqlDate(dateFrom),
                DateParser.toSqlDate(dateTo));

        ModelMapper modelMapper = new ModelMapper();
        List<RaportDasDto> dtos = new ArrayList<>();
        for (RaportTotal r : results) {
            RaportDasDto raportDasDto = new RaportDasDto();
            modelMapper.map(r, raportDasDto);
            dtos.add(raportDasDto);
        }
        return dtos;
    }

    @PostMapping("raport/stat")
    public ResponseEntity findByProp(@Valid @RequestBody Properties properties) throws ParseException {
        System.out.println(properties);

        List<RaportTotal> result = findByDateAndProperties(

                properties.getDateFrom(),
                properties.getDateTo(),
                properties.getAgent(),
                properties.getDyrektorSegmentu(),
                properties.getDyrektorSektora(),
                properties.getKanalDystrybucji(),
                properties.getKierownikZespolu(),
                properties.getMiasto(),
                properties.getNazwaProduktu(),
                properties.getNazwaSektoraSprzedazy(),
                properties.getPlatnosc(),
                properties.getSegmentSprzedazy(),
                properties.getFromAmount(),
                properties.getToAmount(),
                properties.getStatusUmowy(),
                properties.getUzytkownik()
        );

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("raport/stat")
    public List<RaportTotal> findByDateAndProperties(
            @RequestParam(value = "dateFrom", required = false) String dateFrom,
            @RequestParam(value = "dateTo", required = false) String dateTo,
            @RequestParam(value = "agent", required = false) String agent,
            @RequestParam(value = "dyrektorSegmentu", required = false) String dyrektorSegmentu,
            @RequestParam(value = "dyrektorSektora", required = false) String dyrektorSektora,
            @RequestParam(value = "kanalDystrybucji", required = false) String kanalDystrybucji,
            @RequestParam(value = "kierownikZespolu", required = false) String kierownikZespolu,
            @RequestParam(value = "miasto", required = false) String miasto,
            @RequestParam(value = "nazwaProduktu", required = false) String nazwaProduktu,
            @RequestParam(value = "nazwaSektoraSprzedazy", required = false) String nazwaSektoraSprzedazy,
            @RequestParam(value = "platnosc", required = false) String platnosc,
            @RequestParam(value = "segmentSprzedazy", required = false) String segmentSprzedazy,
            @RequestParam(value = "fromAmount", required = false) BigDecimal fromAmount,
            @RequestParam(value = "toAmount", required = false) BigDecimal toAmount,
            @RequestParam(value = "statusUmowy", required = false) String statusUmowy,
            @RequestParam(value = "uzytkownik", required = false) String uzytkownik) throws ParseException {

        if (fromAmount == null) {
            fromAmount = new BigDecimal(0.00);
        }

        if (toAmount == null) {
            toAmount = new BigDecimal(9_999_999.99);
        }

        if (dateTo == null || dateTo.equals("")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            dateTo = dateFormat.format(date);
        }

        if (dateFrom == null || dateFrom.equals("")) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -24);
            dateFrom = dateFormat.format(calendar.getTime());
        }

        if (agent == null) {
            agent = "";
        }

        if (dyrektorSegmentu == null) {
            dyrektorSegmentu = "";
        }

        if (dyrektorSektora == null) {
            dyrektorSektora = "";
        }

        if (kanalDystrybucji == null) {
            kanalDystrybucji = "";
        }

        if (kierownikZespolu == null) {
            kierownikZespolu = "";
        }

        if (miasto == null) {
            miasto = "";
        }

        if (nazwaProduktu == null) {
            nazwaProduktu = "";
        }

        if (nazwaSektoraSprzedazy == null) {
            nazwaSektoraSprzedazy = "";
        }

        if (platnosc == null) {
            platnosc = "";
        }

        if (segmentSprzedazy == null) {
            segmentSprzedazy = "";
        }

        if (statusUmowy == null) {
            statusUmowy = "";
        }

        if (uzytkownik == null) {
            uzytkownik = "";
        }


        List<RaportTotal> results = raportDasRepository.raportByDateAndProperties(
                DateParser.toSqlDate(dateFrom),
                DateParser.toSqlDate(dateTo),
                agent,
                dyrektorSegmentu,
                dyrektorSektora,
                kanalDystrybucji,
                kierownikZespolu,
                miasto,
                nazwaProduktu,
                nazwaSektoraSprzedazy,
                platnosc,
                segmentSprzedazy,
                fromAmount,
                toAmount,
                statusUmowy,
                uzytkownik);

        //nie uzywane mapowanie na dtos.
        //   ModelMapper modelMapper = new ModelMapper();
        //   List<RaportDasDto> dtos = new ArrayList<>();

        //  for (RaportTotal r : results) {
        //      RaportDasDto raportDasDto = new RaportDasDto();
        //     modelMapper.map(r, raportDasDto);
        //     dtos.add(raportDasDto);
        //   }
        return results;
    }

}
