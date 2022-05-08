## 一、题目描述

给你一个下标从 **0** 开始的正整数数组 `candiesCount` ，其中 `candiesCount[i]` 表示你拥有的第 `i` 类糖果的数目。同时给你一个二维数组 `queries` ，其中 `queries[i] = [favoriteTypei, favoriteDayi, dailyCapi]` 。

你按照如下规则进行一场游戏：

- 你从第 **0** 天开始吃糖果。
- 你在吃完 **所有** 第 `i - 1` 类糖果之前，**不能** 吃任何一颗第 `i` 类糖果。
- 在吃完所有糖果之前，你必须每天 **至少** 吃 **一颗** 糖果。

请你构建一个布尔型数组 `answer` ，用以给出 `queries` 中每一项的对应答案。此数组满足：

- `answer.length == queries.length` 。`answer[i]` 是 `queries[i]` 的答案。
- `answer[i]` 为 `true` 的条件是：在每天吃 **不超过** `dailyCapi` 颗糖果的前提下，你可以在第 `favoriteDayi` 天吃到第 `favoriteTypei` 类糖果；否则 `answer[i]` 为 `false` 。

注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。

请你返回得到的数组 `answer` 。

**示例 1：**

```
输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
输出：[true,false,true]
提示：
1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
```

**示例 2：**

```
输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
输出：[false,true,true,false,false]
```

**提示：**

-   `1 <= candiesCount.length <= 10^5`
-   `1 <= candiesCount[i] <= 10^5`
-   `1 <= queries.length <= 10^5`
-   `queries[i].length == 3`
-   `0 <= favoriteTypei < candiesCount.length`
-   `0 <= favoriteDayi <= 10^9`
-   `1 <= dailyCapi <= 10^9`

> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day

## 二、解题思路

第一遍读题的时候，居然理解错，我以为这糖的类型可以挑着吃的……

这个题目翻译成人话，就是从`0`天`0`类型开始吃糖，每天吃糖的的数量 `dayNum` 有一个范围变化，`1 <= dayNum <= dailyCapi`。

那么当在第` favoriteDayi` 天时，这一天的吃的糖是在一个范围内的，我们把到这一天之前的需要吃掉的最少糖数量记做`min`，因为每天至少吃一个，所以`min = favoriteDayi`。

我们把第` favoriteDayi` 天能吃掉的最多糖数量记做`max`，因为每天有一个吃糖数量的限制，所以`max = (favoriteDayi + 1)*dailyCapi`

此时我们已经知道第`favoriteDayi`天需要糖的范围，那么接下来我们需要判断两件事情：

1. 前`favoriteTypei-1`类型的糖果数量之和不要超过最大值`max`，否则尽管孩子尽力吃，天天吃，在当天也吃不到喜欢的类型
2. 前`favoriteTypei`类型的糖果数量之和不要小于最小值`min`，否则孩子尽管省吃俭用的吃，还没撑到当天就把喜欢类型的糖吃完了
3. 但是有一种特殊情况，就是孩子喜欢在某天吃`0`类型的糖，那么此时只要判断 `0` 类型的糖果能大于最低需要值就可以了

既然思路确定了，那么就开始写代码了

```java
public boolean[] canEat(int[] candiesCount, int[][] queries) {
  // 记录结果
  boolean[] result = new boolean[queries.length];
  for (int i = 1; i < candiesCount.length; ++i) {
    // 计算前favoriteTypei类型糖果的数量之和
    candiesCount[i] += candiesCount[i - 1];
  }
  for (int i = 0; i < queries.length; ++i) {
    int[] a = queries[i]; // 需要判断的数据集合
    int min = a[1]; // 最小值min
    int max = a[2] * (a[1]+1); // 最大值max
    int t = a[0]; // 糖果类型
    result[i] = t > 0 ? (candiesCount[t - 1] < max && candiesCount[t] > min) : (candiesCount[t] > min);
  }
  return result;
}
```

是不是感觉这代码写的贼溜，多么无懈可击的代码，但是它出错了，小编都震惊的在厕所里哭晕了，你知道错哪了吗？

---

这里有一个很大问题，那就是数据溢出，最大值`max`，或者前`favoriteTypei`类型糖果的数量之和的值都可能超过 `int` 所能表达的最大范围，这真的是一个坑。

**修改后的代码**

```java
public boolean[] canEat(int[] candiesCount, int[][] queries) {
  boolean[] result = new boolean[queries.length];
  long[] sum = new long[candiesCount.length];
  sum[0] = candiesCount[0];
  for (int i = 1; i < candiesCount.length; ++i) {
    sum[i] = sum[i - 1] + candiesCount[i];
  }

  for (int i = 0; i < queries.length; ++i) {
    int[] a = queries[i];
    long min = a[1];
    long max = a[2] * (long)(a[1] + 1);
    int t = a[0];
    result[i] = t > 0 ? (sum[t - 1] < max && sum[t] > min) : (sum[t] > min);
  }
  return result;
}
```

**复杂度分析**

时间复杂度：`O(n+q)`，其中 n 和 q 分别是数组 candiesCount 和queries 的长度。`O(n) `是计算前缀和，`O(q)`是计算所有询问的结果。

空间复杂度：`O(n)`，即为存储前缀和数组`sum`需要的空间。注意返回值不计入空间复杂度。

## 三、总结

在这个题目里面有两个坑，还都让我踩了一遍。

第一、这个天数和类型的开始值是`0`

第二、数据溢出，这个是真的没有考虑数据取值范围的问题。下次需要多加注意。

这种解法和leetcode官方的解法，在思想上是大体一致的，细节上略有不同，有兴趣的可以瞅瞅Leetcode的官解方法。