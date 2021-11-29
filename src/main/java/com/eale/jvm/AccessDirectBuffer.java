package com.eale.jvm;

import java.nio.ByteBuffer;

/**
 *
 * 直接内存
 *
 * @Author HLD
 * @Date 2021/11/18 0018
 **/
public class AccessDirectBuffer {


    public void directAccess(){
        long current = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
        long current1 = System.currentTimeMillis();
        System.out.println("test direct access "+ (current1 - current));
    }

    public void bufferAccess(){
        long current = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
        long current1 = System.currentTimeMillis();
        System.out.println("test buffer access: "+ (current1 - current));
    }

    public static void main(String[] args) {
        AccessDirectBuffer db = new AccessDirectBuffer();

        db.directAccess();
        db.bufferAccess();

        db.directAccess();
        db.bufferAccess();

    }


}
