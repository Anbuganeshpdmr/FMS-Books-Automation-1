package UlilityPackage;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BasicUlitilityMethods {

    public static Sheet getSheetBySheetName(String filePath, String sheetName) throws FileNotFoundException {

        FileInputStream file = new FileInputStream(filePath);
        {
            Workbook workbook = null; // Load the workbook
            Sheet sheet;

            try {
                workbook = new XSSFWorkbook(file);
                sheet = workbook.getSheet(sheetName);
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return sheet;
        }
    }
}
