package edu.tamu.compassreport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

/**
 * Created by datamaskinaggie on 2/6/17.
 */
public class CsvXls {

    public static void csvToXLS(String csvdat, String name, String path) {
        String xlsFileAddress = path + "/" + name + ".xls"; //xlsx file address
        try {

            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet = workBook.createSheet(name);
            int RowNum=0;

            String[] csvlines = csvdat.split("\n");

            for (String csvline : csvlines) {
                String str[] = csvline.split(",");
                RowNum++;
                HSSFRow currentRow = sheet.createRow(RowNum);
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            FileOutputStream fileOutputStream =  new FileOutputStream(xlsFileAddress);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("CSV to XLS conversion done");
            Log log = new Log4JLogger();
            log.debug("CSV to XLS conversion done for sheet: " + sheet.getSheetName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage()+"Failed to write XLS: " + xlsFileAddress);
        }
    }
}
