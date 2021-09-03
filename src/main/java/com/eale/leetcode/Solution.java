package com.eale.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author HLD
 * @Date 2021/9/3 0003
 * @Description
 * @Version 1.0
 **/
public class Solution {
    /**
     *  设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     *
     *  示例：
     *  输入： arr = [1,3,5,7,2,4,6,8], k = 4
     *  输出： [1,2,3,4]
     *
     *  提示：
     *  0 <= len(arr) <= 100000
     *  0 <= k <= min(100000, len(arr))
     *
     *  Related Topics 数组 分治 快速选择 排序 堆（优先队列）
     *  👍 98 👎 0
     */
    // 排序
    public static int[] smallestK(int[] arr, int k) {
        int[] result = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    // 堆
    public static int[] smallestKQue(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0 ){
            return result;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,2,4,6,8};
        int[] ints = smallestK(arr, 4);
        int[] que = smallestKQue(arr, 4);
        System.out.println(JSON.toJSONString(ints));
        System.out.println(JSON.toJSONString(que));
    }

}
