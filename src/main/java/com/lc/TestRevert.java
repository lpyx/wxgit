package com.lc;

public class TestRevert {
    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode ls = new TestRevert().reverseKGroup(listNode, 3);
        System.out.printf("fff");
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        ListNode cHead = head;
        ListNode ctail = head;
        int i = 1;
        while(ctail!= null&& i<k){
            if(ctail.next == null){
                break;
            }
            ListNode next = ctail.next;
            ListNode temp = next.next;
            next.next = cHead;
            ctail.next = temp;
            cHead = next;

            i++;
            if(i==k){
                break;
            }
            //这里如果跳过去会有问题
            //ctail = ctail.next;


        }
        if(i < k){
            return revert1(cHead,k);
        }
        if(ctail != null)
            ctail.next = reverseKGroup(ctail.next,k);

        return cHead;
    }

    private ListNode revert1(ListNode head, int k){
        ListNode cHead = head;
        ListNode ctail = head;
        int i = 1;
        while(ctail!= null&& i<k){
            if(ctail.next == null){
                break;
            }
            ListNode next = ctail.next;
            ListNode temp = next.next;
            next.next = cHead;
            ctail.next = temp;
            cHead = next;

            i++;
            if(i==k){
                break;
            }
            //这里如果跳过去会有问题
            //ctail = ctail.next;


        }
        return cHead;
    }
}
