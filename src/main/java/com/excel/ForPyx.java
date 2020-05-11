package com.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.CellType.*;

class TClass{
    public String teacher;
    public String date;
    public String time;
    public String className;

    @Override
    public String toString() {
        //return "teacher["+teacher+"] "+"date["+date+"]time["+time+"]className["+className+"]";
        return "teacher["+teacher+"] "+"time["+time+"]";
    }
}


public class ForPyx {

    public static void main(String[] args) {
        String teachDirectory = "/Users/handty/Desktop/pyx/teacher";
        String daoxiaoFile = "/Users/handty/Desktop/pyx/2月7日到校(1).xlsx";
        int year = 2020;
        int month = 2;
        int day = 7;
        List<TClass> teacherClassList = readTeacherDirectory(teachDirectory,year,month,day);
        List<TClass> daoxiaoClassList = readDaoxiao(daoxiaoFile);

        String teachOutput = "/Users/handty/Desktop/pyx/teacher1.txt";
        String daoxiaoOutput = "/Users/handty/Desktop/pyx/daoxiao1.txt";
        Collections.sort(teacherClassList, new Comparator<TClass>() {
            @Override
            public int compare(TClass o1, TClass o2) {
               if(o1.teacher.equals(o2.teacher)){
                   return o1.time.compareTo(o2.time);
               }
               return o1.teacher.compareTo(o2.teacher);
            }
        });

        Collections.sort(daoxiaoClassList, new Comparator<TClass>() {
            @Override
            public int compare(TClass o1, TClass o2) {
                if(o1.teacher.equals(o2.teacher)){
                    return o1.time.compareTo(o2.time);
                }
                return o1.teacher.compareTo(o2.teacher);
            }
        });

        writeToFile(teacherClassList,teachOutput);
        writeToFile(daoxiaoClassList,daoxiaoOutput);
    }



