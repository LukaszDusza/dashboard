package com.dfsp.dashboard.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String shortName;
    private Long year;
    private BigDecimal financialResult;

    public Market(String companyName, Long year, BigDecimal financialResult, String shortName) {
        this.companyName = companyName;
        this.year = year;
        this.financialResult = financialResult;
        this.shortName = shortName;
    }

    public Market() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public BigDecimal getFinancialResult() {
        return financialResult;
    }

    public void setFinancialResult(BigDecimal financialResult) {
        this.financialResult = financialResult;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
