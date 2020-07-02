package com.juc.Atomic;

import java.util.concurrent.LinkedTransferQueue;

public class TestTransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /**
         * transfer 就相当于装完在者等着，等人给它取走菜回去干别等事情
         */
        strs.transfer("aaaa");
        /**
         * put方法就相当于一个线程来来往里装它就走来
         */
        // strs.put("aaa");


//        new Thread(() ->{
//            try {
//                System.out.println(strs.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
