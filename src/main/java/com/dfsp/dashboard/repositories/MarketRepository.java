package com.dfsp.dashboard.repositories;

import com.dfsp.dashboard.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MarketRepository extends JpaRepository<Market, Long>, JpaSpecificationExecutor<Market> {

    String REPORT_BY_YEAR = "SELECT * FROM market.market WHERE market.market.year = ?1";
    String REPORT_BY_SHORT_NAME = "SELECT * FROM market.market WHERE market.market.shortName = ?1";
    String REPORT_BY_YEAR_AND_SHORT_NAME = "SELECT * FROM market.market WHERE market.market.year = ?1 AND " +
            "market.market.shortName = ?2";

    @Async
    @Query(value = REPORT_BY_YEAR, nativeQuery = true)
    List<Market> findByYear(Long year);

    @Async
    @Query(value = REPORT_BY_SHORT_NAME, nativeQuery = true)
    List<Market>findByCompanyShortName(String companyShortName);

    @Async
    @Query(value = REPORT_BY_YEAR_AND_SHORT_NAME, nativeQuery = true)
    List<Market>findByYearAndCompanyShortName(Long year, String companyShortName);
}
