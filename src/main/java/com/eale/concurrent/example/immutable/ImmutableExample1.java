package com.eale.concurrent.example.immutable;

import com.eale.concurrent.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {

        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {

        map.put(1,3);
        log.info("{}",map.get(1));

    }


    private void test(final int a){
//        a = 1;

    }

}
