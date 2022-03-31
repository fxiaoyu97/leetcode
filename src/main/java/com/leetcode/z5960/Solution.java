package com.leetcode.z5960;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().capitalizeTitle("capiTalIze tHe titLe"));
    }
    public String capitalizeTitle(String title) {
        int n = title.length();
        char[] chars = title.toCharArray();
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == ' ') {
                if (queue.size() > 2) {
                    sb.append((char) (queue.poll() - 32));
                }
                while (!queue.isEmpty()) {
                    sb.append(queue.poll());
                }
                sb.append(" ");
                queue = new ArrayDeque<>();
            } else {
                if (c < 97) {
                    c = (char) (c + 32);
                }
                queue.offer(c);
            }

        }
        if (queue.size() > 2) {
            sb.append((char) (queue.poll() - 32));
        }
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }
}
