package com.excel;

import com.google.gson.Gson;
import javassist.tools.web.BadHttpRequest;
import org.apache.commons.net.bsd.RLoginClient;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 亚大专属 sql转换 第二版
 * 标准由我们自己转换，
 * 用户提供三列： 控件ID，标准名称和标准字段
 * 标准名称如下： ＜＝  允差  区间
 */


public class TestEx2 {
    TestEx2(){

    }

    public static int controlIdCellNum = 8;
    public static int standardLogicCellNum = 10;
    public static int standardValueCellNum = 11;
    public static String sheetName = "Sheet1";
    public static Long ORG_ID = 353L;
    static String directory = "/Users/handty/Desktop/工作文件/亚大/20201211/";
    static String fileName = directory+"东250-1燃气(1) 1.xlsx";
    static String outputFileName = directory+"20201211_output353东250-1燃气.sql";
    static String errorFileName = directory+"error_20201211_output353东250-1燃气.txt";


    static Gson gson  =new Gson();

    public static void main(String[] args) {
        List<String> result = readDaoxiao(new File(fileName),ORG_ID);
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
    public static List<String> readDaoxiao(File file,Long orgId) {
        List<String> resultList = new ArrayList();
        List<String> errorList = new ArrayList();
        try (InputStream inputStream = new FileInputStream(file)) {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //筛选sheet
            XSSFSheet sheet = (XSSFSheet) workbook.getSheet(sheetName);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);

                //
                if(row == null){
                    continue;
                }
                String controlId = getCellValue(row.getCell(controlIdCellNum));
                String standardLogic = getCellValue(row.getCell(standardLogicCellNum));
                String standardValue = getCellValue(row.getCell(standardValueCellNum));

                try{
                        InputStandardDO standard = getStandardValue(standardLogic,standardValue);
                        if(standard != null ) {
                            String value = gson.toJson(standard);

                            resultList.add("update sopdef.sop_control set  standard_logic=0,input_standard = CAST(trim('" + value + "') as JSON) where org_id = " + orgId + " and id = " + controlId + ";");
                        }

                    }catch (Exception e1){
                        String errorMsg = controlId+" "+standardLogic+" "+standardValue;
                        errorList.add(errorMsg);
                        System.out.println(errorMsg);
                    }



            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        writeToFile(errorList, errorFileName);
        return resultList;

    }

    private static InputStandardDO getStandardValue(String logic,String value)throws Exception{
        if(logic == null) return null;
        if(value == null) return null;

        logic = logic.trim();
        value = value.trim();
        if(logic.isEmpty()) return null;
        if(value.isEmpty()) return null;
        LogicEnum logicEnum = LogicEnum.findByMessage(logic);
        if(logicEnum == null) throw new Exception("未找到对应的标准");
        InputStandardDO stan =  logicEnum.getStandardValue(value);
        if(stan == null) throw new Exception("标注字段有问题");
        return stan;

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



class InputStandardDO{
    int logic;
    Double max;
    Double min;

    public InputStandardDO(int logic,Double max,Double min, Double base){
        this.logic= logic;
        this.max = max;
        this.min =min;
        this.base = base;
    }

    public InputStandardDO(int logic, Double base){
        this.logic= logic;
        this.base = base;
    }

    public int getLogic() {
        return logic;
    }

    public void setLogic(int logic) {
        this.logic = logic;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    Double base;



}
enum  LogicEnum{
    BETWEEN(0,"区间"){
        @Override
        InputStandardDO getStandardValue(String value) {
            String[]  list = value.split("\\|");
            if(list.length == 2){
                Double d1 = Double.parseDouble(list[0]);
                Double d2 = Double.parseDouble(list[1]);
                if(d1 != null && d2 != null){
                    return new InputStandardDO(
                            this.type,
                            d2,
                            d1,
                            null


                    );
                }
            }
            return null;

        }
    },
    LT(1,"<"){
        @Override
        InputStandardDO getStandardValue(String value) {
            Double d1 = Double.parseDouble(value);
            if(d1 != null){
                return new InputStandardDO(
                        this.type,
                        d1
                );
            }
            return null;
        }
    },
    GT(2,">"){
        @Override
        InputStandardDO getStandardValue(String value) {
            Double d1 = Double.parseDouble(value);
            if(d1 != null){
                return new InputStandardDO(
                        this.type,
                        d1
                );
            }
            return null;
        }
    },

    EQ(3,"="){
        @Override
        InputStandardDO getStandardValue(String value) {
            Double d1 = Double.parseDouble(value);
            if(d1 != null){
                return new InputStandardDO(
                        this.type,
                        d1
                );
            }
            return null;
        }
    },
    LTE(4,"<="){
        @Override
        InputStandardDO getStandardValue(String value) {
            Double d1 = Double.parseDouble(value);
            if(d1 != null){
                return new InputStandardDO(
                        this.type,
                        d1
                );
            }
            return null;
        }
    },
    GTE(5,">="){
        @Override
        InputStandardDO getStandardValue(String value) {
            Double d1 = Double.parseDouble(value);
            if(d1 != null){
                return new InputStandardDO(
                        this.type,
                        d1
                );
            }
            return null;
        }
    },

    TOLERANCE(8,"允差"){
        @Override
        InputStandardDO getStandardValue(String value) {
            String[]  list = value.split("\\|");
            if(list.length == 3){
                BigDecimal d1 = new BigDecimal(list[0]);
                BigDecimal d2 = new BigDecimal(list[1]);
                BigDecimal d3 = new BigDecimal(list[2]);
                if(d1 != null && d2 != null){
                    return new InputStandardDO(
                            this.type,
                            d1.add(d2).doubleValue(),
                            d1.add(d3).doubleValue(),
                            d1.doubleValue()

                    );
                }
            }
            return null;
        }
    };
    abstract InputStandardDO getStandardValue(String value);
    static Map<String,LogicEnum> logicEnumMap = new HashMap<>();
    static {
        LogicEnum[] values = LogicEnum.values();
        int length = values.length;
        for(int i=0;i<length;i++){
            logicEnumMap.put(values[i].message, values[i]);
        }
    }

    int type;
    String message;
    LogicEnum(int type,String message){
        this.type = type;
        this.message = message;
    }
    public static LogicEnum findByMessage(String message){
        String findMessage = message.replaceAll("＜","<")
                .replaceAll("＝","=");
        return logicEnumMap.get(findMessage);
    }
}