    private static void writeToFile(List<TClass> t1 ,String fileName){
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



    /**
     * 读老师的课表，把某个目录中所有的文件都读取出来，然后打印
     */
    private static List<TClass> readDaoxiao(String fileName) {
        File daoxiaoFile = new File(fileName);
        List<TClass> classList = readDaoxiao(daoxiaoFile);
        return classList;
    }


    private static List<TClass> readDaoxiao(File file){
        List<TClass> classList = new ArrayList<TClass>();
        log("开始读取文件"+file.getName());
        try(InputStream inputStream = new FileInputStream(file)){

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Iterator<Sheet> iterator =  workbook.sheetIterator();
            while(iterator.hasNext()){
                //筛选sheet
                XSSFSheet sheet = (XSSFSheet)iterator.next();
                int lastRowNum = sheet.getLastRowNum();
                for(int i=3;i<=lastRowNum;i++){
                    XSSFRow row = sheet.getRow(i);
                    if(row.getLastCellNum() > 7 && getCellValue(row.getCell(0)) != "序号"){
                        //
                        String time = getCellValue(row.getCell(4));
                        String className = getCellValue(row.getCell(5));
                        String teacher = getCellValue(row.getCell(6));
                        if((!time.equals("/") )&& (!time.equals("上课时间"))
                        && time!=null && !time.isEmpty()) {
                            TClass t = new TClass();
                            System.out.println(time);
                            t.className = className;
                            t.time = time;

                            if(teacher == null || teacher.isEmpty()) {
                                    teacher = getCellValue(sheet.getRow(i-1).getCell(6));
                            }
                            t.teacher = teacher;

                            classList.add(t);
                        }

                    }
                }

            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        return classList;

    }



    /**
     * 读老师的课表，把某个目录中所有的文件都读取出来，然后打印
     */
    private static List<TClass> readTeacherDirectory(String directory,int year,int month,int day) {
        List<TClass> classList = new ArrayList<TClass>();
        //String directory = "/Users/handty/Desktop/teacher2.6";
        File directoryFile = new File(directory);
        File[] files = directoryFile.listFiles();
        int length = files.length;
        log("老师文件数量" + length);
        for (int i = 0; i < length; i++) {
            File file = files[i];
            List<TClass> cList = readTeacherFile(file,year,month,day);
            classList.addAll(cList);
//            for(int j=0;j<cList.size();j++){
//                System.out.println(cList.get(j));
//            }
        }
        return classList;

    }

    private static List<TClass> readTeacherFile(File file,int year,int month,int day){
        List<TClass> classList = new ArrayList<TClass>();
        log("开始读取文件"+file.getName());
        try(InputStream inputStream = new FileInputStream(file)){

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Iterator<Sheet> iterator =  workbook.sheetIterator();
            XSSFSheet finalSheet = null;
            while(iterator.hasNext()){
                //筛选sheet
                XSSFSheet sheet = (XSSFSheet)iterator.next();
                String sheetName = sheet.getSheetName();
                String[] s = sheetName.split("年");
                if(s.length == 2){
                    int sYear = getNum(s[0]);
                    int sMonth = getNum(s[1]);
                    if(sYear == year && sMonth == month){
                        finalSheet = sheet;
                        break;
                    }
                }
            }
            if(finalSheet == null){
                log("未找到老师当月课表");
            }else{
                log("找到老师当月课表:"+ finalSheet.getSheetName());
            }
            //找到哪一行到哪一行是今天的记录
            int lastRowNum = finalSheet.getLastRowNum();
            int firstRow = -1;
            int cellIndex = -1;
            for(int i=0;i<= lastRowNum;i++){
                XSSFRow row = finalSheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                int firstCellNum = row.getFirstCellNum();
                for(int j = firstCellNum;j<lastCellNum;j++){
                    //System.out.println(i+"");
                    //System.out.println(j+"");
                    if(row.getCell(j)== null){
                        continue;
                    }
                    String cellStr = getCellValue(row.getCell(j));
                    //System.out.println(cellStr);
                    //判断是不是该天的记录
                    String[] cellM = cellStr.split("-");
                    if(cellM.length == 3){
                        int cYear = getNum(cellM[0]);
                        int cMonth = getNum(cellM[1]);
                        int cDay = getNum(cellM[2]);
                        if(cYear == year && cMonth == month && cDay == day){
                            //当天的记录
                            firstRow = i;
                            cellIndex = j;
                        }
                    }
                }
                if(cellIndex > -1){
                    break;
                }
            }

            if(firstRow > -1){
                //
                log("找到当日课表:"+getCellValue(finalSheet.getRow(firstRow).getCell(cellIndex)));
                //找到记录了
                while(true){
                    String time = getCellValue(finalSheet.getRow(++firstRow).getCell(0));
                    if(time!=null && !time.isEmpty()){
                        String className = getCellValue(finalSheet.getRow(firstRow).getCell(cellIndex));
                        if(className!=null && !className.isEmpty()){
                            //打印当天的课
                            log("时间:"+time+" 课程:"+className);
                            TClass t = new TClass();
                            t.time = time;
                            t.className = className;
                            t.teacher = getCellValue(finalSheet.getRow(0).getCell(0));
                            t.date = finalSheet.getSheetName();
                            classList.add(t);
                        }
                    }else{
                        break;
                    }
                }
            }else{
                log("该老师当天没有课");
            }


//            for (int i = 2; i <= sheet.getLastRowNum(); i ++) {
//                //XSSFRow row = sheet.getRow(i);
//                XSSFRow row = sheet.getRow(i);
//            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        return classList;

    }

    private static int getNum(String s){
        try {
            String reg = "[^0-9]";
            Pattern pattern = Pattern.compile(reg);
            Matcher m = pattern.matcher(s);
            return Integer.parseInt(m.replaceAll(""));
        }catch (Exception ex){
            return 0;
        }

    }

    private static void log(String s ){
        System.out.println(s);
    }


    private static String getCellValue(XSSFCell cell){
        if(cell == null){
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
                }else {

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
