package com.lc;

public class TestSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-2,-3,-4,-5};
        int target = -8;
        int[] ss = new TestSum().twoSum(nums,target);
        System.out.printf(ss.toString());
    }
    public int[] twoSum(int[] nums, int target) {
        if(nums == null){
            return null;
        }
        if(nums.length < 2){
            return null;
        }
        int[] sortedArray = sort(nums);
        int length = nums.length;
        int begin = 0;
        int end = length-1;
        while(begin < end){
            int sum = sortedArray[begin]+sortedArray[end];
            if(sum == target){
                break;
            }
            if(sum > target){
                end = end -1;
            }else{
                begin = begin+1;
            }
        }
        if(begin >= end){
            return null;
        }
        int beginOrigin = -1;
        int endOrigin = -1;



        for(int i=0;i<length;i++){
            if(nums[i] == sortedArray[begin] && beginOrigin  == -1){
                beginOrigin = i;
            }else if(nums[i] == sortedArray[end]){
                endOrigin = i;
            }
        }
        return new int[]{beginOrigin, endOrigin};

    }

    private int[] sort(int[] nums){
        int[] arrays = new int[nums.length];
        int length = nums.length;
        for(int i=0;i<length;i++){
            arrays[i] = nums[i];
        }

        for(int i=0;i<length-1;i++){
            for(int j=0;j<length-i-1;j++){
                if(arrays[j] > arrays[j+1]){
                    int t = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = t;
                }
            }
        }
        return arrays;
    }
}
