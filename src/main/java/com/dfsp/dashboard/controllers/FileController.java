package com.dfsp.dashboard.controllers;

import com.dfsp.dashboard.entities.RaportTotal;
import com.dfsp.dashboard.helpers.ManagerXLS;
import com.dfsp.dashboard.market.MarketService;
import com.dfsp.dashboard.model.MyFile;
import com.dfsp.dashboard.model.RaportAgr;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/files/")
public class FileController {

    private ServletContext servletContext;
    private MarketService marketService;

    //  private String uploads = new File("").getAbsolutePath() + "//uploads//";
    private String uploads;

    @Autowired
    private RaportDasRepository raportDasRepository;

    public FileController(ServletContext servletContext, MarketService marketService) {
        this.servletContext = servletContext;
        this.marketService = marketService;
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

    @PostMapping(value = "save/raport")
    public MyFile saveRaport(@RequestBody List<RaportTotal> raport) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        ManagerXLS<RaportTotal> raportTotalManagerXLS = new ManagerXLS<>(RaportTotal.class);

        File newFile = raportTotalManagerXLS.saveListToXLSFileInToDirectory(raport, uploads, "raport");
        return new MyFile(newFile.getName(), newFile.getAbsolutePath());

    }

    @PostMapping(value = "save/raport/agr")
    public MyFile saveRaportAgr(@RequestBody List<RaportAgr> raport) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        ManagerXLS<RaportAgr> raportTotalManagerXLS = new ManagerXLS<>(RaportAgr.class);

        File newFile = raportTotalManagerXLS.saveListToXLSFileInToDirectory(raport, uploads, "raport_agr");
        return new MyFile(newFile.getName(), newFile.getAbsolutePath());

    }

    @GetMapping(value = "save/total")
    public MyFile saveRaportTotal() throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        List<RaportTotal> raport = raportDasRepository.findAll();

        ManagerXLS<RaportTotal> raportTotalManagerXLS = new ManagerXLS<>(RaportTotal.class);

        File newFile = raportTotalManagerXLS.saveListToXLSFileInToDirectory(raport, uploads, "total_raport");
        return new MyFile(newFile.getName(), newFile.getAbsolutePath());

    }

    @PostMapping("replace")
    public void replaceFromFile(@RequestParam("file") MultipartFile upload) throws IOException, ParseException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ManagerXLS<RaportTotal> manager = new ManagerXLS<>(RaportTotal.class);
        add(manager.saveXLSToList(upload));

    }

    @GetMapping("replace/fromlist")
    public void replaceFileFromList(@RequestParam("filepath") String filePath) throws IOException, ParseException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        MultipartFile multipartFile = new MockMultipartFile("test.xls", new FileInputStream(new File(filePath)));

        ManagerXLS<RaportTotal> manager = new ManagerXLS<>(RaportTotal.class);
        add(manager.saveXLSToList(multipartFile));

    }

    @PostMapping("/xls/save")
    public String saveXlsFile(@RequestParam MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResp = objectMapper.writeValueAsString(marketService.parseXlsFileToMap(file));
        System.out.println(jsonResp);

        //todo - add save file to directory
        return jsonResp;
    }

    //todo add method load files list
    //todo add method send JSON from xls file from directory

    private void add(List<RaportTotal> list) {

        raportDasRepository.deleteAll();
        raportDasRepository.flush();
        raportDasRepository.saveAll(list);
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


}
