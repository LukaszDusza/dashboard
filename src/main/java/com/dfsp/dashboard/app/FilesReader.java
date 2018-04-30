package com.dfsp.dashboard.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FilesReader {

    private final String fileLangPath = "src/main/resources/config/lang/";
    private final String sufix = ".properties";

    public Map<String, String> readLangfile(String file, String lang) {

        Map<String, String> result = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(fileLangPath + file + sufix);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String textLine = bufferedReader.readLine();

            do {
                if(textLine != null ) {
                  String[] content = textLine.split("\\.");
                    if(lang.equals(content[0])) {
                       String[] parts = content[1].split("=");
                        result.put(parts[0], parts[1]);
                    }
                }
                textLine = bufferedReader.readLine();
            } while(textLine != null);
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
