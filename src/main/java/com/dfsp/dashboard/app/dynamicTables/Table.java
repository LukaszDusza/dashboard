package com.dfsp.dashboard.app.dynamicTables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    private List<String> head;
    private Map<String, List<Object>> col;
}
