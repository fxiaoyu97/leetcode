package com.leetcode.z5194;

import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minMoves(19, 2));
    }

    public int minMoves(int target, int maxDoubles) {
        int ans = 0;
        while (target != 1) {
            if (target % 2 == 0 && maxDoubles > 0) {
                target = (target >> 1);
                maxDoubles--;
            } else if (maxDoubles == 0) {
                return ans + target - 1;
            } else {
                target -= 1;
            }
            ans++;
        }
        return ans;
    }
}
