package com.leetcode.z5962;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome(new String[]{"dd", "aa", "bb", "dd", "aa", "dd", "bb", "dd", "aa", "cc", "bb", "cc", "dd", "cc"}));
    }

    public int longestPalindrome(String[] words) {
        // 添加标记位，记录对应的字符串出现的次数
        int[][] one = new int[123][123];
        int n = words.length;
        // 字符串转标记数组，并且记录数量
        for (int i = 0; i < n; i++) {
            String str = words[i];
            char a = str.charAt(0);
            char b = str.charAt(1);
            // 记录相关位置的数量
            one[a][b] += 1;
        }
        // 最终的数量
        int ans = 0;
        // 记录是否存在aa类型的字符串，并且是奇数
        boolean flag = false;
        // 循环处理字符串
        for (int i = 0; i < n; i++) {
            String str = words[i];
            char a = str.charAt(0);
            char b = str.charAt(1);
            // 如果小于1表示反转的字符串不存在，或者已经计算过数量
            if (one[b][a] < 1)
                continue;
            // 如果不是aa型字符串，第一位字符和第二位字符不想等
            if (a != b) {
                // 数量是最少的那个字符串的数量，回文每次都是两个字符串，所以要乘上2
                ans += (Math.min(one[a][b], one[b][a]) << 1);
            } else if (a == b) {
                // 如果是aa型的字符串，则判断数量是奇数还是偶数
                int num = one[a][b];
                if (num % 2 == 1) {
                    // 当存在多个奇数的aa型字符串时，最后只有一个字符串能放到中间形成回文，如：aa,aa,aa,bb,bb,bb,cc,dd,dd组成的字符串为aabbccbbaa，aabbaabbaa
                    // 数量是奇数时，计算除以2的商，偶数的个数，可以形成回文
                    ans += ((num >> 1) << 1);
                    flag = true;
                } else {
                    // 偶数个的字符串，直接添加数量
                    ans += num;
                }
            }
            // 清除标记位，表示这个字符串已经计算过了
            one[a][b] = 0;
            one[b][a] = 0;
        }
        // 返回最终的数量，字符数量需要乘2
        return flag ? (1 + ans) * 2 : ans * 2;
    }
}
