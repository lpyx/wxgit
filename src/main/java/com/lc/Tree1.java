package com.lc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Tree1 {

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    public static void main(String[] args) {
        PriorityQueue a = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Node node1 = new Node(1);
        Node node2= new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node4.left = node2;
        node4.right = node5;
        //node2.left =node1;
        node2.right = node3;
        Node n = new Tree1().treeToDoublyList(node1);
        System.out.printf(n.val+"");
    }
    public Node treeToDoublyList(Node root) {
        return dList(root);
    }

    public Node dList(Node node){
        if(node == null){
            return null;
        }
        Node left = dList(node.left);
        Node right = dList(node.right); //右边的最左节点
        Node cLeft = left;
        Node cRight = right;
        while(cLeft != null && cLeft.right != null
        && cLeft.val < cLeft.right.val){
            cLeft = cLeft.right;
        }
        while(cRight != null && cRight.right != null
        &&cRight.val < cRight.right.val){
            cRight = cRight.right;
        }
        if(left != null){
            left.left = cRight;
            cLeft.right = node;
        }
        if(right != null){
            right.left = node;
            cRight.right = left;
        }
        node.left = cLeft;
        node.right = right;
        if(left != null){
            return left;
        }
        return node;
    }
}
