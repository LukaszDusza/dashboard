package com.dfsp.dashboard.storage.controllers;


import com.dfsp.dashboard.storage.model.DbFile;
import com.dfsp.dashboard.storage.payload.UploadFileResponse;
import com.dfsp.dashboard.storage.services.DbFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping("/files/")
@RestController
public class FileDBController {

    private static final Logger logger = LoggerFactory.getLogger(FileDBController.class);


    private DbFileStorageService dBFileStorageService;

    public FileDBController(DbFileStorageService dBFileStorageService) {
        this.dBFileStorageService = dBFileStorageService;
    }

    @PostMapping("uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DbFile dbFile = dBFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("downloadfile/{file}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String file) {
        System.out.println(file);
        // Load file from database
        DbFile dbFile = dBFileStorageService.getFile(file);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @GetMapping("dblist")
    public ResponseEntity<List<DbFile>> getDBFileList() {

        List<DbFile> dbFiles  = new ArrayList<>();
        dBFileStorageService.getAllDbFiles()
                .stream()
                .map( f -> dbFiles.add(new DbFile(f.getId(), f.getFileName(), f.getFileType())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(dbFiles, HttpStatus.OK);
    }

    @DeleteMapping("deletedb/{file}")
    public ResponseEntity<String> deleteFile(@PathVariable String file) {
        dBFileStorageService.deleteFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

