package com.lc;

public class Palindroom {
    public static void main(String[] args) {
        String s = new Palindroom().longestPalindrome("ccc");
        System.out.printf(s);

    }
    public String longestPalindrome(String s) {
        if(s == null ||s.equals("")){
            return s;
        }
        char[] s2 = new char[20];
        new String(s2);
        int length = s.length();
        int maxLength = 0;
        int maxBegin = -1;
        int maxEnd = -1;
        for(int i=0;i<length;i++){
            char currentChar = s.charAt(i);
            int begin = i;
            int end = i;
            //得到相同的字符串
            for(int j= i+1; j< length;j++){
                if(s.charAt(j)==currentChar){
                    end++;
                    i++;
                }else{
                    break;
                }
            }
            //这里可以得到
            while(begin >= 0 && end < length){
                if(s.charAt(begin) == s.charAt(end)){
                    begin--;
                    end++;
                }else{
                    break;
                }
            }
            //begin,end为当前的长度
            int currentMax = end-begin-1;
            if(maxLength < currentMax){
                maxLength = currentMax;
                maxBegin = begin+1;
                maxEnd = end-1;
            }

        }
        return s.substring(maxBegin,maxEnd+1);


    }
}
