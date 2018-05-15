package com.dfsp.dashboard.app;

import java.util.List;

public class CellCollector <E> {
    private List<E> cells;

    public CellCollector(List<E> cells) {
        this.cells = cells;
    }

    public CellCollector() {
    }

    public List<E> getCells() {
        return cells;
    }

    public void setCells(List<E> cells) {
        this.cells = cells;
    }
}
