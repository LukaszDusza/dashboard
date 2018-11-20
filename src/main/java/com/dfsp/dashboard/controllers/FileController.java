package com.dfsp.dashboard.controllers;

import com.dfsp.dashboard.model.MyFile;
import com.dfsp.dashboard.model.RaportAgr;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/files/")
public class FileController {

    private static String UPLOADED_FOLDER = new File("").getAbsolutePath() + "//uploads//";

    @GetMapping("download")
    public void downloadFile(@RequestParam String filename, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setStatus(HttpServletResponse.SC_OK);

        InputStream is = new FileInputStream(filename);
        FileCopyUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }


    public void createDirectory() {
        Path path = Paths.get(UPLOADED_FOLDER);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }

    @GetMapping("list")
    public List<MyFile> getResources() {

        createDirectory();

        try {
            List<MyFile> files = Files.walk(Paths.get(UPLOADED_FOLDER))
                    .filter(Files::isRegularFile)
                    .map(f -> {
                        try {
                            BasicFileAttributes bs = Files.readAttributes(f.toAbsolutePath(), BasicFileAttributes.class);
                            String time =
                                    bs.creationTime().toString().substring(0,10) + " " +
                                    bs.creationTime().toString().substring(11,19);
                            return new MyFile(f.getFileName().toString(), f.toAbsolutePath().toString(), time);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList());
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @DeleteMapping("delete/{file}")
    public void delete(@PathVariable("file") String fileName) {

        File file = new File(UPLOADED_FOLDER + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @PostMapping(value = "create")
    public MyFile createXLS(@RequestBody List<RaportAgr> raport) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("raport zagregowany");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);


        String[] columns = {
                "Agent",
                "Kanał Dystrybucji",
                "Nazwa Sektora Sprzedaży",
                "Dyrektor Sektora",
                "Segment Sprzedaży",
                "Dyrektor Segmentu",
                "MZA Kierownik Zespołu",
                "Miasto",
                "nr wewnętrzny Agenta",
                "Liczba polis",
                " Suma składek PLN"
        };

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        AtomicInteger counter = new AtomicInteger();

        raport.forEach(b -> {

            counter.getAndIncrement();

            HSSFRow row = sheet.createRow(counter.get());

            HSSFCell cell1 = row.createCell(0);
            HSSFCell cell2 = row.createCell(1);
            HSSFCell cell3 = row.createCell(2);
            HSSFCell cell4 = row.createCell(3);
            HSSFCell cell5 = row.createCell(4);
            HSSFCell cell6 = row.createCell(5);
            HSSFCell cell7 = row.createCell(6);
            HSSFCell cell8 = row.createCell(7);
            HSSFCell cell9 = row.createCell(8);
            HSSFCell cell10 = row.createCell(9);
            HSSFCell cell11 = row.createCell(10);

            cell1.setCellValue(b.getAgent());
            cell2.setCellValue(b.getKanalDystrybucji());
            cell3.setCellValue(b.getNazwaSektoraSprzedazy());
            cell4.setCellValue(b.getDyrektorSektora());
            cell5.setCellValue(b.getSegmentSprzedazy());
            cell6.setCellValue(b.getDyrektorSegmentu());
            cell7.setCellValue(b.getMzaKierownikZespolu());
            cell8.setCellValue(b.getMiasto());
            cell9.setCellValue(b.getNrWewAgenta());
            cell10.setCellValue(b.getQuantity());
            cell11.setCellValue(b.getAmount());
        });


        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }


        long time = System.currentTimeMillis();
        String file = UPLOADED_FOLDER + "raport" + time + ".xls";

        try {
            createDirectory();
            byte[] bytes = workbook.getBytes();
            Path path = Paths.get(file);
            Files.write(path, bytes);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        File newFile = new File(UPLOADED_FOLDER + file);
        return new MyFile(newFile.getName(), file, "");

    }

}
