package com.leetcode.p714;

public class SellStockWithTransactionFee {

}

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 不持有股票的利益
        int noHas = 0;
        // 持有股票的利益
        int has = -prices[0];
        for (int i = 1; i < n; i++) {
            has = Math.max(has, noHas - prices[i]);
            noHas = Math.max(has + prices[i] - fee, noHas);
        }
        return noHas;
    }
}
