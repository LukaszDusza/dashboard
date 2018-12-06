package com.dfsp.dashboard.helpers;

import com.dfsp.dashboard.app.DateParser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ManagerXLS<T> {

    Class<T> clazz;

    public File saveListToXLSFileInToDirectory(List<T> series, String path, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, InstantiationException {

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

        columnsTitles.forEach(
                t -> {
                    String name = "get" + t.substring(0, 1).toUpperCase() + t.substring(1);
                    System.out.println(name);
                }
        );

        // -------------------- end -------------------------------

        // ----- get all GET... methods from obj --------

//        List<String> methods = new ArrayList<>();
//        Method[] methodArray = clazz.getDeclaredMethods();
//
//        for (Method m : methodArray) {
//
//            if(m.getName().startsWith("get") && !m.getName().equals("getId")) {
//                methods.add(m.getName());
//            }
//        }
//           methods.forEach(System.out::println);

        // -------------- end ------------------

        for (int i = 1; i < series.size(); i++) {

            HSSFRow row = sheet.createRow(i);

            for (int j = 0; j < columnsTitles.size(); j++) {

                HSSFCell cell = row.createCell(j);
                Method method = series.get(i).getClass().getMethod("get" + columnsTitles.get(j).substring(0, 1).toUpperCase() + columnsTitles.get(j).substring(1));

                System.out.println(method.invoke(series.get(i)));
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

    public List<T> saveXLSToList(MultipartFile file) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<T> raport = new ArrayList<>();

        Constructor<T> reflConstruct = clazz.getConstructor();

//        List<String> columnsTitles = new ArrayList<>();
//
//        for (Field f : clazz.getDeclaredFields()) {
//            columnsTitles.add(f.getName());
//            //   System.out.println(f.getName());
//        }

//        Object obj = reflConstruct.newInstance();
//        List<String> methods = new ArrayList<>();
//        Method[] methodArray = obj.getClass().getDeclaredMethods();
//
//        for (Method m : methodArray) {
//
//            if (m.getName().startsWith("set") && !m.getName().equals("setId")) {
//                methods.add(m.getName());
//            }
//        }
//          methods.forEach(System.out::println);

     //   int rowNum = 1;
        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {

           //

            List<String> props = new ArrayList<>();

            for (int collIndex = 0; collIndex < sheet.getRow(0).getPhysicalNumberOfCells(); collIndex++) {

            //    System.out.println(rowNum);

                Cell cell = sheet.getRow(rowIndex).getCell(collIndex);
                props.add(cell.toString());

            }

            try {

                Object ob = reflConstruct.newInstance();

                int count = 0;
                for (int i = 1; i < clazz.getDeclaredFields().length; i++) {
                    Field field = clazz.getDeclaredFields()[i];
                    field.setAccessible(true);

                    if (field.getType().getCanonicalName().equals("java.lang.String")) {
                        field.set(ob, props.get(count));
                    }

                    if (field.getType().getCanonicalName().equals("java.sql.Date")) {
                        Date date = DateParser.toSqlDate(props.get(count));
                        //   System.out.println(props.get(count));
                        field.set(ob, date);
                    }

                    if (field.getType().getCanonicalName().equals("java.math.BigDecimal")) {
                        field.set(ob, new BigDecimal(props.get(count)));
                    }

                    count++;

                    //  System.out.println(field.getType().getCanonicalName());
                }

                //   System.out.println(ob);
                raport.add((T) ob);

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

          //  break;
        }

        workbook.close();
        inputStream.close();

     //   raport.forEach(System.out::println);

        return raport;

    }
}
