package com.lc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Mid {
    public static void main(String[] args) {
        String t  = "";
        char[] c =new char[t.length()];
        Mid mid = new Mid();
        mid.addNum(1);
        mid.addNum(2);
        int s = "".charAt(1);
        int b = s;
        char s2 = (char)b;


        System.out.printf(mid.findMedian()+"");
        mid.addNum(3);
        String s4 = "";

        int c22 = s4.charAt(1);
        System.out.printf(mid.findMedian()+"");
    }
    PriorityQueue<Integer> bigHeap = new PriorityQueue<Integer>();  //普通的堆， 顶上。
    PriorityQueue<Integer> smallHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    /** initialize your data structure here. */
    public Mid() {

    }

    public void addNum(int num) {
        if(bigHeap.size() == smallHeap.size() ){
            //
            if(smallHeap.size()==0){
                bigHeap.add(num);
            }else{

                int max = smallHeap.peek();
                if(max > num){
                    smallHeap.poll();
                    smallHeap.add(num);
                    bigHeap.add(max);
                }else{
                    bigHeap.add(num);
                }
            }
        }else{
            int min = bigHeap.peek();
            if(min < num){
                bigHeap.poll();
                bigHeap.add(num);
                smallHeap.add(min);
            }else{
                smallHeap.add(num);
            }
        }
    }

    public double findMedian() {
        if(bigHeap.size() == 0){
            return 0.00;
        }
        if(smallHeap.size() == bigHeap.size()){
            return (smallHeap.peek()+bigHeap.peek()+0.0)/2.0;
        }
        return bigHeap.peek()+0.0;
    }
}
