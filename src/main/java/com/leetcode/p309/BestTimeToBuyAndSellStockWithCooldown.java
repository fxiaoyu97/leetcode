package com.leetcode.p309;

/**
 * @author calos
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 3, 0, 2}));
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // base
        // 没有股票，当天也没有卖出
        int noSale = 0;
        // 持有股票
        int hasStock = -prices[0];
        // 没有股票，当前卖出的
        int saleStock = 0;

        for (int i = 1; i < n; i++) {
            // 当天没有股票，昨天没有或者昨天卖掉了
            int newNoSale = Math.max(noSale,saleStock);
            // 当天卖出，昨天一定有股票
            saleStock = hasStock + prices[i];
            // 当天持有股票，昨天持有或者今天买入的
            hasStock = Math.max(hasStock,noSale - prices[i]);
            noSale = newNoSale;
        }
        return Math.max(noSale, saleStock);
    }
}

class Solution1 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }
}

