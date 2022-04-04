package com.leetcode.p76;

import java.util.HashMap;
import java.util.Objects;

public class Solution {
    public String minWindow(String s, String t) {
        // 需要覆盖的字符串转化成哈希表
        HashMap<Character, Integer> needs = new HashMap<>();
        // 存放遍历s字符串时实际遇到的字符数量
        HashMap<Character, Integer> actual = new HashMap<>();
        // 转化目标字符串为哈希表
        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }
        char[] chars = s.toCharArray();
        // 匹配字符串左边的位置
        int left = 0;
        // 匹配字符串右边的位置
        int right = 0;
        // 已经覆盖目标字符串的字符数量
        int count = 0;
        // 记录答案
        String ans = "";
        // 符合要求的字符串最长不会超过的长度，用来做第一次匹配
        int minLen = s.length() + 1;

        // 基础查找的条件，右边没有越界
        while (right < s.length()) {
            // 滑动窗口右边要添加的字符，直到全部包含t字符串的字符，然后左边开始减少字符
            char c = chars[right++];
            // 如果是覆盖需要些字符，则在实际遇到的字符串数量需要+1
            Integer needNum = needs.get(c);
            if (needNum != null) {
                int actualNum = actual.getOrDefault(c, 0);
                // 如果实际遇到的字符数量少于需要的字符数量，则表示覆盖的字符数量又增加了一位
                // 如果实际遇到的字符数量已经大于等于需要的数量，则覆盖的字符数量不会再增加了
                if (actualNum < needNum) {
                    ++count;
                }
                // 实际遇到的字符数量需要+1
                actual.put(c, actualNum + 1);
            }
            // 左边开始减少字符，缩短目标串的长度
            while (count == t.length()) {
                // 如果此时的目标串小于答案，则更新答案和最小目标串的长度
                if (right - left < minLen) {
                    minLen = right - left;
                    ans = new String(chars, left, right - left);
                }
                char temp = chars[left];
                // 如果左边的字符被实际遇到的字符统计了，则需要-1
                if (actual.containsKey(temp)) {
                    // 如果实际统计的字符数量和需要的字符数量恰好相等，在左边减少一个字符的时候，覆盖t串的长度也会-1
                    if (Objects.equals(actual.get(temp), needs.get(temp))) {
                        --count;
                    }
                    actual.put(temp, actual.get(temp) - 1);
                }
                ++left;
            }
        }
        return ans;
    }
}
