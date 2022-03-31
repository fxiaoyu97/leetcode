package com.leetcode.z5961;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public static void main(String[] args) {
        ListNode d = new ListNode(3);
        ListNode c = new ListNode(2, d);
        ListNode b = new ListNode(2, c);
        ListNode a = new ListNode(4, b);
        System.out.println(new Solution().pairSum(a));
    }

    public int pairSum(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode first = head;
        ListNode second = head;
        int max = 0;
        while (second != null) {
            if (first != null) {
                stack.push(second.val);
                first = first.next.next;
                second = second.next;
            } else {
                max = Math.max(max, second.val + stack.pop());
                second = second.next;
            }
        }
        return max;
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
