## 一、题目描述

给你一个字符串 `s` 、一个字符串 `t` 。返回 `s` 中涵盖 `t` 所有字符的最小子串。如果 `s` 中不存在涵盖 `t` 所有字符的子串，则返回空字符串 `""` 。

**注意：**

- 对于 `t` 中重复字符，我们寻找的子字符串中该字符数量必须不少于 `t` 中该字符数量。
- 如果 `s` 中存在这样的子串，我们保证它是唯一的答案

**示例 1：**

```
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
```

**示例 2：**

```
输入：s = "a", t = "a"
输出："a"
```

**示例 3：**

```
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
```

**提示：**

- `1 <= s.length, t.length <= 105`
- `s 和 t 由英文字母组成`

## 二、解题思路

看到这个题，我们会发现这题虽然被Leetcode标记为困难，但是了解套路的话就会感觉不难，所谓的套路，就是说这一个标准的滑动窗口题目。

首先我们对题目转化一下，就是要在字符串`s`中寻找一个连续的字符串，它包含`t`中所有的字符，并且相应字符的数量不能比`t`中的字符数量少，那么现在这个题就变成了数量相关的题目。

然后要注意另外一点，就是`t`中可能存在重复的字符。这时候就会出现一个问题，在滑动窗口的过程中，当你要移除一个存在重复的字符时，会不会涉及到移除字符的位置，在做这个题的时候差点被绕进去了。

重复的也好，不重复的也好，在不考虑被覆盖字符串的字符顺序的情况下，这时候只要把字符数量对上了，那么就符合题目要求了。

接着我们使用两个集合分别存储完全覆盖字符串`t`需要的字符数量，和遍历字符串`s`时遇到的有效字符数量，这里所谓的有效字符就是指在`t`中出现的字符。

使用左右指针表示要查找的目标字符串的左边和右边，它们同时从字符串`s`的左边往右边移动。右指针移动，目标字符串不断增长，直到目标字符串可以覆盖字符串`t`。讲诶等着移动左指针删除字符，目标字符串不断变短，直到再删除一个字符就不能满足题目要求，此时就是当前满足要求的最短字符串。

不满足要求以后，接着移动右指针，重复上述操作，可能会发现第二个满足要求的目标字符串，然后比较这些目标字符串的长度，获取一个最短的。

**示例代码**

```java
class Solution {
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
            // 如果是覆盖需要的字符，则在实际遇到的字符串数量需要+1
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
```

代码的运行效率并不是很高，考虑到使用哈希表的过程中，可能是哈希计算和比较等操作花费时间比较多，所以改用数组计数，效率有明显提升。

因为字符串全部为英文字母，所以我们可以直接固定数组大小为123。

**示例代码**

```java
class Solution {
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
            // 如果是覆盖需要的字符，则在实际遇到的字符串数量需要+1
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
```

## 三、总结

最后经过不断的尝试，把时间从17ms变成了3ms，感觉还挺有点意思。滑动窗口没有什么好说的，就是理解它的原理，按照原理来就可以。

这次的优化做法，主要就是减少循环内的执行语句。