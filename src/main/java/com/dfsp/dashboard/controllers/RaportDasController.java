package com.dfsp.dashboard.controllers;


import com.dfsp.dashboard.entities.RaportDas;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping("/usersum")
    public List<RaportDas> getRaportDasByUser() {
        List<RaportDas> report = raportDasRepository.findAll();
        Map<String, RaportDas> hm = new LinkedHashMap<>();

        for (RaportDas r : report) {
            String key = r.getNazwaAgenta();
         //   RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(n, r.getSkladka()));
            RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(
                    n,
                  //  r.getSkladka(),
                    new BigDecimal(0),
                    r.getNrWewnAgenta(),
                    r.getNrKnfAgenta(),
                    r.getUzytkownik(),
                    r.getNrKnfUzytkownika(),
                    r.getKanalDystrybucji(),
                    r.getPoziom1KNF(),
                    r.getPoziom2(),
                    r.getNazwaSektoraSprzedazy(),
                    r.getPoziom2KNF(),
                    r.getPoziom3(),
                    r.getDyrektorSektora(),
                    r.getPoziom3KNF(),
                    r.getPoziom4(),
                    r.getSegmentSprzedazy(),
                    r.getPoziom4knf(),
                    r.getPoziom5(),
                    r.getDrEkspertSegmentu(),
                    r.getPoziom5knf(),
                    r.getPoziom6(),
                    r.getMiasto(),
                    r.getPoziom6knf(),
                    r.getPoziom7(),
                    r.getMzaKierownikZespolu(),
                    r.getPoziom7knf(),
                    0
            ));

            raportDas.setSkladka(raportDas.getSkladka().add(r.getSkladka()));
            raportDas.setNumberOfContract(raportDas.getNumberOfContract() + r.getNumberOfContract());
        }
        return new ArrayList<>(hm.values());
    }

    @GetMapping("/date/{dateFrom}/{dateTo}/{status}")
    public List<RaportDas> getRaportDasByDateSummary(
            @PathVariable String dateFrom,
            @PathVariable String dateTo,
            @PathVariable String status) {

        java.sql.Date dateFromFormat = null;
        java.sql.Date dateToFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedFrom = format.parse(dateFrom);
            Date parsedTo = format.parse(dateTo);

            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
            dateToFormat = new java.sql.Date(parsedTo.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<RaportDas> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
        Map<String, RaportDas> hm = new LinkedHashMap<>();

        for (RaportDas r : result) {
            String key = r.getNazwaAgenta();
            //   RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(n, r.getSkladka()));
            RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(
                    n,
                    //  r.getSkladka(),
                    new BigDecimal(0),
                    r.getNrWewnAgenta(),
                    r.getNrKnfAgenta(),
                    r.getUzytkownik(),
                    r.getNrKnfUzytkownika(),
                    r.getKanalDystrybucji(),
                    r.getPoziom1KNF(),
                    r.getPoziom2(),
                    r.getNazwaSektoraSprzedazy(),
                    r.getPoziom2KNF(),
                    r.getPoziom3(),
                    r.getDyrektorSektora(),
                    r.getPoziom3KNF(),
                    r.getPoziom4(),
                    r.getSegmentSprzedazy(),
                    r.getPoziom4knf(),
                    r.getPoziom5(),
                    r.getDrEkspertSegmentu(),
                    r.getPoziom5knf(),
                    r.getPoziom6(),
                    r.getMiasto(),
                    r.getPoziom6knf(),
                    r.getPoziom7(),
                    r.getMzaKierownikZespolu(),
                    r.getPoziom7knf(),
                    0
            ));
            raportDas.setSkladka(raportDas.getSkladka().add(r.getSkladka()));
            raportDas.setNumberOfContract(raportDas.getNumberOfContract() + r.getNumberOfContract());
        }
        return new ArrayList<>(hm.values());

    }

