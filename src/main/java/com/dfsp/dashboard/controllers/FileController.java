package com.dfsp.dashboard.controllers;

import com.dfsp.dashboard.app.DateParser;
import com.dfsp.dashboard.entities.RaportTotal;
import com.dfsp.dashboard.helpers.CreatorXLS;
import com.dfsp.dashboard.model.MyFile;
import com.dfsp.dashboard.model.RaportAgr;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/files/")
public class FileController {

    private ServletContext servletContext;

    //  private String uploads = new File("").getAbsolutePath() + "//uploads//";
    private String uploads;

    @Autowired
    private RaportDasRepository raportDasRepository;

    public FileController(ServletContext servletContext) {
        this.servletContext = servletContext;
        createDirectory();
    }

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
        uploads = servletContext.getRealPath("/uploads/");
        System.out.println(uploads);
        Path path = Paths.get(uploads);
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

        try {
            List<MyFile> files = Files.walk(Paths.get(uploads))
                    .filter(Files::isRegularFile)
                    .map(f -> {
                        try {
                            BasicFileAttributes bs = Files.readAttributes(f.toAbsolutePath(), BasicFileAttributes.class);
                            String time =
                                    bs.creationTime().toString().substring(0, 10) + " " +
                                            bs.creationTime().toString().substring(11, 19);
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

        File file = new File(uploads + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

//    @PostMapping(value = "create")
//    public MyFile createXLS(@RequestBody List<RaportAgr> raport) throws IOException {
//
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("raport zagregowany");
//
//        Font headerFont = workbook.createFont();
//        headerFont.setBold(true);
//        headerFont.setFontHeightInPoints((short) 10);
//        headerFont.setColor(IndexedColors.BLACK.getIndex());
//
//        CellStyle headerCellStyle = workbook.createCellStyle();
//        headerCellStyle.setFont(headerFont);
//
//
//        String[] columns = {
//                "Agent",
//                "Kanał Dystrybucji",
//                "Nazwa Sektora Sprzedaży",
//                "Dyrektor Sektora",
//                "Segment Sprzedaży",
//                "Dyrektor Segmentu",
//                "MZA Kierownik Zespołu",
//                "Miasto",
//                "nr wewnętrzny Agenta",
//                "Liczba polis",
//                " Suma składek PLN"
//        };
//
//        Row headerRow = sheet.createRow(0);
//
//        for (int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//            cell.setCellStyle(headerCellStyle);
//        }
//
//        AtomicInteger counter = new AtomicInteger();
//
//        raport.forEach(b -> {
//
//            counter.getAndIncrement();
//
//            HSSFRow row = sheet.createRow(counter.get());
//
//            HSSFCell cell1 = row.createCell(0);
//            HSSFCell cell2 = row.createCell(1);
//            HSSFCell cell3 = row.createCell(2);
//            HSSFCell cell4 = row.createCell(3);
//            HSSFCell cell5 = row.createCell(4);
//            HSSFCell cell6 = row.createCell(5);
//            HSSFCell cell7 = row.createCell(6);
//            HSSFCell cell8 = row.createCell(7);
//            HSSFCell cell9 = row.createCell(8);
//            HSSFCell cell10 = row.createCell(9);
//            HSSFCell cell11 = row.createCell(10);
//
//            cell1.setCellValue(b.getAgent());
//            cell2.setCellValue(b.getKanalDystrybucji());
//            cell3.setCellValue(b.getNazwaSektoraSprzedazy());
//            cell4.setCellValue(b.getDyrektorSektora());
//            cell5.setCellValue(b.getSegmentSprzedazy());
//            cell6.setCellValue(b.getDyrektorSegmentu());
//            cell7.setCellValue(b.getMzaKierownikZespolu());
//            cell8.setCellValue(b.getMiasto());
//            cell9.setCellValue(b.getNrWewAgenta());
//            cell10.setCellValue(b.getQuantity());
//            cell11.setCellValue(b.getAmount());
//        });
//
//
//        for (int i = 0; i < columns.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//
//        long time = System.currentTimeMillis();
//        String file = uploads + "raport" + time + ".xls";
//
//        try {
//            createDirectory();
//            byte[] bytes = workbook.getBytes();
//            Path path = Paths.get(file);
//            //  Files.write(path, bytes);
//
//
//            workbook.write(new File(file));
//
//            workbook.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File newFile = new File(uploads + file);
//        return new MyFile(newFile.getName(), file);
//
//    }


    @PostMapping(value = "save/raport")
    public MyFile saveRaport(@RequestBody List<RaportTotal> raport) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        CreatorXLS raportTotalCreatorXLS = new CreatorXLS(RaportTotal.class);

        File newFile = raportTotalCreatorXLS.saveListToXLSFileInToDirectory(raport, uploads, "raport");
        return new MyFile(newFile.getName(), newFile.getAbsolutePath());

    }

    @PostMapping(value = "save/raport/agr")
    public MyFile saveRaportAgr(@RequestBody List<RaportAgr> raport) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        CreatorXLS raportTotalCreatorXLS = new CreatorXLS(RaportAgr.class);

        File newFile = raportTotalCreatorXLS.saveListToXLSFileInToDirectory(raport, uploads, "raport_agr");
        return new MyFile(newFile.getName(), newFile.getAbsolutePath());

    }

    @GetMapping(value = "save/total")
    public MyFile saveRaportTotal() throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        List<RaportTotal> raport = raportDasRepository.findAll();

        CreatorXLS raportTotalCreatorXLS = new CreatorXLS(RaportTotal.class);

        File newFile = raportTotalCreatorXLS.saveListToXLSFileInToDirectory(raport, uploads, "total_raport");
        return new MyFile(newFile.getName(), newFile.getAbsolutePath());

    }

    @PostMapping("replace")
    public List<RaportTotal> replaceFromFile(@RequestParam("file") MultipartFile upload) throws IOException, ParseException {

        InputStream inputStream = new BufferedInputStream(upload.getInputStream());
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<RaportTotal> raportTotals = new ArrayList<>();
        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {

            List<String> props = new ArrayList<>();

            for (int collIndex = 0; collIndex < 4; collIndex++) {

                Cell cell = sheet.getRow(rowIndex).getCell(collIndex);
                props.add(cell.toString());

            }
            RaportTotal raportTotal = new RaportTotal(

                    props.get(0),
                    DateParser.toSqlDate(props.get(1)),
                    props.get(2),
                    DateParser.toSqlDate(props.get(3)),
                    DateParser.toSqlDate(props.get(4)),
                    DateParser.toSqlDate(props.get(5)),
                    props.get(6),
                    props.get(7),
                    new BigDecimal(props.get(8)),
                    props.get(9),
                    props.get(10),
                    props.get(11),
                    props.get(12),
                    props.get(13),
                    props.get(14),
                    props.get(15),
                    props.get(16),
                    props.get(17),
                    props.get(18),
                    props.get(19),
                    props.get(20),
                    props.get(21)
            );
            raportTotals.add(raportTotal);
        }
        workbook.close();
        inputStream.close();

           raportTotals.forEach(System.out::println);

      //  add(raportTotals);

        return raportTotals;
    }


    private void add(List<RaportTotal> list) {

           raportDasRepository.deleteAll();
           raportDasRepository.flush();

        raportDasRepository.saveAll(list);

        //    List<RaportTotal> totals = new ArrayList<>();

//        list.forEach(o ->
//
//                totals.add(
//                    new Book(
//                            o.getTitle(),
//                            o.getIsbn(),
//                            o.getAuthor(),
//                            category
//                    )
//            )
//        );

    }

}
