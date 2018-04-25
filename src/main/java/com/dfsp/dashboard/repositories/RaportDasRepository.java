package com.dfsp.dashboard.repositories;


import com.dfsp.dashboard.entities.RaportDas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Transactional
@Repository
public interface RaportDasRepository extends JpaRepository<RaportDas, Long>, JpaSpecificationExecutor<RaportDas> {

    String REPORT_BY_DATE = "SELECT * FROM raport_das WHERE raport_das.dataZawarcia >= ?1 AND raport_das.dataZawarcia <= ?2";
 //   String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= '?1' and raport_das.dataZawarcia <= '?2' and raport_das.status = '?3'";
  //  String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where cast(raport_das.dataZawarcia as date) >= ?1 and cast(raport_das.dataZawarcia as date) <= ?2 and raport_das.status = ?3";
    String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= ?1 and raport_das.dataZawarcia <= ?2 and raport_das.status = ?3";
  //  String REPORT_BY_DATE_STATUS = "SELECT * FROM raport_das where raport_das.dataZawarcia >= '2017-09-04' and raport_das.dataZawarcia <= '2017-09-04' and raport_das.status = 'umowa'";
String REPORT_BY_FILTERS_SALES = "SELECT * FROM raport_das where  raport_das.skladka  >= 0 and raport_das.skladka <= 1000 and raport_das.miasto  Like '%' and raport_das.status = 'umowa'";
//    @Async
//    @Query(value = REPORT_BY_DATE, nativeQuery = true)
//    List<RaportDas> findByDate(String dateFrom, String dateTo);

    @Async
    @Query(value = REPORT_BY_DATE, nativeQuery = true)
    List<RaportDas> findByDate(Date dateFrom, Date dateTo);

//    @Async
//    @Query(value = REPORT_BY_DATE_STATUS, nativeQuery = true)
//    List<RaportDas> findByDateAndStatus(String dateFrom, String dateTo, String status);

    @Async
    @Query(value = REPORT_BY_DATE_STATUS, nativeQuery = true)
    List<RaportDas> findByDateAndStatus(Date dateFrom, Date dateTo, String status);



    @Async
    @Query(value = REPORT_BY_FILTER, nativeQuery = true)
    List<RaportDas> findByFilter(Date dateFrom, Date dateTo, String status);
    String REPORT_BY_FILTER = "SELECT * FROM raport_das where raport_das.dataZawarcia >= ?1 and raport_das.dataZawarcia <= ?2 and raport_das.status = ?3";









    @Async
    @Query(value = REPORT_BY_FILTERS_SALES, nativeQuery = true)
    List<RaportDas> findByFilterSales2nd(Date dateFrom,
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
    List<RaportDas> findByFilterSales3rd(Date dateFrom,
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




//distributionChanel: new FormControl(),
//        salesSector: new FormControl(),
//        salesSegment: new FormControl(),
//        salesDirector: new FormControl(),
//        city: new FormControl(),
//        manager: new FormControl(),
//        agent: new FormControl(),