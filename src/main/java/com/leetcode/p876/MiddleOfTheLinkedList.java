package com.leetcode.p876;


public class MiddleOfTheLinkedList {
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode bigRabbit = head;
        ListNode smallRabbit = head;

        while (bigRabbit != null && bigRabbit.next != null) {
            smallRabbit = smallRabbit.next;
            bigRabbit = bigRabbit.next.next;
        }
        return smallRabbit;
    }
}