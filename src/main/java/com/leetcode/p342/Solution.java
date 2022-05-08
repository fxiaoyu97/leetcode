/*
 * Copyright (c) fxiaoyu97 my copyright message. 2022-2022. All rights reserved.
 */

package com.leetcode.p342;

/**
 * @author calos
 * @version 1.0
 * @description: 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * @date 2022/5/8 21:01
 */
public class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }
}
