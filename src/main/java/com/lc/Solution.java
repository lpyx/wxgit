package com.lc;

import org.jetbrains.annotations.NotNull;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Set<Integer> s = new HashSet<Integer>();
        System.out.printf(Integer.MAX_VALUE+"");
        int[] heights = new int[]{2,3,1,2,4,3};
        int[] heights2 = new int[]{3};
        //new Solution().minSubArrayLen(7, heights);
    }

    public int minSubArrayLen(int s, int[] nums) {
        int beginIndex = 0;
        int sum = 0;

        int minLength = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            if(sum >= s){
                if(nums[i] >= s){
                    return 1;
                }
                int length = i-beginIndex+1;
                if(length < minLength){
                    minLength = length;
                }
                int j = beginIndex;
                for(;j < i;j++){
                    sum = sum - nums[j];
                    //一直减到小于它
                    if(sum < s){
                        beginIndex = j+1;
                        break;
                    }else{

                    }
                }
                //j的位置加起来小于0

                int nowLength = i - j +1;
                if(nowLength < minLength){
                    minLength = nowLength;
                }
            }
        }
        return minLength;
    }
}