package com.eale.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Administrator
 * @Date 2021/4/6
 * @Description //请实现一个处理函数，找出数列中第一个只出现一次的数字。比如：在数列
 * {1,2,3,4,5,1,2,3,6,5}中,第一个只出现一次的数字是 4。
 * @Version 1.0
 **/
public class Test1 {


    //

    public Integer getFirstOnlyNumber(Integer[] numbers){

        List<Integer> integers = Arrays.asList(numbers);

        Map<Integer, List<Integer>> collect = integers.stream().collect(Collectors.groupingBy(Integer::intValue));

        Set<Map.Entry<Integer, List<Integer>>> entries = collect.entrySet();

        for (Map.Entry<Integer, List<Integer>> next : entries) {
            if (next.getValue().size() < 2) {
                return next.getKey();
            }
        }
        return 0;
    }

}
