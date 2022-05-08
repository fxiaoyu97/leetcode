## 一、题目描述

给你一个整数数组 `nums` 和一个整数 `k` ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

- 子数组大小 **至少为 2** ，且
- 子数组元素总和为 `k` 的倍数。

如果存在，返回 `true` ；否则，返回 `false` 。

如果存在一个整数 `n` ，令整数 `x` 符合 `x = n * k` ，则称 `x` 是 `k` 的一个倍数。`0` 始终视为 `k` 的一个倍数。

**示例 1：**

```
输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
```

**示例 2：**

```
输入：nums = [23,2,6,4,7], k = 6
输出：true
解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
```

**示例 3：**

```
输入：nums = [23,2,6,4,7], k = 13
输出：false
```

**提示：**

-   `1 <= nums.length <= 10^5`
-   `0 <= nums[i] <= 10^9`
-   `0 <= sum(nums[i]) <= 2^31 - 1`
-   `1 <= k <= 2^31 - 1`

## 二、解题思路

使用最简单最暴力的方法，我们可以求取所有子数组的和，然后判断子数组的和是不是 k 的倍数。

这个时间复杂度还是很恐怖的。

那么是不是还有更优雅的方式呢？

1. 设`sum[i]`表示从 0 到 i 这段数组的数字之和，那么我们要求的 `i+1` 到 `j` 这个子数组的和，就可以利用差值的方式。也就是 `(sum[j] - sum[i]) % k = 0`，并且`j - i >= 2`。

2. 同余定理：**a - b = c , 如果c % k = 0， 则 a % k = b % k**
3. 根据同余定理我们看出，判断子数组`[i+1, j]`能不能被 k 整除，就可以转化为判断`sum[j]`和`sum[i]`的余数相等。
4. 我们可以把余数和坐标放到几何中，当余数存在时，判断两个坐标的差值是否大于 2

**参考代码**

```java
public boolean checkSubarraySum(int[] nums, int k) {
  int len = nums.length; // 数组长度
  if (len < 2) // 长度小于2的不做考虑
    return false;
	// 存放余数和坐标的集合
  Map<Integer, Integer> map = new HashMap<>();
  // 0位置之前的子数组集合不存在，用-1表示位置
  // 计算从0位置开始的子数组时，sum[i]的值为0，所以sum[i]的余数为0
  map.put(0, -1); 
  // 前缀数组的和
  int sum = 0; 
  for (int i = 0; i < len; i++) {
    sum += nums[i];
    // 前缀数组的余数
    int remainder = sum % k;
    // 判断是否存在相同的余数
    if (map.containsKey(remainder)) {
      // 判断子数组长度是否大于2
      if (i - map.get(remainder) >= 2)
        return true;
    } else {
      // 不存在时候，把新出现的余数和位置坐标添加到集合
      map.put(remainder, i);
    }
  }
  return false;
}
```

### 优雅升级

怎么优雅的升级的呢？

我们可以优化掉前缀数组之和这个参数

 **`(sum[i] + num[j]) % k`与`(sum[i] % k + num[j]) % k`的值是相等的。**

**参考代码**

```java
public boolean checkSubarraySum(int[] nums, int k) {
  int m = nums.length;
  if (m < 2) {
    return false;
  }
  Map<Integer, Integer> map = new HashMap<Integer, Integer>();
  map.put(0, -1);
  int remainder = 0;
  for (int i = 0; i < m; i++) {
    remainder = (remainder + nums[i]) % k;
    if (map.containsKey(remainder)) {
      int prevIndex = map.get(remainder);
      if (i - prevIndex >= 2) {
        return true;
      }
    } else {
      map.put(remainder, i);
    }
  }
  return false;
}
```

当然还有优雅的操作， Yao大佬把位置的存储也省略了，只记录余数，这个有趣

**参考代码**

```java
public boolean checkSubarraySum(int[] nums, int k) {
  int m = nums.length;
  if (m < 2) {
    return false;
  }
  Set<Integer> set = new HashSet<>();
  int remainder = 0;
  for (int num : nums)  {
    int pre = remainder;
    remainder = (remainder + num) % k;
    if (set.contains(remainder)) {
        return true;
    }
    set.add(pre);
  }
  return false;
}
```

你看懂这个操作了吗？