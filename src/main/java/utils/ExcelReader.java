package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> getData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (InputStream fileStream = getFileStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("❌ Sheet not found: " + sheetName);
            }

            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                throw new RuntimeException("❌ Sheet is empty: " + sheetName);
            }

            // Read headers
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(formatter.formatCellValue(cell).trim());
            }

            // Read rows
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                Map<String, String> rowData = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = currentRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(i), formatter.formatCellValue(cell).trim());
                }
                dataList.add(rowData);
            }

        } catch (Exception e) {
            throw new RuntimeException("Runtime Error reading Excel file: " + filePath + " | " + e.getMessage(), e);
        }

        return dataList;
    }

    private static InputStream getFileStream(String filePath) throws FileNotFoundException {
        File fileFromPath = new File(filePath);
        if (fileFromPath.exists()) {
            return new FileInputStream(fileFromPath);
        }
        InputStream resourceStream = ExcelReader.class.getClassLoader().getResourceAsStream(filePath);
        if (resourceStream != null) {
            return resourceStream;
        }
        throw new FileNotFoundException("❌ Excel file not found at path or resources: " + filePath);
    }
}
