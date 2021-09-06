package com.eale.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

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

    // 快速排序思想
    private static int[] smallestKP(int[] arr,int k){
        // 递归调用快排
        randomizedSelected(arr,0,arr.length-1,k);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    private static void randomizedSelected(int[] arr, int s, int e, int k) {
        //
        if (s > e){
            return;
        }
        // 示分界值 pivot 最终在数组中的位置
        int post = randomizedPartition(arr,s,e);
        int num = post - s +1;
        if (num == k){
            return;
        }else if (num > k){
            randomizedSelected(arr, s, post - 1, k);
        }else {
            randomizedSelected(arr,post + 1,e,k - num);
        }

    }

    // 基于随机划分 划分函数
    private static int randomizedPartition(int[] arr, int s, int e) {
        int i = new Random().nextInt(e-s+1) + 1;
        swap(arr,e,i);
        return partition(arr,s,e);
    }

    private static int partition(int[] nums, int s, int e) {
        int pivot = nums[e];
        int i = s - 1;
        for (int j = s; j <= e - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, e);
        return i + 1;
    }

    private static void swap(int[] arr, int s, int e) {
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,2,4,6,8};
        int[] ints = smallestK(arr, 4);
        int[] que = smallestKQue(arr, 4);
        int[] kp = smallestKP(arr, 4);
        System.out.println(JSON.toJSONString(ints));
        System.out.println(JSON.toJSONString(que));
        System.out.println(JSON.toJSONString(kp));
    }

}
