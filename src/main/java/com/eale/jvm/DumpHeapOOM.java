package com.eale.jvm;

import java.util.Vector;

/**
 * @Author HLD
 * @Date 2021/11/18 0018
 **/
public class DumpHeapOOM {

    public static void main(String[] args) {


        Vector vector = new Vector();
        for (int i = 0; i < 25; i++) {
            vector.add(new byte[1024*1024]);
        }
    }


}
