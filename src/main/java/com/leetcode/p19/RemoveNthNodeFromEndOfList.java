package com.leetcode.p19;

import java.util.List;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {

    }
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

class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 计算链表长度
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            ++length;
            temp = temp.next;
        }

        // 增加前置节点，防止删除第一个元素
        ListNode ans = new ListNode(0, head);
        temp = ans;
        // 计算出要删除节点在链表中的位置
        for (int i = 1; i < length - n + 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return ans.next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = head;

        while (n > 0) {
            first = first.next;
            --n;
        }

        if (first == null) {
            return head.next;
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }
}
