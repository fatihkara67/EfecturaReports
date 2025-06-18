package Efectura.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class CommonExcelReader {
    //excel den veri okumam gerektiği zaman
    public static void updateCellValue(String filePath, String colName, int rowIndex, String newValue) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Başlık satırını al
        Row headerRow = sheet.getRow(0);

        // Sütun adını sütun indeksine çevir
        int colIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(colName)) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        // Sütun bulunamazsa hata fırlat
        if (colIndex == -1) {
            workbook.close();
            fileInputStream.close();
            throw new IllegalArgumentException("Sütun adı bulunamadı: " + colName);
        }

        // İlgili satırı al
        Row row = sheet.getRow(rowIndex);

        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        // İlgili hücreyi al veya oluştur
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }

        // Yeni değeri ayarla
        cell.setCellValue(newValue);

        // Değişiklikleri kaydet
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        workbook.close();
    }

    public static String getCellValue(String filePath, String colName, int rowIndex) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Başlık satırını al
        Row headerRow = sheet.getRow(0);

        // Sütun adını sütun indeksine çevir
        int colIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(colName)) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        // Sütun bulunamazsa hata fırlat
        if (colIndex == -1) {
            workbook.close();
            fileInputStream.close();
            throw new IllegalArgumentException("Sütun adı bulunamadı: " + colName);
        }

        // İlgili satırı al
        Row row = sheet.getRow(rowIndex);

        if (row == null) {
            workbook.close();
            fileInputStream.close();
            throw new IllegalArgumentException("Satır bulunamadı: " + rowIndex);
        }

        // İlgili hücreyi al
        Cell cell = row.getCell(colIndex);

        if (cell == null) {
            workbook.close();
            fileInputStream.close();
            throw new IllegalArgumentException("Hücre bulunamadı: " + colName + ", " + rowIndex);
        }

        // Hücre değerini oku ve doğru formatta dönüştür
        String cellValue;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    // Eğer hücredeki sayı çok büyükse, uzun (long) olarak almayı deneyin
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == (long) numericValue) {
                        cellValue = String.valueOf((long) numericValue);
                    } else {
                        cellValue = String.valueOf(numericValue);
                    }
                }
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }

        fileInputStream.close();
        workbook.close();

        return cellValue;
    }
    public static String getExcelPath(String fileName) {
        String projectDir = System.getProperty("user.dir");
        String relativePath = "src/test/resources/data/" + fileName + ".xlsx";
        return Paths.get(projectDir, relativePath).toString();
    }

    /**
     * Var olan bir Excel dosyasını açar ve belirtilen hücreyi kırmızı renkte boyar.
     *
     * @param filePath Excel dosyasının yolu
     * @param rowIndex Hücrenin satır numarası
     * @param colName Hücrenin sütun adı (örneğin: "A", "B", "C" vb.)
     * @throws IOException Dosya okuma/yazma hatası
     */
    public static void colorCellRedIn(String filePath, int rowIndex, String colName) throws IOException {
        // Mevcut Excel dosyasını okuyoruz
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // İlk sayfayı seçiyoruz

        // Sütun adını, sayısal sütun indeksine dönüştürüyoruz
        int colIndex = CellReference.convertColStringToIndex(colName);

        // Satırda hücreyi alıyoruz
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex); // Eğer satır yoksa yeni satır oluşturuyoruz
        }
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex); // Eğer hücre yoksa yeni hücre oluşturuyoruz
        }

        // Hücre stili oluşturuyoruz
        CellStyle cellStyle = workbook.createCellStyle();

        // Hücre dolgu rengini kırmızı yapıyoruz
        FillPatternType fillPattern = FillPatternType.SOLID_FOREGROUND;
        short redColor = IndexedColors.RED.getIndex(); // Kırmızı renk
        cellStyle.setFillForegroundColor(redColor);
        cellStyle.setFillPattern(fillPattern);

        // Hücreye stil uyguluyoruz
        cell.setCellStyle(cellStyle);

        // Dosyayı kaydediyoruz
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Kaynakları serbest bırakıyoruz
        fis.close();
        workbook.close();

        System.out.println("Excel dosyasındaki hücre kırmızı renkte boyandı.");
    }

}





