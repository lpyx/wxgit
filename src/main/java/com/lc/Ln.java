package com.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Ln {

   static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
       TreeNode root = new TreeNode(1);
       root.left = new TreeNode(2);
       root.right = new TreeNode(3);
       root.left.right = new TreeNode(4);

       TreeNode tn = new Ln().deserialize("5,2,3,null,null,2,4,3,1");
        System.out.printf("fff");

    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
       if(root==null){
           return "null";
       }
        //用双队列，如果发现有一次，添加的数据全为null,则不再处理
       // start.offer(root);
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        ss(list,0,0);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i< list.size();i++){
            if(i> 0){
                sb.append(",");
            }
            if(list.get(i) ==null){
                sb.append("null");
            }else{
                sb.append(list.get(i).val+"");
            }
           // sb.append(list);
        }
        return sb.toString();

    }
    private void ss(List<TreeNode> result,int start,int end){
       for(int i=start;i<=end;i++){
           TreeNode tn = result.get(i);
           if(tn == null){

           }else{
               result.add(tn.left);
               result.add(tn.right);
           }
       }
       if(result.size() == end+1){
           return;
       }
       ss(result,end+1,result.size()-1);
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       data = data.substring(0,data.length());
        //化成数组，然后分层解析
        if(data == null || data.length() == 0){
            return null;
        }
        String[] sList = data.split(",");
        if(sList[0].equals("null")){
            return null;
        }
        int rootValue = Integer.parseInt(sList[0]);
        TreeNode root = new TreeNode(rootValue);
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        des(sList,list,1);

        return root;
    }
    //假设跟为1层
    private void des(String[] sList,List<TreeNode> up,int start){
        //需要处理的数字
        if(up.size() == 0){
            return;
        }
        int size = up.size()*2;
        List<TreeNode> listNode = new ArrayList();
        for(int i=0;i<size;i++){
            if(start+i >= sList.length){
                break;
            }
            String value = sList[start+i];
            TreeNode tn = null;
            if(!value.equals("null")){
                tn = new TreeNode(Integer.parseInt(value));
                listNode.add(tn);
                int lIndex = i/2;
                if(i%2==0){
                    up.get(lIndex).left = tn;
                }else{
                    up.get(lIndex).right = tn;
                }
            }

        }


        des(sList,listNode,start+size);
    }
}
