package com.binary;

public class BinaryNode {
    public int val;
    public BinaryNode left;
    public BinaryNode right;

    public BinaryNode(BinaryNode left,BinaryNode right, int value){
        this.left = left;
        this.right = right;
        this.val = value;
    }

}
