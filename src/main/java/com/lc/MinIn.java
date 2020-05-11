package com.lc;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;

public class MinIn {
    public static void main(String[] args) {

        List<Integer> a = null;
        int[] a1 = new int[2];
        int[] a3 = Ints.toArray(a);
        int[] A = new int[]{3,2,1,2,1,7};
        new MinIn().minIncrementForUnique(A);
    }
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int count = 0;
        for(int i=1;i<A.length;i++){
            if(A[i] > A[i-1]){

            }else{
                count += A[i-1] - A[i]+1;
                A[i] = A[i-1]+1;


            }
        }
        return count;

    }
}
