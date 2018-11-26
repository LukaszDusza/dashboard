package com.dfsp.dashboard.helpers;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CreatorXLS {

    private Class<?> clazz;

    public File saveListToXLSFileInToDirectory(List<?> series, String path, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // ---- create column titles and first row in document ----
        List<String> columnsTitles = new ArrayList<>();

        for (Field f : clazz.getDeclaredFields()) {
            columnsTitles.add(f.getName());
        }
        columnsTitles.remove(0); //without id

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnsTitles.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnsTitles.get(i));
            cell.setCellStyle(headerCellStyle);
        }

//        columnsTitles.forEach(
//                t -> {
//                   String name = "get" + t.substring(0,1).toUpperCase() + t.substring(1);
//                    System.out.println(name);
//                }
//               );

        // -------------------- end -------------------------------

        // ----- get all GET... methods from obj --------

//        List<String> methods = new ArrayList<>();
//        Method[] methodArray = RaportTotal.class.getDeclaredMethods();
//
//        for (Method m : methodArray) {
//
//            if(m.getName().startsWith("get") && !m.getName().equals("getId")) {
//                methods.add(m.getName());
//            }
//        }
        //   methods.forEach(System.out::println);

        // -------------- end ------------------

        for (int i = 1; i < series.size(); i++) {

            HSSFRow row = sheet.createRow(i);

            for (int j = 0; j < columnsTitles.size(); j++) {

                HSSFCell cell = row.createCell(j);
                Method method = series.get(i).getClass().getMethod("get" + columnsTitles.get(j).substring(0, 1).toUpperCase() + columnsTitles.get(j).substring(1));
                //   System.out.println(method.invoke(series.get(i)));
                Object result = method.invoke(series.get(i));
                cell.setCellValue(String.valueOf(result));

            }
        }

        for (int i = 0; i < columnsTitles.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        long time = System.currentTimeMillis();
        String file = path + fileName + "_" + time + ".xls";

        //  byte[] bytes = workbook.getBytes();
        //  Path path = Paths.get(file);
        //  Files.write(path, bytes);

        workbook.write(new File(file));
        workbook.close();

        return new File(file);
    }
}
