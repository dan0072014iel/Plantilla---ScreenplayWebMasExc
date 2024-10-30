package com.demoblaze.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class Excel {

    Excel() {
    }

    public record LogEntry(String level, String logger, String message, String status, String thread, String tester) {
    }

    public static List<Map<String, String>> leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel) throws IOException {
        List<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList<>();
        Map<String, String> informacionProyecto;

        try (FileInputStream inputStream = new FileInputStream(new File(rutaDeExcel));
             XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
            Iterator<Row> rowIterator = newSheet.iterator();
            Row titulos = rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                informacionProyecto = new HashMap<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    Cell titleCell = titulos.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String title = titleCell.toString();

                    switch (cell.getCellType()) {
                        case STRING:
                            informacionProyecto.put(title, cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            informacionProyecto.put(title, String.valueOf((long) cell.getNumericCellValue()));
                            break;
                        case BLANK:
                            informacionProyecto.put(title, "");
                            break;
                        default:
                    }
                }
                arrayListDatoPlanTrabajo.add(informacionProyecto);
            }
        }

        return arrayListDatoPlanTrabajo;
    }


    public static void escribirDatosEnHojaDeExcel(String rutaDeExcel, String hojaDeExcel, LogEntry logEntry) throws IOException {
        File file = new File(rutaDeExcel);

        try (FileInputStream inputStream = file.exists() ? new FileInputStream(file) : null;
             XSSFWorkbook workbook = inputStream != null ? new XSSFWorkbook(inputStream) : new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.getSheet(hojaDeExcel);
            if (sheet == null) {
                sheet = workbook.createSheet(hojaDeExcel);
            }

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            int rowCount = sheet.getLastRowNum();
            if (rowCount == -1 && sheet.getRow(0) == null) {
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Fecha ejecucion");
                headerRow.createCell(2).setCellValue("Nivel de registro");
                headerRow.createCell(3).setCellValue("Tipo prueba");
                headerRow.createCell(4).setCellValue("Nombre caso de prueba");
                headerRow.createCell(5).setCellValue("Status");
                headerRow.createCell(6).setCellValue("Hilo");
                headerRow.createCell(7).setCellValue("Tester");
                for (int i = 0; i < 8; i++) {
                    headerRow.getCell(i).setCellStyle(headerStyle);
                }
                rowCount = 0;
            }

            Row row = sheet.createRow(rowCount + 1);
            String id = String.valueOf(rowCount + 1);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(timestamp);
            row.createCell(2).setCellValue(logEntry.level());
            row.createCell(3).setCellValue(logEntry.logger());
            row.createCell(4).setCellValue(logEntry.message());
            row.createCell(5).setCellValue(logEntry.status());
            row.createCell(6).setCellValue(logEntry.thread());
            row.createCell(7).setCellValue(logEntry.tester());

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        }
    }




    public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws IOException {
        Sheet sheet = getSheetByName(excelFilePath, sheetName);
        return readSheet(sheet);
    }

    private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException {
        return getWorkBook(excelFilePath).getSheet(sheetName);
    }

    private Workbook getWorkBook(String excelFilePath) throws IOException {
        return WorkbookFactory.create(new File(excelFilePath));
    }

    private List<Map<String, String>> readSheet(Sheet sheet) {
        Row row;
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<>();
        int headerRowNumber = getHeaderRowNumber(sheet);
        if (headerRowNumber != -1) {
            int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
            int setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                }
                excelRows.add(columnMapdata);
            }
        }
        return excelRows;
    }

    private int getHeaderRowNumber(Sheet sheet) {
        Row row;
        int totalRow = sheet.getLastRowNum();
        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
            row = getRow(sheet, currentRow);
            if (row != null) {
                int totalColumn = row.getLastCellNum();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    Cell cell;
                    cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Set<CellType> validCellTypes = EnumSet.of(CellType.STRING, CellType.NUMERIC, CellType.BOOLEAN, CellType.ERROR);
                    if (validCellTypes.contains(cell.getCellType())) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return (-1);
    }

    private Row getRow(Sheet sheet, int rowNumber) {
        return sheet.getRow(rowNumber);
    }

    private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
        if (row == null) {
            addEmptyHeaderIfNotBlank(sheet, columnMapdata, currentColumn);
            return columnMapdata;
        }
        Cell cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Function<Cell, String> valueExtractor = getValueExtractor(cell);
        if (valueExtractor != null) {
            String columnHeaderName = getColumnHeaderName(sheet, cell);
            columnMapdata.put(columnHeaderName, valueExtractor.apply(cell));
        }
        return columnMapdata;
    }

    private void addEmptyHeaderIfNotBlank(Sheet sheet, LinkedHashMap<String, String> columnMapdata, int currentColumn) {
        Cell headerCell = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        if (headerCell.getCellType() != CellType.BLANK) {
            String columnHeaderName = headerCell.getStringCellValue();
            columnMapdata.put(columnHeaderName, "");
        }
    }

    private Function<Cell, String> getValueExtractor(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return Cell::getStringCellValue;
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return c -> NumberToTextConverter.toText(c.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BLANK) {
            return c -> "";
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return c -> Boolean.toString(c.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.ERROR) {
            return c -> Byte.toString(c.getErrorCellValue());
        }
        return null;
    }

    private String getColumnHeaderName(Sheet sheet, Cell cell) {
        return sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
    }
}