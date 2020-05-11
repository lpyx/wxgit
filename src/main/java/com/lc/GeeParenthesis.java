package com.lc;

import java.util.ArrayList;
import java.util.List;

public class GeeParenthesis {
    public static void main(String[] args) {
        new GeeParenthesis().generateParenthesis(3);
    }
    public List<String> generateParenthesis(int n) {
        if(n <= 0) return new ArrayList();
        List<String> result = new ArrayList<String>();
        genePath(result, "",0,0,n);
        return result;
    }

    private void genePath(List<String> result,String before,int leftCount,int rightCount,int target){
        if(leftCount > target){
            return;
        }
        if(leftCount == target && rightCount == target){
            result.add(before);
            return;
        }
        if(leftCount == rightCount){
            genePath(result,before+"(",leftCount+1,rightCount,target);
        }else {
            if(leftCount> rightCount){
                genePath(result,before+"(",leftCount+1,rightCount,target);
                genePath(result,before+")",leftCount,rightCount+1,target);
            }
        }
    }
}
