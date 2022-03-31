package com.leetcode.p504;

class Solution {
    public String convertToBase7(int num) {
        boolean flag = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        return (flag ? "-" : "") + sb.reverse();
    }
}
