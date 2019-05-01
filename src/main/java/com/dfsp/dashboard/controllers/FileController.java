package com.dfsp.dashboard.controllers;

import com.dfsp.dashboard.config.Constans;
import com.dfsp.dashboard.entities.RaportTotal;
import com.dfsp.dashboard.helpers.ManagerXLS;
import com.dfsp.dashboard.market.MarketService;
import com.dfsp.dashboard.model.LocalFile;
import com.dfsp.dashboard.model.MessageModel;
import com.dfsp.dashboard.model.MyFile;
import com.dfsp.dashboard.model.RaportAgr;
import com.dfsp.dashboard.repositories.RaportDasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.activation.MimetypesFileTypeMap;
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
@RequestMapping(Constans.FILES)
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

    @PostMapping("/xls/save-to-json")
    public void saveXlsFileToJson(@RequestParam MultipartFile file) {
        marketService.marketModelXlsToJsonFile(file);
    }

    //todo add method send JSON from xls file from directory

    private void add(List<RaportTotal> list) {

        raportDasRepository.deleteAll();
        raportDasRepository.flush();
        raportDasRepository.saveAll(list);
    }

    @GetMapping(Constans.RAPORTS_FILES_LIST)
    public List<LocalFile> getRaportsList() throws IOException {

        return Files.walk(Paths.get(Constans.LOCAL_FILES_PATH_RAPORTS))
                .filter(Files::isRegularFile)
                .map(f -> {

                    try {
                        BasicFileAttributes bs = Files.readAttributes(f.toAbsolutePath(), BasicFileAttributes.class);

                        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(Constans.FILES + Constans.DOWNLOAD_FILES_URI)
                                .path(f.getFileName().toString())
                                .toUriString();

                        String fileDeleteUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(Constans.FILES + Constans.DELETE_FILES_URI)
                                .path(f.getFileName().toString())
                                .toUriString();

                        return new LocalFile(
                                f.getFileName().toString(),
                                bs.creationTime().toString(),
                                bs.lastModifiedTime().toString(),
                                bs.size(),
                                fileDownloadUri,
                                fileDeleteUri,
                                Files.probeContentType(f.toAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    @GetMapping(Constans.DOWNLOAD_FILES_URI + "{filename}")
    public ResponseEntity<?> downloadRaportFile(@PathVariable String filename) throws IOException {

        Path path = Paths.get(Constans.LOCAL_FILES_PATH_RAPORTS + filename);
        Resource resource = new UrlResource(path.toUri());

        File targetFile = new File(Constans.LOCAL_FILES_PATH_RAPORTS + filename);
        //  MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
        //  String contentType = Files.probeContentType(path);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + targetFile.getName() + "\"")
                .contentLength(targetFile.length())
                .body(resource);
    }

    @DeleteMapping(Constans.DELETE_FILES_URI + "{filename}")
    public ResponseEntity<MessageModel> deleteRaportFile(@PathVariable("filename") String fileName) {
        if (new File(Constans.LOCAL_FILES_PATH_RAPORTS + fileName).delete()) {
            return new ResponseEntity<>(MessageModel.FILE_SUCCES_DELETED, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageModel.FILE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
