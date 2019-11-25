package com.util;

import javax.sound.midi.Soundbank;
import java.io.UnsupportedEncodingException;

public class HexUtil {
    public static void main(String[] args) {
        String hexString = "1703 0300 2964 027c 7acb d951 07e2 ad52 71ce cb31 36fb 0cfb f8b0 1b39 a408 c9d1 f14e eaa4 7c0b 1003 5c99 a146 30a2";


        String hexStrins = "1503 0300 1a64 027c 7acb d951 080a 1a7e 6eb4 763d 276a 5bcd 8404 549f a34f 58";

        String hexStrina = "1503 0300 1a00 0000 0000 0000 0511 afab ed9a af9a e500 90b2 3dd5 ebfd 7de3 09";
        hexString.replaceAll(" ","");

        byte[] bytes = HexStringToBinary(hexString);
        try {
            for(int i=0;i<bytes.length;i++){
                System.out.println((char)bytes[i]);
            }
          //  System.out.printf(new String(bytes,"ISO-8859-1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] HexStringToBinary(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位

        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexString.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexString.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }
}
