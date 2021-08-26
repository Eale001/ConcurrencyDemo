package com.eale.filter;

/**
 * @Author HLD
 * @Date 2021/4/20 0020
 * @Description // 布隆过滤器
 * @Version 1.0
 **/
public class SimpleBloomFilter {

    private byte[] data;


    public SimpleBloomFilter(int initSize) {
        // 默认创建大小*2的空间
        this.data = new byte[initSize*2];
    }

    public void add(int key){
        int location1 = Math.abs(hash1(key) % data.length);
        int location2 = Math.abs(hash2(key) % data.length);
        int location3 = Math.abs(hash3(key) % data.length);
        data[location1] = data[location2] = data[location3] = 1;
    }

    public boolean contains(int key){
        int location1 = Math.abs(hash1(key) % data.length);
        int location2 = Math.abs(hash2(key) % data.length);
        int location3 = Math.abs(hash3(key) % data.length);

        return data[location1] * data[location2] * data[location3] == 1;
    }


    private int hash1(Integer key){
        return key.hashCode();
    }

    private int hash2(Integer key){
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 3);
    }

    private int hash3(Integer key){
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }




}
