package com.demoblaze.utils;

import com.demoblaze.exceptions.ExcepcionesExcel;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.demoblaze.exceptions.ExcepcionesExcel.*;
import static com.demoblaze.utils.Constants.RUTA_EXCEL1;


public class CallData {

    private CallData () {
    }

    public static List<Map<String, String>> extractToLogin(){
        List<Map<String, String>> dataLogin = new ArrayList<>();
        try {
            dataLogin = Excel.leerDatosDeHojaDeExcel(RUTA_EXCEL1,"DatosLogin");
        } catch (IOException e) {
            throw new ExcepcionesExcel(MENSAJE_EXCEL_LOGIN + RUTA_EXCEL1, e);
        }
        return dataLogin;
    }

    public static List<Map<String, String>> extractToVariables(){
        List<Map<String, String>> url = new ArrayList<>();
        try {
            url = Excel.leerDatosDeHojaDeExcel(RUTA_EXCEL1,"Variables");
        } catch (IOException e) {
            throw new ExcepcionesExcel(MENSAJE_EXCEL_VARIABLES + RUTA_EXCEL1, e);
        }
        return url;
    }



    private static List<String> setExcelDataToFeature(File featureFile) throws IOException {
        List<String> fileData = new ArrayList<>();
        try (BufferedReader buffReader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), StandardCharsets.UTF_8))) {
            List<Map<String, String>> excelData = null;
            boolean foundHashTag = false;
            boolean featureData = false;
            String data;
            while ((data = buffReader.readLine()) != null) {
                if (isExternalDataTag(data)) {
                    excelData = processExternalDataTag(data);
                    foundHashTag = true;
                    fileData.add(data);
                }
                if (foundHashTag) {
                    addExcelDataToFeature(fileData, excelData);
                    foundHashTag = false;
                    featureData = true;
                    continue;
                }
                if (data.startsWith("|") || data.endsWith("|")) {
                    if (!featureData) {
                        fileData.add(data);
                    }
                } else {
                    featureData = false;
                    fileData.add(data);
                }
            }
        }
        return fileData;
    }
    private static boolean isExternalDataTag(String data) {
        return data.trim().contains("##@externaldata");
    }
    private static List<Map<String, String>> processExternalDataTag(String data) throws IOException {
        String excelFilePath = data.substring(StringUtils.ordinalIndexOf(data, "@", 2) + 1, data.lastIndexOf("@"));
        String sheetName = data.substring(data.lastIndexOf("@") + 1);
        return new Excel().getData(excelFilePath, sheetName);
    }
    private static void addExcelDataToFeature(List<String> fileData, List<Map<String, String>> excelData) {
        for (int rowNumber = 0; rowNumber < excelData.size() -1; rowNumber++) {
            StringBuilder cellData = new StringBuilder();
            for (Map.Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {
                cellData.append("|").append(mapData.getValue());
            }
            fileData.add(cellData + "|");
        }
    }

    private static List<File> listOfFeatureFiles(File folder) {
        List<File> featureFiles = new ArrayList<>();
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                featureFiles.addAll(listOfFeatureFiles(fileEntry));
            } else {
                if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
                    featureFiles.add(fileEntry);
                }
            }
        }
        return featureFiles;
    }

    public static void overrideFeatureFiles(String featuresDirectoryPath) throws IOException{
        List<File> listOfFeatureFiles = listOfFeatureFiles(new File(featuresDirectoryPath));
        for (File featureFile : listOfFeatureFiles) {
            List<String> featureWithExcelData = setExcelDataToFeature(featureFile);
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(featureFile), StandardCharsets.UTF_8));) {
                for (String string : featureWithExcelData) {
                    writer.write(string);
                    writer.write("\n");
                }
            }
        }
    }
}
