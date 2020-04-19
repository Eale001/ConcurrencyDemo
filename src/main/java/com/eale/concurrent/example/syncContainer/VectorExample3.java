package com.eale.concurrent.example.syncContainer;

import com.eale.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class VectorExample3 {

    // 在循环结束时才删除
    // 同步容器

    private static void test1(Vector<Integer> v1){ // foreach
        for (Integer i : v1){
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    // 删除时需要用synchronized处理
    private static void test2(Vector<Integer> v2){ // iterator
        Iterator<Integer> iterator = v2.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i.equals(3)){
                iterator.remove();
            }
        }

    }

    private static void test3(Vector<Integer> v3){
        for (int i = 0; i< v3.size(); i++){
            if (i == 3){
                v3.remove(i);
            }
        }
    }

    public static void main(String[] args) {

        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

//        test1(vector);
//        test2(vector);
        test3(vector);
    }

}
