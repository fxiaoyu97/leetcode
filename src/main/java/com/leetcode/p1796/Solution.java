/*
 * Copyright (c) fxiaoyu97 my copyright message. 2022-2022. All rights reserved.
 */

package com.leetcode.p1796;

/**
 * @author calos
 * @version 1.0
 * @description: 字符串中第二大的数字
 * @date 2022/12/3 22:04
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().secondHighest("abc1111"));
    }

    public int secondHighest1(String s) {
        // 从题目可以看到，涉及的数字只有0-9，所以可以使用一个数组存放结果，如果字符串中出现了这个数字，就在数组中标为1
        // 最后的时候，从大往小，遍历结果数组，遇到第二个存放1的位置，直接返回坐标即可
        int[] result = new int[10];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            // 遍历字符串，过滤掉字母，数字直接作为数组下标
            if (c >= '0' && c <= '9') {
                result[c - '0'] = 1;
            }
        }
        // 需要一个标记，来区分是第一大还是第二大的数字
        boolean flag = false;
        // 遍历记录结果的数字
        for (int i = 9; i >= 0; i--) {
            // 如果存放的数字为1，说明字符串中有这个数字
            if (result[i] == 1) {
                // 如果标记为true，表示这是第二次遇到字符串中出现的数字，直接返回
                if (flag) {
                    return i;
                } else {
                    //第一次遇到字符串中出现的数字，此时是最大的数字
                    flag = true;
                }
            }
        }
        return -1;
    }

    public int secondHighest(String s) {
        // max记录最大的数字，min记录第二大的数字
        int max = -1;
        int min = -1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            // 遍历字符串字符，如果是字母，直接跳过
            // 如果当前遇到的数字和最大的数字相等，直接跳过
            if (c > '9' || c - '0' == max) {
                continue;
            }
            // 字符串相减得到实际数字的值
            int temp = c - '0';
            // 如果当前遇到的数字比最大数还要大，则更新最大数和第二大数的值
            if (temp > max) {
                min = max;
                max = temp;
            } else if (temp > min) {
                // 如果当前遇到的数字比最大数小，比第二大数大，更新第二大数的值
                min = temp;
            }
        }
        return min;
    }
}
