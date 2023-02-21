package com.eale.io.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.eale.io.serialize.Serializer;

/**
 * @Author HLD
 * @Date 2022/4/19 0019
 **/
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
