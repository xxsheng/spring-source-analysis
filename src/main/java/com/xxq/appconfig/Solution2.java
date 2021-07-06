package com.xxq.appconfig;

import java.util.Objects;

public class Solution2 {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode node2 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode listNode = addTwoNumbers(node1, node2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode tempNode = listNode;
        int temp=0;
        while (!(l1 == null && l2 == null)) {
            int i = (Objects.isNull(l1) ? 0 : l1.val) + (Objects.isNull(l2) ? 0 :l2.val) + temp;

            if (i <10) {
                listNode.next = new ListNode(i);
                temp = 0;
            } else {
                int i1 = i % 10;
                listNode.next = new ListNode(i1);
                temp = i /10;
            }
            listNode = listNode.next;
            l1 = Objects.isNull(l1) ? null : l1.next;
            l2 = Objects.isNull(l2) ? null : l2.next;
        }
        if (temp > 0) {
            listNode.next = new ListNode(temp);
        }
        return tempNode.next;
    }

}


   class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

