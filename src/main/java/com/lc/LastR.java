import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LastR {

    public static void main(String[] args) {
        int a = 3;
        double[] s = new double[3];
        LastR l = new LastR();
        List<List<Integer>> list = new ArrayList();
        list.toArray((ArrayList[])new ArrayList().toArray());
        int[] nums = new int[]{7,5,6,4};
        int b = l.reversePairs(nums);
        System.out.printf(b+"");
    }
    public int reversePairs(int[] nums) {
        int length = nums.length;
        int count = 0;
        for(int i=1;i<length;i++){
            count += revert(nums,0,i-1,i);
        }
        return count;
    }

    public int revert(int[] nums,int start, int end,int insertJ){
        int middle = (start+end)/2;
        int count = 0;
        while(start <= end){
            middle = (start+end)/2;
            if(nums[middle] > nums[insertJ]){
                //
                end = middle - 1;
            }else{
                start = middle+1;
            }

        }
        if(nums[insertJ] > nums[middle]){
            //插入middle这里
            int insertValue = nums[insertJ];
            for(int i=insertJ;i>=middle+2;i--){
                nums[i] = nums[i-1];
                count++;
            }
            nums[middle+1] = insertValue;
        }else{
            int insertValue = nums[insertJ];
            for(int i=insertJ;i>=middle+1;i--){
                nums[i] = nums[i-1];
                count++;
            }
            nums[middle] = insertValue;
        }
        return count;
    }
}