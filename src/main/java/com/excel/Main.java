package com.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        readExcel();
    }

    private static void readExcel() {
        String filePath = "/Users/handty/Desktop/1030def.xls";
        String output = "/Users/handty/Desktop/supplier.sql";
        try {
            File excelFile = new File(filePath);
            InputStream inputStream = new FileInputStream(excelFile);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(2);
            System.out.println(sheet.getLastRowNum() + " --- " + sheet.getLastRowNum());
            File outputFile = new File(output);
            outputFile.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

            for (int i = 2; i <= sheet.getLastRowNum(); i ++) {
                HSSFRow row = sheet.getRow(i);
                StringBuilder stringBuilder = new StringBuilder();
                String  code =  row.getCell(0).getStringCellValue();
                String  name = row.getCell(1).getStringCellValue();
                stringBuilder.append("insert into def.supplier(org_id,code,name,remark,status,attachments) values(");
                stringBuilder.append("405,");
                stringBuilder.append("'").append(code).append("'").append(",");
                stringBuilder.append("'").append(name).append("'").append(",");
                stringBuilder.append("'',1,'[]'");
                stringBuilder.append(");\r\n");

                out.write(stringBuilder.toString());
            }

            out.flush();
            out.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}