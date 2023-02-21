package com.eale.concurrent.unsafe;

import java.util.List;
import java.util.Vector;

/**
 * @Author HLD
 * @Date 2021/12/14 0014
 **/
public class ThreadUnSafe {

    public static List<Integer> list = new Vector<>();

    public static class AddToInt implements Runnable{

        int startNum = 0;

        public AddToInt(int startNumber) {
            startNum = startNumber;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 100000){
                list.add(startNum);
                startNum +=2;
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new AddToInt(0));
        Thread thread1 = new Thread(new AddToInt(1));
        thread.start();
        thread1.start();

    }


}
