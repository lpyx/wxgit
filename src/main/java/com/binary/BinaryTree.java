package com.binary;

import java.util.Random;

public class BinaryTree {
    public BinaryNode root = null;


    public void addRandomNode(int value){
        root = addRandom(value, root);
    }

    private BinaryNode addRandom(int value,BinaryNode node){
        if(node == null){
            node = new BinaryNode(null , null, value);
            return node;
        }
        int random = new Random().nextInt(100);
        if(random >= 50){
            //右边
            node.right = addRandom(value, node.right);
        }else{
            //左边
            node.left = addRandom(value ,node.left);
        }
        return node;
    }
}
