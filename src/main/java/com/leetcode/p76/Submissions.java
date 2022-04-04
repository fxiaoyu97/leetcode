package com.leetcode.p76;


/**
 * @author calos
 */
public class Submissions {
    public String minWindow(String s, String t) {
        // 使用数组统计字符串t完全覆盖需要的字符数量有什么需要多少
        int[] needArr = new int[123];
        // 统计遍历字符串s时遇到的有效字符数量
        int[] actualArr = new int[123];
        for (int i = 0; i < t.length(); i++) {
            ++needArr[t.charAt(i)];
        }
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
        int sLen = s.length();

        // 基础查找的条件，右边没有越界
        while (right < sLen) {
            // 滑动窗口右边要添加的字符，直到全部包含t字符串的字符，然后左边开始减少字符
            char c = s.charAt(right);
            ++right;
            // 如果实际遇到的字符数量少于需要的字符数量，则表示覆盖的字符数量又增加了一位
            // 如果实际遇到的字符数量已经大于等于需要的数量，则覆盖的字符数量不会再增加了
            if (needArr[c] != 0 && actualArr[c] < needArr[c]) {
                ++count;
            }
            // 实际遇到的字符数量需要+1
            ++actualArr[c];

            // 左边开始减少字符，缩短目标串的长度
            while (count == t.length()) {
                // 如果此时的目标串小于答案，则更新答案和最小目标串的长度
                if (right - left < minLen) {
                    minLen = right - left;
                    ans = s.substring(left, right);
                }
                char temp = s.charAt(left);
                // 如果左边的字符被实际遇到的字符统计了，则需要-1
                // 如果实际统计的字符数量和需要的字符数量恰好相等，在左边减少一个字符的时候，覆盖t串的长度也会-1
                if (actualArr[temp] == needArr[temp]) {
                    --count;
                }
                --actualArr[temp];
                ++left;
            }
        }
        return ans;
    }

    public String minWindow1(String s, String t) {
        // 使用数组统计字符串t完全覆盖需要的字符数量有什么需要多少
        int[] needArr = new int[123];
        // 统计遍历字符串s时遇到的有效字符数量
        int[] actualArr = new int[123];
        for (int i = 0; i < t.length(); i++) {
            ++needArr[t.charAt(i)];
        }
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
        int sLen = s.length();

        // 基础查找的条件，右边没有越界
        while (right < sLen) {
            // 滑动窗口右边要添加的字符，直到全部包含t字符串的字符，然后左边开始减少字符
            char c = s.charAt(right);
            ++right;
            // 如果是覆盖需要些字符，则在实际遇到的字符串数量需要+1
            if (needArr[c] != 0) {
                int actualNum = actualArr[c];
                // 如果实际遇到的字符数量少于需要的字符数量，则表示覆盖的字符数量又增加了一位
                // 如果实际遇到的字符数量已经大于等于需要的数量，则覆盖的字符数量不会再增加了
                if (actualNum < needArr[c]) {
                    ++count;
                }
                // 实际遇到的字符数量需要+1
                ++actualArr[c];
            }
            // 左边开始减少字符，缩短目标串的长度
            while (count == t.length()) {
                // 如果此时的目标串小于答案，则更新答案和最小目标串的长度
                if (right - left < minLen) {
                    minLen = right - left;
                    ans = s.substring(left, right);
                }
                char temp = s.charAt(left);
                // 如果左边的字符被实际遇到的字符统计了，则需要-1
                if (actualArr[temp] != 0) {
                    // 如果实际统计的字符数量和需要的字符数量恰好相等，在左边减少一个字符的时候，覆盖t串的长度也会-1
                    if (actualArr[temp] == needArr[temp]) {
                        --count;
                    }
                    --actualArr[temp];
                }
                ++left;
            }
        }
        return ans;
    }
}
