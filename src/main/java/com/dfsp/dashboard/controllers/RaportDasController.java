package com.dfsp.dashboard.controllers;


import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/raportdas")
public class RaportDasController {

//    @Autowired
//    RaportDasRepository raportDasRepository;
//
//
//    @GetMapping("/all")
//    public List<RaportTotal> getRaportDasAll() {
//        return raportDasRepository.findAll();
//    }
//
//
//    @GetMapping("/usersum")
//    public List<RaportTotal> getReportDasByUser() {
//        List<RaportTotal> list = raportDasRepository.findAll();
//
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        for (RaportTotal r : list) {
//            String key = r.getNazwaAgenta();
//         //   RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(n, r.getSkladka()));
//            RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(
//                    n,
//                  //  r.getSkladka(),
//                    new BigDecimal(0),
//                    r.getNrWewnAgenta(),
//                    r.getNrKnfAgenta(),
//                    r.getUzytkownik(),
//                    r.getNrKnfUzytkownika(),
//                    r.getKanalDystrybucji(),
//                    r.getPoziom1KNF(),
//                    r.getPoziom2(),
//                    r.getNazwaSektoraSprzedazy(),
//                    r.getPoziom2KNF(),
//                    r.getPoziom3(),
//                    r.getDyrektorSektora(),
//                    r.getPoziom3KNF(),
//                    r.getPoziom4(),
//                    r.getSegmentSprzedazy(),
//                    r.getPoziom4knf(),
//                    r.getPoziom5(),
//                    r.getDrEkspertSegmentu(),
//                    r.getPoziom5knf(),
//                    r.getPoziom6(),
//                    r.getMiasto(),
//                    r.getPoziom6knf(),
//                    r.getPoziom7(),
//                    r.getMzaKierownikZespolu(),
//                    r.getPoziom7knf(),
//                    0
//            ));
//
//            reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//            reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//        }
//        return new ArrayList<>(hm.values());
//    }
//
//
//    @GetMapping("/sum/{className}/{methodName}")
//    public List<RaportTotal> getSummary(@PathVariable String className, @PathVariable String methodName) {
//
//        List<RaportTotal> list = raportDasRepository.findAll();
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        //przyjmuje nazwe metody i ja zapisuje do obiektu m.
//       Method m = MyInvoker.getMethodFromClass(className, methodName);
//
////       Method[] methods = MyInvoker.getMethodsTableFromClass(className);
////        for (Method method : methods) {
////            if (method.toString().contains("com.dfsp.dashboard.entities.RaportTotal.get")) {
////                System.out.println(method);
////            }
////        }
//
//        for (RaportTotal r : list) {
//            String key = null;
//            try {
//                key = (String) m.invoke(r);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//            RaportTotal reportDas = hm.computeIfAbsent(key, n -> MyInvoker.newObject(r,n));
//            reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//            reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//        }
//        return new ArrayList<>(hm.values());
//    }
//
//    @GetMapping("/date/{dateFrom}/{dateTo}/{status}")
//    public List<RaportTotal> getRaportDasByDateSummary(
//            @PathVariable String dateFrom,
//            @PathVariable String dateTo,
//            @PathVariable String status) {
//
//        java.sql.Date dateFromFormat = null;
//        java.sql.Date dateToFormat = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedFrom = format.parse(dateFrom);
//            Date parsedTo = format.parse(dateTo);
//
//            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
//            dateToFormat = new java.sql.Date(parsedTo.getTime());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<RaportTotal> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        for (RaportTotal r : result) {
//            String key = r.getNazwaAgenta();
//            //   RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(n, r.getSkladka()));
//            RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(
//                    n,
//                    //  r.getSkladka(),
//                    new BigDecimal(0),
//                    r.getNrWewnAgenta(),
//                    r.getNrKnfAgenta(),
//                    r.getUzytkownik(),
//                    r.getNrKnfUzytkownika(),
//                    r.getKanalDystrybucji(),
//                    r.getPoziom1KNF(),
//                    r.getPoziom2(),
//                    r.getNazwaSektoraSprzedazy(),
//                    r.getPoziom2KNF(),
//                    r.getPoziom3(),
//                    r.getDyrektorSektora(),
//                    r.getPoziom3KNF(),
//                    r.getPoziom4(),
//                    r.getSegmentSprzedazy(),
//                    r.getPoziom4knf(),
//                    r.getPoziom5(),
//                    r.getDrEkspertSegmentu(),
//                    r.getPoziom5knf(),
//                    r.getPoziom6(),
//                    r.getMiasto(),
//                    r.getPoziom6knf(),
//                    r.getPoziom7(),
//                    r.getMzaKierownikZespolu(),
//                    r.getPoziom7knf(),
//                    0
//            ));
//            reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//            reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//        }
//        return new ArrayList<>(hm.values());
//
//    }
//
////    @GetMapping("/date/{dateFrom}/{dateTo}/{status}/{distributionChanel}/{salesSector}/{salesSegment}/{salesDirector}/{city}/{manager}/{agent}")
////    public Set<RaportTotal> getRaportDasSalesFilters(
////            @PathVariable String dateFrom,
////            @PathVariable String dateTo,
////            @PathVariable String status,
////            @PathVariable String distributionChanel,
////            @PathVariable String salesSector,
////            @PathVariable String salesSegment,
////            @PathVariable String salesDirector,
////            @PathVariable String city,
////            @PathVariable String manager,
////            @PathVariable String agent
////    ) {
////
////        java.sql.Date dateFromFormat = null;
////        java.sql.Date dateToFormat = null;
////        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
////        try {
////            Date parsedFrom = format.parse(dateFrom);
////            Date parsedTo = format.parse(dateTo);
////            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
////            dateToFormat = new java.sql.Date(parsedTo.getTime());
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////
////        List<RaportTotal> result = raportDasRepository.findByFilterSales3rd(
////                dateFromFormat,
////                dateToFormat,
////                status,
////                distributionChanel,
////                salesSector,
////                salesSegment,
////                salesDirector,
////                city,
////                manager,
////                agent
////         );
////  return collectionIterate(result);
////    }
//
//
//    public Set<RaportTotal> collectionIterate(List<RaportTotal> result) {
//        Set<RaportTotal> targetList = new HashSet<>(result);
//        Iterator iter = targetList.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
//
//        return targetList;
//    }
//
//
//    @GetMapping("/date/{dateFrom}/{dateTo}")
//    public List<RaportTotal> getRaportDasByDate(@PathVariable String dateFrom, @PathVariable String dateTo) {
//
//        java.sql.Date dateFromFormat = null;
//        java.sql.Date dateToFormat = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedFrom = format.parse(dateFrom);
//            Date parsedTo = format.parse(dateTo);
//
//            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
//            dateToFormat = new java.sql.Date(parsedTo.getTime());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        List<RaportTotal> result = raportDasRepository.findByDate(dateFromFormat, dateToFormat);
//        return result;
//    }
//
//
//    @GetMapping("/product/{dateFrom}/{dateTo}/{status}")
//    public List<RaportTotal> getRaportDasByAmountVsProduct(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String status) {
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
////        LocalDate dateFromFormat = LocalDate.parse(dateFrom, formatter);
////        LocalDate dateToFormat = LocalDate.parse(dateTo, formatter);
//
//        java.sql.Date dateFromFormat = null;
//        java.sql.Date dateToFormat = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedFrom = format.parse(dateFrom);
//            Date parsedTo = format.parse(dateTo);
//
//            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
//            dateToFormat = new java.sql.Date(parsedTo.getTime());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<RaportTotal> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        for (RaportTotal r : result) {
//            String key = r.getNazwaProduktu();
//            //   RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(n, r.getSkladka()));
//            RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(
//                    n,
//                    r.getSkladka(),
//                    r.getNumberOfContract()
//            ));
//            reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//            reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//        }
//        return new ArrayList<>(hm.values());
//
//    }
//
//    @GetMapping("/payment/{dateFrom}/{dateTo}/{status}")
//    public List<RaportTotal> getRaportDasByPayment(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String status) {
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
////        LocalDate dateFromFormat = LocalDate.parse(dateFrom, formatter);
////        LocalDate dateToFormat = LocalDate.parse(dateTo, formatter);
//
//        java.sql.Date dateFromFormat = null;
//        java.sql.Date dateToFormat = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedFrom = format.parse(dateFrom);
//            Date parsedTo = format.parse(dateTo);
//
//            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
//            dateToFormat = new java.sql.Date(parsedTo.getTime());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<RaportTotal> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        for (RaportTotal r : result) {
//            String key = r.getPlatnosc();
//            //   RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(n, r.getSkladka()));
//            RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(
//                    n,
//                    r.getSkladka()
//                    ));
//            reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//           // reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//        }
//        return new ArrayList<>(hm.values());
//
//    }
//
//
//    @GetMapping("/sector/{dateFrom}/{dateTo}/{status}")
//    public List<RaportTotal> getRaportDasBySector(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String status) {
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
////        LocalDate dateFromFormat = LocalDate.parse(dateFrom, formatter);
////        LocalDate dateToFormat = LocalDate.parse(dateTo, formatter);
//
//        java.sql.Date dateFromFormat = null;
//        java.sql.Date dateToFormat = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedFrom = format.parse(dateFrom);
//            Date parsedTo = format.parse(dateTo);
//
//            dateFromFormat = new java.sql.Date(parsedFrom.getTime());
//            dateToFormat = new java.sql.Date(parsedTo.getTime());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<RaportTotal> result = raportDasRepository.findByDateAndStatus(dateFromFormat, dateToFormat, status);
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        for (RaportTotal r : result) {
//            String key = r.getSegmentSprzedazy();
//            //   RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(n, r.getSkladka()));
//            RaportTotal reportDas = hm.computeIfAbsent(key, n -> new RaportTotal(
//                    r.getSkladka(),
//                    n
//            ));
//            reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//            // reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//        }
//        return new ArrayList<>(hm.values());
//    }
//
//
//    @GetMapping("/date/{dateFrom}/{dateTo}/{status}/{filter}")
//    public List<RaportTotal> getReportByFilter(
//            @PathVariable String dateFrom,
//            @PathVariable String dateTo,
//            @PathVariable String status,
//            @PathVariable String filter
//            ) {
//        List<RaportTotal> result = raportDasRepository.findByFilter(
//                DateParser.toSqlDate(dateFrom),
//                DateParser.toSqlDate(dateTo),
//                status );
//        Map<String, RaportTotal> hm = new LinkedHashMap<>();
//
//        String key;
//        for (RaportTotal r : result) {
//
//            switch (filter) {
//                case "distributionchanel":
//                    key = r.getKanalDystrybucji();
//                    break;
//                case "salessector":
//                    key = r.getNazwaSektoraSprzedazy();
//                    break;
//                case "salessegment":
//                    key = r.getSegmentSprzedazy();
//                    break;
//                case "city":
//                    key = r.getMiasto();
//                    break;
//                case "manager":
//                    key = r.getMzaKierownikZespolu();
//                    break;
//                case "agent":
//                    key = r.getNazwaAgenta();
//                    break;
//                default:
//                    key = r.getPlatnosc();
//            }
//                    RaportTotal reportDas = hm.computeIfAbsent(key, premium -> new RaportTotal(
//                            premium,
//                            new BigDecimal(0),
//                            r.getNrWewnAgenta(),
//                            r.getNrKnfAgenta(),
//                            r.getUzytkownik(),
//                            r.getNrKnfUzytkownika(),
//                            r.getKanalDystrybucji(),
//                            r.getPoziom1KNF(),
//                            r.getPoziom2(),
//                            r.getNazwaSektoraSprzedazy(),
//                            r.getPoziom2KNF(),
//                            r.getPoziom3(),
//                            r.getDyrektorSektora(),
//                            r.getPoziom3KNF(),
//                            r.getPoziom4(),
//                            r.getSegmentSprzedazy(),
//                            r.getPoziom4knf(),
//                            r.getPoziom5(),
//                            r.getDrEkspertSegmentu(),
//                            r.getPoziom5knf(),
//                            r.getPoziom6(),
//                            r.getMiasto(),
//                            r.getPoziom6knf(),
//                            r.getPoziom7(),
//                            r.getMzaKierownikZespolu(),
//                            r.getPoziom7knf(),
//                            0
//                    ));
//
//                    reportDas.setSkladka(reportDas.getSkladka().add(r.getSkladka()));
//                    reportDas.setNumberOfContract(reportDas.getNumberOfContract() + r.getNumberOfContract());
//            }
//
//        return new ArrayList<>(hm.values());
//    }
//
//
//
//    @GetMapping("/filter/sales")
//    public List<RaportTotal> headerFilters() {
//     return null;
//    }
//
//    @GetMapping("/lang/{lang}")
//    public Map<String, String> lang(@PathVariable String lang) {
//       return new FilesReader().readLangfile("dictionary",lang);
//    }
//
//    @GetMapping("/file/{name}")
//    public List <Map<String,String>> getFile(@PathVariable String name) {
//        return new FilesReader().csvParser(name);
//    }
//

}

