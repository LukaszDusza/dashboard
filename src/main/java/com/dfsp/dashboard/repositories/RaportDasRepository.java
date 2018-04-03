package com.dfsp.dashboard.repositories;



import com.dfsp.dashboard.entities.RaportDas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RaportDasRepository extends JpaRepository<RaportDas, Long>, JpaSpecificationExecutor<RaportDas> {

    String REPORT_BY_DATE = "SELECT * FROM market.raport_das where market.raport_das.dataZawarcia >= ?1 and market.raport_das.dataZawarcia <= ?2";
    String REPORT_BY_DATE_STATUS = "SELECT * FROM market.raport_das where market.raport_das.dataZawarcia >= ?1 and market.raport_das.dataZawarcia <= ?2 and market.raport_das.status = ?3";

    @Async
    @Query(value = REPORT_BY_DATE, nativeQuery = true)
    List<RaportDas> findByDate(String dateFrom, String dateTo);

    @Async
    @Query(value = REPORT_BY_DATE_STATUS, nativeQuery = true)
    List<RaportDas> findByDateAndStatus(String dateFrom, String dateTo, String status);

}
