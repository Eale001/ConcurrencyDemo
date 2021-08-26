package com.eale.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author HLD
 * @Date 2021/4/20 0020
 * @Description //BloomFilterTest
 * @Version 1.0
 **/
public class BloomFilterTest {


    public static void main(String[] args) {

        // 创建布隆过滤器对象
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));

    }


}
