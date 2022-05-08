package com.leetcode.p231;

/**
 * @author calos
 * @version 1.0
 * @description: 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false
 * @date 2022/5/8 20:51
 */
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
