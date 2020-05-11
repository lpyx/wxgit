package com.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEx {
    TestEx(){

    }

    public static void main(String[] args) {

        String fileName = "/Users/handty/Desktop/0507.xlsx";
        String outputFileName = "/Users/handty/Desktop/0507_output408.sql";
        List<String> result = readDaoxiao(new File(fileName));
        System.out.println(result.size());
        writeToFile(result,outputFileName);
    }

    private static void writeToFile(List<String> t1 ,String fileName){
        File file = new File(fileName);
        FileOutputStream fos = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            for(int i=0;i<t1.size();i++){
                fos.write((t1.get(i)+"\r\n").getBytes("UTF-8"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }

    }
    public static List<String> readDaoxiao(File file) {
        List<String> resultList = new ArrayList();
        try (InputStream inputStream = new FileInputStream(file)) {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //筛选sheet
            XSSFSheet sheet = (XSSFSheet) workbook.getSheet("sheet1");
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);

                //
                String controlId = getCellValue(row.getCell(0));
                String value = getCellValue(row.getCell(1));

                resultList.add("update sopdef.sop_control set  standard_logic=0,input_standard = CAST(trim('"+value+"') as JSON) where org_id = 408 and id = "+controlId+";");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;

    }

    private static String getCellValue(XSSFCell cell) {
        if (cell == null) {
            return null;
        }
        String value = "";
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case _NONE:
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    } else {
                        value = "";
                    }
                } else {

                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case BLANK: // 空值
                value = "";
                break;
            case ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;

    }
}
