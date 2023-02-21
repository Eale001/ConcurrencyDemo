package com.eale.io.serialize;

/**
 * @Author HLD
 * @Date 2022/4/19 0019
 **/
public interface Serializer {

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
