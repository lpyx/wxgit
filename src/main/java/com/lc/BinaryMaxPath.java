package com.lc;

import com.binary.BinaryNode;
import com.binary.BinaryTree;

import java.util.Arrays;

/**
 * 如果根节点时空，那么用0表示
 * 否则用其他的表示
 */
public class BinaryMaxPath {
    public static void main(String[] args) {
    }

    int allMaxValue = 0;

    public int maxPathSum(BinaryNode root) {
        if(root == null){
            allMaxValue = 0;
            return 0;
        }else{
            allMaxValue = root.val;
        }
        maxPath(root);
        return allMaxValue;
    }

    public int maxPath(BinaryNode bn){
        if(bn == null) return 0;


        int current = bn.val;
        int maxValue; //获取最大的值
        int currentMaxValue;  //包含该节点的做大的值

        if(bn.right == null && bn.left == null){
            maxValue = bn.val;
            currentMaxValue = bn.val;
            allMaxValue = Math.max(allMaxValue, maxValue);
            return currentMaxValue;
        }

        if(bn.left == null){
            int maxRight = maxPath(bn.right);
            currentMaxValue = Math.max(current+maxRight,current);
            maxValue = Math.max(currentMaxValue,maxRight);
            allMaxValue = Math.max(allMaxValue, maxValue);
            return currentMaxValue;
        }

        if(bn.right == null){
            int maxLeft = maxPath(bn.left);
            currentMaxValue = Math.max(current+maxLeft,current);
            maxValue = Math.max(currentMaxValue,maxLeft);
            allMaxValue = Math.max(allMaxValue, maxValue);
            return currentMaxValue;
        }

        int maxLeft = maxPath(bn.left);
        int maxRight = maxPath(bn.right);
        //作为路径的一部分，current只能加左节点或者加右节点
        //作为最大值，可以加所有的
        if(current > 0){
            maxValue = current;
            if(maxLeft > 0){
                maxValue = maxLeft+maxValue;
            }
            if(maxRight > 0){
                maxValue = maxRight+maxValue;
            }
        }else{
            //左边打，右边打，左边+中间+右边大
            maxValue = Math.max(Math.max(maxLeft,maxRight),maxLeft+maxRight+current);
        }
        currentMaxValue = Math.max(Math.max(current+maxLeft, current+maxRight),current);
        allMaxValue = Math.max(allMaxValue, maxValue);
        return currentMaxValue;
    }

    
}
