package com.eale.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author Administrator
 * @Date 2021/4/14
 * @Description // 请实现一个处理函数，找出数列中第一个只出现一次的数字。比如：在数列
 *  * {1,2,3,4,5,1,2,3,6,5}中,第一个只出现一次的数字是 4。
 * @Version 1.0
 **/
public class Test1Test {

    @Test
    public void test1(){
        Test1 test1 = new Test1();
        Integer[] numbers = new Integer[]{1,2,3,4,5,1,2,3,6,5};
        Integer[] numbers1 = new Integer[]{1,2,3,4,5,1,9,3,6,5};
        Integer[] numbers2 = new Integer[]{};
        Integer[] numbers3 = new Integer[]{1,2,3,4,5,1,2,3,4,5};
        Integer firstOnlyNumber = test1.getFirstOnlyNumber(numbers);
        Integer firstOnlyNumber1 = test1.getFirstOnlyNumber(numbers1);
        Integer firstOnlyNumber2 = test1.getFirstOnlyNumber(numbers2);
        Integer firstOnlyNumber3 = test1.getFirstOnlyNumber(numbers3);

        Assert.assertEquals((long) 4,(long)firstOnlyNumber);
        Assert.assertEquals((long) 2,(long)firstOnlyNumber1);
        Assert.assertEquals((long) 0,(long)firstOnlyNumber2);
        Assert.assertEquals((long) 0,(long)firstOnlyNumber3);
    }



}
