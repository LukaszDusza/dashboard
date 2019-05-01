package com.dfsp.dashboard.market;

import com.dfsp.dashboard.config.Constans;
import com.dfsp.dashboard.helpers.ManagerXLS;
import com.dfsp.dashboard.model.MarketModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.dfsp.dashboard.config.Constans.LOCAL_FILES_PATH_RAPORTS;

@Service
public class MarketService {

    private FileManager fileManager;

    public MarketService(FileManager fileManager) {
        this.fileManager = fileManager;
    }


    public String getJsonData(Map<Cell, List<Cell>> collection) {
        return new Gson().toJson(collection);
    }


    /*glowna metoda zamieniajaca plik XLS na JSON i zapisujaca na dysk*/
    public void marketModelXlsToJsonFile(MultipartFile multipartFile) {
        ManagerXLS<MarketModel> xls = new ManagerXLS<>(MarketModel.class);
        try {
            fileManager.writeToJsonFile(xls.saveXLSToList(multipartFile), LOCAL_FILES_PATH_RAPORTS);
        } catch (IOException | InstantiationException | InvocationTargetException | ParseException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
