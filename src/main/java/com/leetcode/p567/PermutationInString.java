package com.leetcode.p567;

import java.util.Arrays;

public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("ab", "eidboaoo"));
    }

}

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s2.length();
        int[] needs = new int[123];
        int[] actual = new int[123];
        for (int i = 0; i < s1.length(); i++) {
            ++needs[s1.charAt(i)];
        }

        int count = 0;
        int right = 0;
        while (right < n) {
            char c = s2.charAt(right);
            ++right;
            if (needs[c] <= 0) {
                actual = new int[123];
                count = 0;
                continue;
            }
// 套娃操作，里面也需要判断当前字符串和是否相等
//            while (count < s1.length()) {
//                ++actual[s2.charAt(right)];
//                ++right;
//                ++count;
//            }

            ++actual[c];
            --actual[s2.charAt(right - 1 - s1.length())];

            if (Arrays.equals(needs, actual)) {
                return true;
            }
        }
        return false;
    }
}