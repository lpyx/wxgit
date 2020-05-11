package com.lc;

public class S1 {
    public static void main(String[] args) {
        boolean a = new S1().verifyPostorder(
                new int[]{
                        5,10,6,9}


        );
        System.out.printf(a+"");
    }

    public boolean verifyPostorder(int[] postorder) {
        //树，关键是找根节点
        //后序的话，最后一个是根节点
        return verify(postorder,0, postorder.length-1);
        //分为两半树

    }

    public boolean verify(int[] postorder,int begin, int end){
        if(begin <= end){
            return true;
        }
        int root = postorder[end];
        int m = -1;
        for(int i=begin;i<end;i++){
            if(postorder[i] > root){
                m = i;
                break;
            }
        }
        if(m > -1){
            for(int i=m+1;i<end;i++){
                if(postorder[i] <= root){
                    return false;
                }
            }
        }
        if(m == -1){
            return verify(postorder, begin, end-1);
        }else{
            return verify(postorder,begin , m-1) && verify(postorder,m,end-1);
        }
    }
}
