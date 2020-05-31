package com.juc.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo_T2 {

    public static void main(String[] args) {
        // CyclicBarrier barrier = new CyclicBarrier(20); // 什么也不做

        /**
         * CyclicBarrier 当任务就是：每次满20个线程，输出：满人。。。。。
         */
        CyclicBarrier barrier = new CyclicBarrier(20, () ->{
            System.out.println("满人 。。。。");
        });


        /**
         * 创建100个线程，每满20个线程，输入一次
         */
        for (int i=0; i<100; i++){
            new Thread(() ->{
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
