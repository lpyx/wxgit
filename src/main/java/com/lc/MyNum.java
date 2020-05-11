package com.lc;

import java.util.Arrays;
import java.util.Comparator;

public class MyNum {
    public static void main(String[] args) {

        String s = new MyNum().minNumber(
                new int[]{3,30}
        );
        System.out.printf(s);
    }
    public String minNumber(int[] nums) {
        Integer[] a = new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
            a[i] = nums[i];
        }
        Arrays.sort(a, new MyCompare());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<a.length;i++){
            sb.append(a[i]+"");
        }
        return sb.toString();
    }

    static class MyCompare implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = o1+"";
            String  s2= o2+"";
            return compare(s1,s2);
        }


        private int compare(String s1,String s2){
            int length1 = s1.length();
            int length2 = s2.length();
            if(length1 < length2){
                return 0-compare(s2,s1);
            }
            if(length1 >= length2){
                for(int i=0;i<length2;i++){
                    if(s1.charAt(i) > s2.charAt(i)){
                        return 1;
                    }
                    if(s1.charAt(i) < s2.charAt(i)){
                        return -1;
                    }


                }
                int t = length1/length2;
                for(int j=1;j<t;j++){
                    for(int i=0;i<length2;i++){
                        if(s1.charAt((j)*length2+i) > s2.charAt(i%length2)){
                            return 1;
                        }
                        if(s1.charAt((j)*length2+i) < s2.charAt(i%length2)){
                            return -1;
                        }
                    }
                }
                int yushu = length1 - length2*(t);
                int begin = (t)*length2;
                if(yushu == 0){
                    return 0;
                }
                return compare(s1.substring(begin),s2);

            }
            return 0;
        }

    }
}