//    @GetMapping("/date/{dateFrom}/{dateTo}/{status}/{distributionChanel}/{salesSector}/{salesSegment}/{salesDirector}/{city}/{manager}/{agent}")
//    public Set<RaportDas> getRaportDasSalesFilters(
//            @PathVariable String dateFrom,
//            @PathVariable String dateTo,
//            @PathVariable String status,
//            @PathVariable String distributionChanel,
//            @PathVariable String salesSector,
//            @PathVariable String salesSegment,
//            @PathVariable String salesDirector,
//            @PathVariable String city,
//            @PathVariable String manager,
//            @PathVariable String agent
//    ) {
//
//        java.sql.Date dateFromFormat = null;
//        java.sql.Date dateToFormat = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedFrom = format.parse(dateFrom);
//            Date parsedTo = format.parse(dateTo);
//            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
//            dateToFormat = new java.sql.Date(parsedTo.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<RaportDas> result = raportDasRepository.findByFilterSales3rd(
//                dateFromFormat,
//                dateToFormat,
//                status,
//                distributionChanel,
//                salesSector,
//                salesSegment,
//                salesDirector,
//                city,
//                manager,
//                agent
//         );
//  return collectionIterate(result);
//    }


    public Set<RaportDas> collectionIterate(List<RaportDas> result) {
        Set<RaportDas> targetList = new HashSet<>(result);
        Iterator iter = targetList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        return targetList;
    }


    @GetMapping("/date/{dateFrom}/{dateTo}")
    public List<RaportDas> getRaportDasByDate(@PathVariable String dateFrom, @PathVariable String dateTo) {

        java.sql.Date dateFromFormat = null;
        java.sql.Date dateToFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedFrom = format.parse(dateFrom);
            Date parsedTo = format.parse(dateTo);

            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
            dateToFormat = new java.sql.Date(parsedTo.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<RaportDas> result = raportDasRepository.findByDate(dateFromFormat, dateToFormat);
        return result;
    }


    @GetMapping("/product/{dateFrom}/{dateTo}/{status}")
    public List<RaportDas> getRaportDasByAmountVsProduct(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String status) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//        LocalDate dateFromFormat = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateToFormat = LocalDate.parse(dateTo, formatter);

        java.sql.Date dateFromFormat = null;
        java.sql.Date dateToFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedFrom = format.parse(dateFrom);
            Date parsedTo = format.parse(dateTo);

            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
            dateToFormat = new java.sql.Date(parsedTo.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<RaportDas> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
        Map<String, RaportDas> hm = new LinkedHashMap<>();

        for (RaportDas r : result) {
            String key = r.getNazwaProduktu();
            //   RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(n, r.getSkladka()));
            RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(
                    n,
                    r.getSkladka(),
                    r.getNumberOfContract()
            ));
            raportDas.setSkladka(raportDas.getSkladka().add(r.getSkladka()));
            raportDas.setNumberOfContract(raportDas.getNumberOfContract() + r.getNumberOfContract());
        }
        return new ArrayList<>(hm.values());

    }

    @GetMapping("/payment/{dateFrom}/{dateTo}/{status}")
    public List<RaportDas> getRaportDasByPayment(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String status) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//        LocalDate dateFromFormat = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateToFormat = LocalDate.parse(dateTo, formatter);

        java.sql.Date dateFromFormat = null;
        java.sql.Date dateToFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedFrom = format.parse(dateFrom);
            Date parsedTo = format.parse(dateTo);

            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
            dateToFormat = new java.sql.Date(parsedTo.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<RaportDas> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
        Map<String, RaportDas> hm = new LinkedHashMap<>();

        for (RaportDas r : result) {
            String key = r.getPlatnosc();
            //   RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(n, r.getSkladka()));
            RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(
                    n,
                    r.getSkladka()
                    ));
            raportDas.setSkladka(raportDas.getSkladka().add(r.getSkladka()));
           // raportDas.setNumberOfContract(raportDas.getNumberOfContract() + r.getNumberOfContract());
        }
        return new ArrayList<>(hm.values());

    }


    @GetMapping("/sector/{dateFrom}/{dateTo}/{status}")
    public List<RaportDas> getRaportDasBySector(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String status) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//        LocalDate dateFromFormat = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateToFormat = LocalDate.parse(dateTo, formatter);

        java.sql.Date dateFromFormat = null;
        java.sql.Date dateToFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedFrom = format.parse(dateFrom);
            Date parsedTo = format.parse(dateTo);

            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
            dateToFormat = new java.sql.Date(parsedTo.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<RaportDas> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
        Map<String, RaportDas> hm = new LinkedHashMap<>();

        for (RaportDas r : result) {
            String key = r.getSegmentSprzedazy();
            //   RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(n, r.getSkladka()));
            RaportDas raportDas = hm.computeIfAbsent(key, n -> new RaportDas(
                    r.getSkladka(),
                    n
            ));
            raportDas.setSkladka(raportDas.getSkladka().add(r.getSkladka()));
            // raportDas.setNumberOfContract(raportDas.getNumberOfContract() + r.getNumberOfContract());
        }
        return new ArrayList<>(hm.values());
    }


    @GetMapping("/date/{dateFrom}/{dateTo}/{status}/{filter}")
    public List<RaportDas> getReportByFilter(
            @PathVariable String dateFrom,
            @PathVariable String dateTo,
            @PathVariable String status,
            @PathVariable String filter
            ) {
        List<RaportDas> result = raportDasRepository.findByFilter(
                toSqlDateParser(dateFrom),
                toSqlDateParser(dateTo),
                status );
        Map<String, RaportDas> hm = new LinkedHashMap<>();

        String key;
        for (RaportDas r : result) {

            switch (filter) {
                case "distributionchanel":
                    key = r.getKanalDystrybucji();
                    break;
                case "salessector":
                    key = r.getNazwaSektoraSprzedazy();
                    break;
                case "salessegment":
                    key = r.getSegmentSprzedazy();
                    break;
                case "city":
                    key = r.getMiasto();
                    break;
                case "manager":
                    key = r.getMzaKierownikZespolu();
                    break;
                case "agent":
                    key = r.getNazwaAgenta();
                    break;
                default:
                    key = r.getPlatnosc();
            }
            System.out.println(filter);
            System.out.println(key);
                    RaportDas raportDas = hm.computeIfAbsent(key, premium -> new RaportDas(
                            premium,
                            new BigDecimal(0),
                            r.getNrWewnAgenta(),
                            r.getNrKnfAgenta(),
                            r.getUzytkownik(),
                            r.getNrKnfUzytkownika(),
                            r.getKanalDystrybucji(),
                            r.getPoziom1KNF(),
                            r.getPoziom2(),
                            r.getNazwaSektoraSprzedazy(),
                            r.getPoziom2KNF(),
                            r.getPoziom3(),
                            r.getDyrektorSektora(),
                            r.getPoziom3KNF(),
                            r.getPoziom4(),
                            r.getSegmentSprzedazy(),
                            r.getPoziom4knf(),
                            r.getPoziom5(),
                            r.getDrEkspertSegmentu(),
                            r.getPoziom5knf(),
                            r.getPoziom6(),
                            r.getMiasto(),
                            r.getPoziom6knf(),
                            r.getPoziom7(),
                            r.getMzaKierownikZespolu(),
                            r.getPoziom7knf(),
                            0
                    ));

                    raportDas.setSkladka(raportDas.getSkladka().add(r.getSkladka()));
                    raportDas.setNumberOfContract(raportDas.getNumberOfContract() + r.getNumberOfContract());
            }

        return new ArrayList<>(hm.values());
    }

    // =================================== DATE PARSER ===================================

    public java.sql.Date toSqlDateParser(String date) {
        java.sql.Date result = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = format.parse(date);
            result = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    // ================================== RAPORT HEADERS =================================


    @GetMapping("/filter/sales/{prop}")
    public static List<String> headerFilters(@PathVariable String prop) {
   String input = "src/main/resources/config/filters.properties";

   Map<String, String> result = new HashMap<>();

    try {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String textLine = bufferedReader.readLine();
        do {
            if(textLine != null) {
                String[] parts = textLine.split("=");
                result.put(parts[0], parts[1]);
            }
            textLine = bufferedReader.readLine();
        } while(textLine != null);
        bufferedReader.close();

    } catch (IOException ex) {
        ex.printStackTrace();
    }

    switch (prop) {
        case "key":
            return new ArrayList<>(result.keySet());
        case "value":
            return new ArrayList<>(result.values());
            default:
                return new ArrayList<>(Arrays.asList("Error return statement."));
    }
}


//nie dziala z map po stronie JS
    @GetMapping("/filter/sales/map")
    public static Map<String, String> headerFiltersMap() {
        String input = "src/main/resources/config/filters.properties";

        Map<String, String> result = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String textLine = bufferedReader.readLine();
            do {
                if(textLine != null) {
                    String[] parts = textLine.split("=");
                    result.put(parts[0], parts[1]);
                }
                textLine = bufferedReader.readLine();
            } while(textLine != null);
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}

