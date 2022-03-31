package com.leetcode.p139;

import java.util.List;

public class WordBreak {
}
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 标记数据，记录不能匹配的字符位，遇到相同位置时，直接返回
        boolean[] flag = new boolean[s.length()];
        return dfs(s, 0, wordDict, flag);
    }

    public boolean dfs(String s, int index, List<String> wordDict, boolean[] flag) {
        // 当前位置等于字符串长度时，字符串组合成功
        if (index == s.length()) {
            return true;
        }
        // 如果这个位置上的字符已经匹配过，且匹配失败，则直接返回结果
        if (flag[index]) {
            return false;
        }
        // 选择不同的单词，从index位置开始匹配字符串
        for (String word : wordDict) {
            // 当前位置赋值给临时变量，更新单词时需要从头开始匹配
            int k = index;
            for (int i = 0; i < word.length(); i++) {
                // 字符串长度结果时单词还没结束，或者字符不想等都是匹配失败
                if (k >= s.length() || word.charAt(i) != s.charAt(k)) {
                    break;
                }
                // 匹配成功，字符串要匹配字符位置+1
                k++;
            }
            // 当前单词完全匹配，并且后续字符匹配成功，表示从index位置开始到字符串结尾这段字符串匹配成功，返回true
            if (k - index == word.length() && dfs(s, k, wordDict, flag)) {
                return true;
            }
        }
        // 匹配失败，当前位置开始，任何单词组合都无法匹配，作标记，并且返回false
        flag[index] = true;
        return false;
    }
}