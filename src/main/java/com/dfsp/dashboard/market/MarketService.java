package com.dfsp.dashboard.market;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MarketService {

    private FileManager fileManager;

    public MarketService(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public  Map<String, List<String>> parseXlsFileToMap(MultipartFile multipartFile) throws IOException {
        return fileManager.xlsToMap(multipartFile);
    }


}
