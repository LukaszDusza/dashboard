package com.dfsp.dashboard.repositories;


import com.dfsp.dashboard.entities.RaportTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Transactional
@Repository
public interface RaportDasRepository extends JpaRepository<RaportTotal, Long>, JpaSpecificationExecutor<RaportTotal> {

    String REPORT_BY_DATE = "SELECT * FROM raport_das WHERE raport_das.datazawarcia BETWEEN ?1 AND ?2";
 //   String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= '?1' and raport_das.dataZawarcia <= '?2' and raport_das.status = '?3'";
  //  String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where cast(raport_das.dataZawarcia as date) >= ?1 and cast(raport_das.dataZawarcia as date) <= ?2 and raport_das.status = ?3";
    String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= ?1 and raport_das.dataZawarcia <= ?2 and raport_das.status = ?3";
  //  String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= '2017-09-04' and raport_das.dataZawarcia <= '2017-09-04' and raport_das.status = 'umowa'";
String REPORT_BY_FILTERS_SALES = "SELECT * FROM raport_das where  raport_das.skladka  >= 0 and raport_das.skladka <= 1000 and raport_das.miasto  Like '%' and raport_das.status = 'umowa'";
//    @Async
//    @Query(value = REPORT_BY_DATE, nativeQuery = true)
//    List<RaportTotal> findByDate(String dateFrom, String dateTo);

    @Async
    @Query(value = REPORT_BY_DATE, nativeQuery = true)
    List<RaportTotal> findByDate(Date dateFrom, Date dateTo);


    String RAPORT_BY_PROPERTIES = "SELECT * FROM raport_test WHERE" +
            " raport_test.data_zawarcia BETWEEN ?1 AND ?2" +
            " AND raport_test.nazwa_agenta like (?3%)" +
            " AND raport_test.dyrektor_ekspert_segmentu like ?4%" +
            " AND raport_test.dyrektor_sektora like ?5%" +
            " AND raport_test.kanal_dystrybucji like ?6%" +
            " AND raport_test.mza_kierownik_zespolu like ?7%" +
            " AND raport_test.nr_zespolu_sprzedazy_miasto like ?8%" +
            " AND raport_test.nazwa_produktu like ?9%" +
            " AND raport_test.nazwa_sektora_sprzedazy like ?10%" +
            " AND raport_test.platnosc like ?11%" +
            " AND raport_test.nazwa_segmentu_sprzedazy like ?12%" +
            " AND raport_test.skladka >= ?13" +
            " AND raport_test.skladka <= ?14" +
            " AND raport_test.status like ?15%" +
            " AND raport_test.uzytkownik like ?16%";

    @Async
    @Query(value = RAPORT_BY_PROPERTIES, nativeQuery = true)
    List<RaportTotal> raportByDateAndProperties(
            Date dateFrom,
            Date dateTo,
            String agent,
            String dyrektorSegmentu,
            String dyrektorSektora,
            String kanalDystrybucji,
            String kierownikZespolu,
            String miasto,
            String nazwaProduktu,
            String nazwaSektoraSprzedazy,
            String platnosc,
            String segmentSprzedazy,
            BigDecimal fromAmount,
            BigDecimal toAmount,
            String statusUmowy,
            String uzytkownik
    );


//    @Async
//    @Query(value = REPORT_BY_DATE_STATUS, nativeQuery = true)
//    List<RaportTotal> findByDateAndStatus(String dateFrom, String dateTo, String status);

    @Async
    @Query(value = REPORT_BY_DATE_STATUS, nativeQuery = true)
    List<RaportTotal> findByDateAndStatus(Date dateFrom, Date dateTo, String status);



    @Async
    @Query(value = REPORT_BY_FILTER, nativeQuery = true)
    List<RaportTotal> findByFilter(Date dateFrom, Date dateTo, String status);
    String REPORT_BY_FILTER = "SELECT * FROM raport_das where raport_das.dataZawarcia >= ?1 and raport_das.dataZawarcia <= ?2 and raport_das.status = ?3";


    @Async
    @Query(value = REPORT_BY_FILTERS_SALES, nativeQuery = true)
    List<RaportTotal> findByFilterSales2nd(Date dateFrom,
                                           Date dateTo,
                                           String status,
                                           String businessLine,
                                           String productLine,
                                           String product,
                                           String personType,
                                           String  paymentMode,
                                           String  paymentMethod

            );


    String REPORT_BY_FILTERS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= (?1) and raport_das.dataZawarcia" +
            "<= (?2) and raport_das.status = (?3)" +
            "and raport_das.kanaldystrybucji Like (?4%)" +
            "and raport_das.nazwasektorasprzedazy Like (?5%)" +
            "and raport_das.segmentsprzedazy Like (?6%)" +
            "and raport_das.dyrektorsektora Like (?7%)" +
            "and raport_das.miasto Like (?8%)" +
            "and raport_das.mzakierownikzespolu Like (?9%)" +
            "and raport_das.nazwaagenta Like (?10%)";

    String REPORT_BY_FILTERS1 = "SELECT * FROM raport_das where raport_das.dataZawarcia >= (?1) and raport_das.dataZawarcia <= (?2) and raport_das.status = (?3) and raport_das.kanaldystrybucji Like (?4) and raport_das.nazwasektorasprzedazy Like (?5) and raport_das.segmentsprzedazy Like (?6) and raport_das.dyrektorsektora Like (?7) and raport_das.miasto Like (?8) and raport_das.mzakierownikzespolu Like (?9) and raport_das.nazwaagenta Like (?10)";

    @Async
    @Query(value = REPORT_BY_FILTERS,nativeQuery = true)
    List<RaportTotal> findByFilterSales3rd(Date dateFrom,
                                           Date dateTo,
                                           String status,
                                           String distributionChanel,
                                           String  salesSector,
                                           String  salesSegment,
                                           String  salesDirector,
                                           String  city,
                                           String  manager,
                                           String  agent
    );


}


