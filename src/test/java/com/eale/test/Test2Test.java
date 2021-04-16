package com.eale.test;

import org.junit.Assert;
import org.junit.Test;


public class Test2Test {

    @Test
    public void backNumberOrder() {
        Test2 test2 = new Test2();
        int[] numbers = new int[]{1,2,3};
        String result = test2.backNumberOrder(numbers);
        Assert.assertEquals("312",result);
    }


}