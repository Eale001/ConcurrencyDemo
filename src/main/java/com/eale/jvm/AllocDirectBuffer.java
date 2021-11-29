package com.eale.jvm;

import java.nio.ByteBuffer;

/**
 * @Author HLD
 * @Date 2021/11/18 0018
 **/
public class AllocDirectBuffer {

    public void directAlloc(){
        long current = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocateDirect(1000);
        }
        long current1 = System.currentTimeMillis();
        System.out.println("test direct alloc : " + (current1-current));
    }

    public void bufferAlloc(){
        long current = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocate(1000);
        }
        long current1 = System.currentTimeMillis();
        System.out.println("test buffer alloc : " + (current1-current));
    }

    public static void main(String[] args) {
        AllocDirectBuffer db = new AllocDirectBuffer();
        db.bufferAlloc();
        db.directAlloc();

        db.bufferAlloc();
        db.directAlloc();

    }


}
