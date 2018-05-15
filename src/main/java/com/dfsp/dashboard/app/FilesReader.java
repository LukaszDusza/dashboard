package com.dfsp.dashboard.app;

import com.dfsp.dashboard.entities.Cell;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class FilesReader {

    private final String fileLangPath = "src/main/resources/config/lang/";
    private final String report_files = "src/main/resources/report_files/";
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

    public List<Map<String,String>> csvParser(String filePath) {

        List < Map < String, String >> list = new ArrayList < > ();
        try (InputStream in = new FileInputStream(report_files + filePath)) {
            CSV csv = new CSV(true, ',', in );
            List < String > fieldNames = null;
            if (csv.hasNext()) fieldNames = new ArrayList <> (csv.next());

            while (csv.hasNext()) {
                List < String > x = csv.next();
                Map < String, String > obj = new LinkedHashMap < > ();
                for (int i = 0; i < fieldNames.size(); i++) {
                    obj.put(fieldNames.get(i), x.get(i));
                }
                list.add(obj);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(System.out, list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
