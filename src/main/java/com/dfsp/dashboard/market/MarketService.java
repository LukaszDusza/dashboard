package com.dfsp.dashboard.market;

import com.dfsp.dashboard.helpers.ManagerXLS;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MarketService {

    private FileExtractor fileExtractor;


    public Map<String, List<String>> extractXlsFile(MultipartFile multipartFile) {

        fileExtractor = new FileExtractor();
        try {
            fileExtractor.extractXLSFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
