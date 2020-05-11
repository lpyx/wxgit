package com.lc;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class T1 {
    static class t1 implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    }
    public static void main(String[] args) {

        Arrays.sort(new Integer[0],new t1());

        new T1().wordPattern("abba","dog cat cat dog");
    }
    public boolean wordPattern(String pattern, String str) {
        Map<Integer, List<Integer>> a = new HashMap();
        char[] c = pattern.toCharArray();
        String[] list1 =  str.split(" ");
        if(c.length != list1.length){
            return false;
        }
        for(int i=0;i<c.length;i++){
            int c1 = c[i];
            if(!a.containsKey(c1)){
                a.put(c1,new ArrayList());
            }
            a.get(c1).add(i);
        }
        Iterator<List<Integer>> ite = a.values().iterator();
        while(ite.hasNext()){
            List<Integer> list = ite.next();
            for(int i=1;i<list.size();i++){
                if(!list1[list.get(i)].equals(list1[list.get(0)])){
                    return false;
                }
            }
        }
        return true;
    }
}
