package com.skiplist;

import java.util.Random;

public class MySkipList {

    public static void main(String[] args) {
        MySkipList skipList = new MySkipList();
        for(int i=0;i<1000;i++){
            skipList.addNode(i);
        }

        for(int i=0;i<10;i++){
            skipList.removeNode(i*10);
        }

        MySkipNode sn = skipList.findNode(60);
        System.out.printf(sn.score+"");

    }

//    static class MySkipLevel{
//        MySkipNode skipNode;
//        //int span;
//    }
    static class MySkipNode{
        double score; //分数
        Object obj; //对象
        MySkipNode[] mySkipNode;
       // MySkipNode back;
    }

    //int totalLevel  = 31;
    MySkipNode head = null;
   // MySkipNode tail = null;
    int maxLevel = 0;//记录当前最大的level,去除头结点

    MySkipList(){
        head = new MySkipNode();
        head.score = 0;
        head.obj = null;
        head.mySkipNode = new MySkipNode[32];
       // head.levelArray = new MySkipLevel[32];

        //tail = new MySkipNode();
        head.score = 0;
        head.obj = null;
       // tail.levelArray = new MySkipLevel[32];

        for(int i=0;i<32;i++){

         //  MySkipLevel level = new MySkipLevel();
          // head.levelArray[i] = level;
          // level.span = 0;
        //   level.skipNode = tail;
        }
       // tail.back = head;
    }

    public void addNode(int val){
        int level = getLevel();
        if(level >= maxLevel){
            maxLevel = level;
        }
        MySkipNode before = head;
        MySkipNode current = null;
        MySkipNode addNode = new MySkipNode();
        addNode.score = val;
        addNode.mySkipNode = new MySkipNode[level+1];
        for(int i=level;i>=0;i--){
            current = before.mySkipNode[i];
            while(current != null && current.score < val){
                //current = before;
                before = current;
                current = before.mySkipNode[i];
            }
            //找到before
            before.mySkipNode[i] =addNode;
            addNode.mySkipNode[i]= current;
        }
    }

    public MySkipNode findNode(int val){
        MySkipNode before = head;
        MySkipNode current = null;
        for(int i=maxLevel;i>=0;i--){
            current = before.mySkipNode[i];
            if(current.score == val){
                return current;
            }
            if(current.score < val){
                continue;
            }
            if(current.score > val){
                break;
            }
        }
        return null;
    }

    public void removeNode(int val){
        MySkipNode before = head;
        MySkipNode current = null;
        for(int i=maxLevel;i>=0;i--){
            current = before.mySkipNode[i];
            if(current.score == val){
                //找到该节点
                before.mySkipNode[i] = current.mySkipNode[i];
            }
            if(current.score < val){
                continue;
            }
            if(current.score > val){
                break;
            }
        }
       // return null;
    }



    private int getLevel(){
        return new Random().nextInt(32);
    }


}
