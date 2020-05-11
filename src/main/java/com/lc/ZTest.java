package com.lc;

public class ZTest {
    public static void main(String[] args) {
        String s1 = new ZTest().convert("PAYPALISHIRING",3);
        System.out.printf(s1);
        Double d = 0.00;
        double d1 = 0.00;
        ((Double) d1).intValue();
        Integer s = 1;

    }
    public String convert(String s, int numRows) {
        //哪些排在第一行， 哪些排在最后一样
        int length = s.length();
        char[] s3 = new char[length];
        //只有一行
        if(numRows == 1){
            return s;
        }

        int currentLength = 0;
        for(int i=1;i<=numRows;i++){
            //numRows 超过本身长度
            if(i > length){
                break;
            }

            s3[currentLength++] = s.charAt(i-1);
            int tabRows = 2*(numRows-i);
            int tabRows2 = 2*(i-1);
            if(tabRows == 0 && tabRows2 == 0){
                //两个都=0要特殊处理
            }else {
                for (int j = i - 1; j < length; ) {

                    if (tabRows > 0) {
                        j = j + tabRows;
                        if (j < length) {
                            s3[currentLength++] = s.charAt(j);
                        } else {
                            break;
                        }
                    }
                    if (tabRows2 > 0) {
                        j = j + tabRows2;
                        if (j < length) {
                            s3[currentLength++] = s.charAt(j);
                        } else {
                            break;
                        }
                    }

                }
            }
        }
        return new String(s3);
    }
}
