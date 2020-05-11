package com.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phone {
    public List<String> letterCom(String digits){
        List<String> result = new ArrayList();
        back(result, "", digits);
        return result;
    }
    private void back(List<String> result,String condition, String next_digits){
        Map<String,String> newHashMap = new HashMap();
        newHashMap.put("2","abc");
        newHashMap.put("3","def");
        newHashMap.put("4","ghi");
        newHashMap.put("5","jkl");
        newHashMap.put("6","mno");
        newHashMap.put("7","pqrs");
        newHashMap.put("8","tuv");
        newHashMap.put("9","wxyz");
        if(next_digits.length() ==0) {
            result.add(condition);
        }
        String letter = next_digits.substring(0,1);
        String  str = newHashMap.get(letter);
        for(int i=0;i<str.length();i++){
            back(result, condition+str.substring(i,i+1),next_digits.substring(1));
        }

    }


    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0){
            return new ArrayList();
        }

        Map<Integer,char[]> a = new HashMap();
        a.put((int)'2',new char[]{'a','b','c'});
        a.put((int)'3',new char[]{'d','e','f'});
        a.put((int)'4',new char[]{'g','h','i'});
        a.put((int)'5',new char[]{'j','k','l'});
        a.put((int)'6',new char[]{'m','n','o'});
        a.put((int)'7',new char[]{'p','q','r','s'});
        a.put((int)'8',new char[]{'t','u','v'});
        a.put((int)'9',new char[]{'w','x','y','z'});

        int totalLength = 1;
        for(int i= 0; i< digits.length();i++){
            totalLength  = totalLength * a.get((int)digits.charAt(i)).length;
        }
        List<char[]> list = new ArrayList(totalLength);
        for(int i=0;i<totalLength;i++){
            list.add(new char[digits.length()]);
        }
        int beforeLength = 1;
        for(int i=0;i<digits.length();i++){
            //对于第一个数字
            char[] ch = a.get((int)digits.charAt(i));

            for(int j=0;j<totalLength;j++){
                list.get(j)[i] = ch[j%(totalLength/beforeLength)/(totalLength/beforeLength/ch.length)];
            }
            beforeLength = beforeLength*ch.length;

        }
        List<String> result = new ArrayList();
        for(int i=0;i<list.size();i++){
            result.add(new String(list.get(i)));
        }
        return result;

    }
}
