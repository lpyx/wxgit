package com.lc;

public class My1 {
    public static void main(String[] args) {

        new My1().findNthDigit(10000);
    }
    public int findNthDigit(int n) {
        //1  0-9 0-9
        //2. 10-99 90*2  189
        //3. 100-999. 900*3  2889
        //4. 1001 -9999 9000*4
        //10000 -2889 = 7111
        //7111/4
        //11777+
        int[] i1 = new int[8];
        i1[0] = 9;
        for(int i=1;i<8;i++){
            i1[i] = (int)Math.pow(10,i)*9*(i+2)+i1[i-1];
        }
        //这个数字的区间
        int i = 0;
        for(i=0;i<8;i++){
            if(n <= i1[i]){
                break;
            }
        }
        if(i == 0){
            return n;
        }

        int currentSize = n - i1[i-1];  //currentSize = 2  i = 1
        int currentNum = (currentSize-1)/(i+1)+(int)Math.pow(10,i);//第几个数字
        int currentDigit = (currentSize-1)%(i+1);


        return int1(currentNum,currentDigit);


    }

    private int int1(int currentNum,int currentDigit){
        //10  0
        //weishu = 2
        int weishu = 0;
        int cn = currentNum;
        while(cn > 0){
            cn = cn/10;
            weishu++;
        }

        return currentNum/(int)Math.pow(10,weishu-currentDigit-1)%10;
    }
}
