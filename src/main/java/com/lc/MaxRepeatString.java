package com.lc;

import java.util.HashMap;
import java.util.Map;

public class MaxRepeatString {
    public static void main(String[] args) {

        String s = "";
        int a =  (int)s.charAt(1);
    }

    public int lengthOfLongestSubstring(String s) {
        int repeatIndex = -1;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int maxSize = 0;
        int currentSize = 0;
        if(s == null) return 0;
        for(int i=0;i<s.length();i++){
            Integer char1 = (int)s.charAt(i);
            if(!map.containsKey(char1)){
                map.put(char1,i);
                currentSize = currentSize+1;
            }else{
                Integer oldIndex= map.get(char1);
                map.put(char1,i);
                if(oldIndex < repeatIndex){
                    currentSize = currentSize+1;
                }else{

                    if(maxSize < currentSize){
                        maxSize = currentSize;
                    }

                    repeatIndex = oldIndex;
                    currentSize = i - oldIndex;

                }
            }
        }
        if(maxSize < currentSize){
            maxSize = currentSize;
        }
        return maxSize;
    }
}
