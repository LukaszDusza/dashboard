package com.dfsp.dashboard.market;

import com.dfsp.dashboard.config.Constans;
import com.dfsp.dashboard.model.MarketModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.dfsp.dashboard.config.Constans.LOCAL_FILES_PATH_RAPORTS;

@Component
public class FileManager<T> {

    private ServletContext servletContext;

    public FileManager(ServletContext servletContext) {
        this.servletContext = servletContext;
        createLocalDirectory();
        createServletContextDirectory();
    }

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

        /*write file to directory*/
        writeXlsFileToLocalDirectory(workbook);
        return result;
    }

    private void createLocalDirectory() {
        Path path = Paths.get(Constans.LOCAL_FILES_PATH_RAPORTS);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createServletContextDirectory() {
          Constans.APP_CONTEXT_FILES_PATH = servletContext.getRealPath(Constans.FILES_PATH);
        Path path = Paths.get(Constans.APP_CONTEXT_FILES_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeXlsFileToLocalDirectory(HSSFWorkbook workbook) throws IOException {
        workbook.write(new File(Constans.LOCAL_FILES_PATH_RAPORTS
                + Constans.RAPORT_FILES_TITLE + "_" + currentTime() + ".xls"));
        workbook.close();
    }

    private void writeXlsFileToContextDirectory(HSSFWorkbook workbook) throws IOException {
        workbook.write(new File(Constans.APP_CONTEXT_FILES_PATH
                + Constans.RAPORT_FILES_TITLE + "_" + currentTime() + ".xls"));
        workbook.close();
    }

    private String currentTime() {
        DateFormat df = new SimpleDateFormat(Constans.DATE_FORMAT_PATTERN);
        return df.format(new Date(System.currentTimeMillis()));
    }

    public String getNewFileNameJson() {
        return Constans.RAPORT_FILES_TITLE + "_" + currentTime() + ".json";
    }

    public void writeToJsonFile(List<T> obj, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getNewFileNameJson()), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
