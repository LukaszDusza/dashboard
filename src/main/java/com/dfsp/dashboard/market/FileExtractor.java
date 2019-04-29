package com.dfsp.dashboard.market;

import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class FileExtractor<T> {

    Class<T> clazz;

    public Map<String, List<String>> extractXLSFile(MultipartFile file) throws IOException {
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, List<String>> content = new LinkedHashMap<>();

        /*add columns names*/
        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            Cell cell = sheet.getRow(0).getCell(i);
            content.put(cell.getStringCellValue(), new ArrayList<>());
        }

        /*add vales per column*/
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                content.put()

            }
       }

        content.keySet().forEach(System.out::println);

        return null;
    }
}
