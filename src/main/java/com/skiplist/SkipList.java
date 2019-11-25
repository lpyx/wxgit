package com.skiplist;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表， 多级链表，
 * head
 * node ,(每个node指向多个下级）
 */
public class SkipList {
    SkipNode header = new SkipNode();

    //每个级别的链表数据
    public void add(String data){
        //先查看是否存在

        //如果不存在，判断要添加几级索引，

        //将该数据链接到对应的级别中（链接时也是先根据高级别再判断低级别数据）
    }

    public void remove(String data){

    }

    public void contains(String data){

    }
}

class SkipNode{
    String data = null;
    List<SkipNode> nextNodeList = new ArrayList<SkipNode>();
}
