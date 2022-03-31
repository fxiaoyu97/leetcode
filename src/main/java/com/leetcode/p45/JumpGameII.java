package com.leetcode.p45;

public class JumpGameII {
    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{1,4}));
    }
}
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n];
        // base
        for (int i = 1; i < n; i++) {
            dp[i] = 10000;
        }
        // status
        for (int i = 0; i < n; i++) {
            for(int j = i + 1; j <= i + nums[i]; j++) {
                if (j >= n) {
                    break;
                }
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }
}
