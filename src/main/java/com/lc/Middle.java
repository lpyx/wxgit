package com.lc;

public class Middle {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if(length1 > length2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            int tempI = length1;
            length1 = length2;
            length2 = tempI;
        }

        int min1 = 0;
        int max1 = length1;
        int half = (length1+length2+1)/2;
        while(min1<=max1){
            int i = (min1+max1)/2;
            int j = half -  i;
            //当min1 = max1的时候，
            if(i < max1 && nums2[j-1]>nums1[i]){
                min1 = i+1;
            }else if(i > min1 && nums1[i-1]>nums2[j]){
                max1 = i-1;
            }else{
                int maxLeft;
                if(i ==0){
                    maxLeft = nums2[j-1];
                }else if(j==0){
                    maxLeft = nums1[i-1];
                }else{
                    maxLeft = Math.max(nums1[i-1],nums2[j-1]);
                }
                if ( (length1 + length2) % 2 == 1 ) { return maxLeft; }

                int maxRight;
                if(i == length1){
                    maxRight = nums2[j];
                }else if(j==length2){
                    maxRight = nums1[i];
                }else{
                    maxRight = Math.min(nums1[i],nums2[j]);
                }
                return (maxLeft+maxRight)/2.0;
            }
        }
        return 0.0;
    }

}
