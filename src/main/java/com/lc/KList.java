package com.lc;
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
public class KList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(5);

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(4);

        ListNode listNode2 = new ListNode(2);
        listNode2.next = new ListNode(6);

        ListNode[] ls = new ListNode[]{listNode,listNode1,listNode2};
        ListNode result = new KList().mergeKLists(ls);
        System.out.printf("fff");
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null) return null;
        initD(lists.length);
        ListNode head =  new ListNode(-1);
        ListNode current = head;
        for(int i=0;i<lists.length;i++){
            if(lists[i] != null){
                push(lists[i]);
            }
        }
        if(currentSize <= 0){
            return null;
        }
        while(true){
            ListNode c= pop();
            if(c == null){
                break;
            }
            current.next = c;
            current = current.next;
            if(c.next!=null){
                push(c.next);
            }
        }
        return head.next;
    }

    ListNode[] dlist= null;
    int currentSize = 0;
    private void initD(int size){
        dlist = new ListNode[size];
        currentSize = 0;
    }

    private void push(ListNode listNode){
        dlist[currentSize] = listNode;
        int currentI = currentSize;


        //放的位置
        while(currentI > 0){
            //
            int parentI = (currentI -1)/2;
            ListNode parent = dlist[parentI];
            if(dlist[currentI].val < parent.val){
                ListNode temp = dlist[currentI];
                dlist[currentI] = dlist[parentI];
                dlist[parentI] = temp;
                currentI = parentI;

            }else{
                break;
            }

        }

        currentSize++;

    }

    private ListNode pop(){


        if(currentSize == 0){
            return null;
        }

        ListNode result = dlist[0];
        dlist[0] = dlist[currentSize-1];
        currentSize--;
        int currentI = 0;
        while(currentI < currentSize-1){
            int sonI = 2*currentI+1;
            if(sonI > currentSize){
                break;
            }
            if(sonI < currentSize-1){
                //
                if(dlist[sonI].val > dlist[sonI+1].val){
                    sonI = sonI+1;
                }
            }
            if(dlist[sonI].val > dlist[currentI].val){
                break;
            }
            ListNode temp = dlist[currentI];
            dlist[currentI] = dlist[sonI];
            dlist[sonI] = temp;
            currentI = sonI;

        }

        return result;
    }
}
