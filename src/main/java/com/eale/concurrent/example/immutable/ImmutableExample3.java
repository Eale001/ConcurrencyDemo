package com.eale.concurrent.example.immutable;

import com.eale.concurrent.annoations.NotThreadSafe;
import com.eale.concurrent.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ImmutableExample3 {


    private final static ImmutableList list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map1 = ImmutableMap.of(1,2,3,4);

    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,3).put(4,5).build();

    public static void main(String[] args) {

        System.out.println(map2.get(1));

    }

}
