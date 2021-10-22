package com.eale.leetcode;

import java.util.*;

/**
 * 【229】求众数II
 *
 * @Author HLD
 * @Date 2021/10/22 0022
 **/
public class SolutionMajorityElement {

    /**
     *  给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

     *  示例 1：
     *
     * 输入：[3,2,3]
     * 输出：[3]
     *
     *  示例 2：
     * 输入：nums = [1]
     * 输出：[1]
     *
     *  示例 3：
     * 输入：[1,1,1,3,3,2,2,2]
     * 输出：[1,2]
     *
     *  提示：
     *  1 <= nums.length <= 5 * 104
     *  -109 <= nums[i] <= 109
     *
     *  进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
     *
     *  Related Topics 数组 哈希表 计数 排序  摩尔投票法
     *  👍 486 👎 0
     */

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int i = nums.length/3;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer total = map.get(num);
            if (Objects.isNull(total)){
                total = 1;
            }else {
                total ++;
            }
            map.put(num,total);
            if (total > i && !result.contains(num)){
                result.add(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{3,2,3}));
        System.out.println(majorityElement(new int[]{1}));
        System.out.println(majorityElement(new int[]{1,1,1,3,3,2,2,2}));

    }


}
