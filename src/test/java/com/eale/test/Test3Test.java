package com.eale.test;

import org.junit.Assert;
import org.junit.Test;


public class Test3Test {

    @Test
    public void transformCn() {

        Test3 test3 = new Test3();

        long number = 1000000003;
        String s = test3.transformCn(number);
        Assert.assertEquals("十亿零三",s);
        long number1 = 100003;
        String s1 = test3.transformCn(number1);
        Assert.assertEquals("十万零三",s1);
    }
}