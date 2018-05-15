package com.dfsp.dashboard.entities;


import java.sql.Date;


public class Cell {

    private String stringCell;
    private Long longCell;
    private Date dateCell;

    public Cell() {
    }

    public Cell(String stringCell) {
        this.stringCell = stringCell;
    }

    public Cell(Long longCell) {
        this.longCell = longCell;
    }

    public Cell(Date dateCell) {
        this.dateCell = dateCell;
    }

    public String getStringCell() {
        return stringCell;
    }

    public void setStringCell(String stringCell) {
        this.stringCell = stringCell;
    }

    public Long getLongCell() {
        return longCell;
    }

    public void setLongCell(Long longCell) {
        this.longCell = longCell;
    }

    public Date getDateCell() {
        return dateCell;
    }

    public void setDateCell(Date dateCell) {
        this.dateCell = dateCell;
    }


}
