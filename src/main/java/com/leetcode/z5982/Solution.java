package com.leetcode.z5982;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().mostPoints(new int[][]{{3, 2}, {4, 3}, {4, 4}, {2, 5}}));
    }

    public long mostPoints(int[][] questions) {
        int n = questions.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = questions[0][0];
        for (int i = 0; i < n; i++) {
            int skip = questions[i][1];
            for (int j = i + 1; j < n; j++) {
                dp[j][0] = Math.max(dp[i][0], dp[j][0]);
                dp[j][0] = Math.max(dp[i][1], dp[j][0]);
                dp[j][1] = Math.max(dp[i][0] + questions[j][0], dp[j][1]);
                if (j > i + skip) {
                    dp[j][1] = Math.max(dp[i][1] + questions[j][0], dp[j][1]);
                }
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}