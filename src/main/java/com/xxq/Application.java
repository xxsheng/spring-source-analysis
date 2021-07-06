package com.xxq;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class Application {

    static String excelPath = "D:\\08_file\\6月活跃门店.xlsx";
    static  String memberPath = "D:\\08_file\\ads脚本sql.sql";
    static String  timeText = "D:\\08_file\\time.txt";

    static Map<String, String> map = new HashMap<String, String>();
    static Map<String, String> timeMap = new HashMap<>();
    public static void main(String[] args) throws IOException, InvalidFormatException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(timeText)));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(",");
            if (strings.length == 3) {
                map.put(strings[1], strings[2]);
            }
        }


        Workbook sheets = WorkbookFactory.create(new File(excelPath));
        Sheet sheetAt = sheets.getSheetAt(0);
        Iterator<Row> rowIterator = sheetAt.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();
            Cell cell1 = next.getCell(2);
            cell1.setCellType(CellType.STRING);
            String stringCellValue = cell1.getStringCellValue();
            String member = map.get(stringCellValue);
            Cell cell = next.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (member != null) {
                cell.setCellValue(member);
            } else {
//                cell.setCellValue("收银2.0会员");
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\08_file\\6月活跃门店1.xlsx"));
        sheets.write(fileOutputStream);
        fileOutputStream.flush();
    }
}
