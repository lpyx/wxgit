package com.lc;

public class TowDRay {
    public static void main(String[] args) {
        int[][] aa= new int[1][2];
        aa[0][0] = -1;
        aa[0][1] = 3;
        boolean a = new TowDRay().findNumberIn2DArray(aa, 3);
        System.out.printf(a+"");
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //拆为4份
        //开始节点， end节点，中间节点
        //开始到中间， 中间
        if(matrix.length == 0){
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        return findNumberIn2DArray(matrix, target,0,n-1,0,m-1 );

    }
    private boolean findNumberIn2DArray(int[][] matrix, int target,
                                        int startN,int endN,int startM ,int endM){
        if(startN > endN){
            return false;
        }
        if(startM > endM){
            return false;
        }
        if(matrix[startN][startM] > target){
            return false;
        }
        if(matrix[endN][endM] < target ){
            return false;
        }
        int middleM = (startM+endM)/2;
        int middleN = (startN+endN)/2;
        if(startN == endN && startM == endM){
            return matrix[startN][startM] == target;
        }
        return findNumberIn2DArray(matrix,target,startN, middleN ,startM ,middleM)
                || findNumberIn2DArray(matrix,target,startN, middleN ,middleM+1 ,endM)
                || findNumberIn2DArray(matrix,target,middleN+1, endN ,startM ,middleM)
                || findNumberIn2DArray(matrix,target,middleN+1, endN ,middleM+1 ,endM) ;

    }
}
