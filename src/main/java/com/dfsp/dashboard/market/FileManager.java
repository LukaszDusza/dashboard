package com.dfsp.dashboard.market;

import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class FileManager {

    public Map<String, List<String>> xlsToMap(MultipartFile file) throws IOException {

        /*libs*/
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<String> columnsNames = new LinkedList<>();
        Map<String, List<String>> result = new LinkedHashMap<>();
        DataFormatter formatter = new DataFormatter();

        /*add columns titles*/
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            columnsNames.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        /*add content to columns*/
        for (int i = 0; i < columnsNames.size(); i++) {
            List<String> column = new ArrayList<>();
            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                column.add(formatter.formatCellValue(sheet.getRow(j).getCell(i)));
            }
            result.put(columnsNames.get(i), column);
        }
    //    result.forEach((k, v) -> System.out.println(k + ": " + v));
        return result;
    }
}
