package com.lc;

import java.util.*;

public class IntPalindroom {
    public static void main(String[] args) {

        new IntPalindroom().isPalindrome(10);
    }
    public boolean isPalindrome(int x) {
        //第一个数和最后一个数字
        if(x < 0){
            return false;
        }
        int length = 0;
        int x1 = x;
        while(x1 != 0){
            length = length+1;
            x1 = x1/10;

        }
        Map<Integer,char[]> a = new HashMap();
        a.put((int)'d',new char[10]);
        List list = new ArrayList();
        char[] as = new char[]{'f','f'};
        HashMap<Integer,Integer> has = new HashMap();
        int[] ins = new int[1];
        has.keySet().toArray(new Integer[1]);
        while(length > 2){


            int begin = x%10;
            int end=  ((Double) (x/ Math.pow(10,length-1))).intValue();


            if(begin != end){
                return false;
            }
            x = ((Double)((x % Math.pow(10,length-1))/10)).intValue();
            length = length-2;
        }
        return true;
    }
}
