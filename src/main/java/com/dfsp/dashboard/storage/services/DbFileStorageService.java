package com.dfsp.dashboard.storage.services;


import com.dfsp.dashboard.model.MyFile;
import com.dfsp.dashboard.storage.exceptions.FileStorageException;
import com.dfsp.dashboard.storage.exceptions.MyFileNotFoundException;
import com.dfsp.dashboard.storage.model.DbFile;
import com.dfsp.dashboard.storage.repositories.DbFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DbFileStorageService {


    private DbFileRepository dbFileRepository;

    public DbFileStorageService(DbFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }

    public DbFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DbFile dbFile = new DbFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DbFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public List<DbFile> getAllDbFiles() {
        return dbFileRepository.findAll();
    }

    public void deleteFile(String fileId) {
       dbFileRepository.deleteById(fileId);
    }
}
