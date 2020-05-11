package com.lc;

class TreeNode{
    TreeNode left;
    TreeNode right;
    int val  = 0;
    public TreeNode(int val){
        this.val = val;
    }
}
public class RebuildTreeNode {
    public static void main(String[] args) {
       // [3,9,20,15,7]
//[9,3,15,20,7]

        int[] a = new int[]{1,2,3};
        int[] b=  new int[]{2,3,1};
        TreeNode tn = new RebuildTreeNode().buildTree(a,b);
        System.out.printf("fff");
    }

    public TreeNode buildTree(int[] inorder, int[] preorder) {
        //找到root节点
        return  buildTree(preorder,inorder,0, preorder.length-1, 0, inorder.length-1);

    }

    public TreeNode buildTree(int[] preorder, int[] inorder,int beginP, int endP, int beginI, int endI){
        if(beginI > endI){
            return null;
        }
        //
        if(beginI == endI){
            return new TreeNode(inorder[beginI]);
        }
        int root = inorder[beginI];
        int newBeginP = beginP;
        for(;newBeginP<=endP;newBeginP++){
            if(preorder[newBeginP] == inorder[beginI]){
                break;
            }
        }
        //newBegin是新的begin
        TreeNode rootT = new TreeNode(inorder[beginI]);

        rootT.left = buildTree(preorder, inorder, beginP, newBeginP-1,beginI+1, beginI+newBeginP-beginP);
        rootT.right = buildTree(preorder, inorder, newBeginP+1,endP ,beginI+newBeginP-beginP+1, endI);
        return rootT;
    }

    //记录记录的结果
    //全集
    //目标集合
    //已经存储的值
    //当前运行到哪个点
    public void backtrack(int[] resultList,int[] all){

    }
}
