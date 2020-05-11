package com.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arr {
    public static void main(String[] args) {
        Set<String> s = new HashSet();
        s.toArray(new String[0]);
        //String[] s = new Arr().permutation("abc");
    }
    public String[] permutation(String s) {
        if(s==null || s.length()== 0){
            return new String[0];
        }
        List<char[]> charList= new ArrayList();
        char[] c = new char[s.length()];

        backTracking(charList, c, 0, s,new HashSet());
        String[] s1 = new String[charList.size()];
        for(int i=0;i<charList.size();i++){
            s1[i] = new String(charList.get(i));
        }
        return s1;

    }
    private void backTracking(List<char[]> charList, char[] currentCharArray, int currentLength, String s,
                              Set<Integer> set){
        for(int j=0;j<s.length();j++){
            char ch = s.charAt(j);
            boolean has = false;

            if(set.contains(j)){
                continue;
            }
            currentCharArray[currentLength] = ch;
            set.add(j);
            if(currentLength == s.length()-1){
                char[] r1 = new char[currentCharArray.length];
                for(int k=0;k<currentCharArray.length;k++){
                    r1[k] = currentCharArray[k];
                    charList.add(r1);
                }

            }else{
                backTracking(charList,currentCharArray, currentLength+1,s,set);
            }
            currentCharArray[currentLength] = 0;
            set.remove(j);
        }



    }
}
