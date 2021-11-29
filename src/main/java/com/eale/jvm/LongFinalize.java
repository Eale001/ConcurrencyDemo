package com.eale.jvm;

/**
 * @Author HLD
 * @Date 2021/11/22 0022
 **/
public class LongFinalize {

    public static class LF{
        private byte[] bytes = new byte[512];

//        @Override
//        protected void finalize() throws Throwable {
//            try {
//                System.out.println(Thread.currentThread().getId());
//                Thread.sleep(1000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

    public static void main(String[] args) {
        long current = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            LF lf = new LF();
        }
        long now = System.currentTimeMillis();
        System.out.println(now - current);
    }

}
