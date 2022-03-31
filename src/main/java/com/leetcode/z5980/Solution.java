package com.leetcode.z5980;

class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        String[] ans = new String[n % k != 0 ? n / k + 1 : n / k];
        char[] chars = s.toCharArray();
        char[] tempChars = new char[k];
        int j = 0;
        int m = 0;
        for (int i = 0; i < n; i++) {
            tempChars[j++] = chars[i];
            if (j == k) {
                ans[m++] = String.valueOf(tempChars);
                tempChars = new char[k];
                j = 0;
            }
        }
        if (j != 0) {
            for (int i = j; i < k; i++) {
                tempChars[i] = fill;
            }
            ans[m] = String.copyValueOf(tempChars);
        }
        return ans;
    }
}
