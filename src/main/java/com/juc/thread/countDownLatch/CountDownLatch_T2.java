package com.juc.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_T2 {

    private static void usingCountDownLatch(){
        Thread[] threads = new Thread[100];

        // 门栓的个数为threads.length
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                System.out.println("thread :"+ Thread.currentThread().getName() + "执行。。。。" +result);
                // 线程执行完-1
                latch.countDown();

                System.out.println("thread :"+ Thread.currentThread().getId() + "countDown。。。。");
            });
        }

        // 启动线程
        for (int i =0; i < threads.length ; i++){
            threads[i].start();
        }


        try {
            /**
             *
             * 给线程栓住，当线程数量达到 threads.length时执行才让线程继续执行
             *
             */
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch....");
    }


    public static void main(String[] args) {
        // usingCountDownLatch();

        usingJoin();
    }


    private static void usingJoin(){
        Thread[] threads = new Thread[100];

        /**
         * 创建100个线程
         */
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() ->{
                int result = 0;
                for (int j =0; j < 10000; j++){
                    result += j;
                }

                System.out.println(Thread.currentThread().getName()+ "thread execute result: " + result);
            });
        }

        // 启动所有线程
        for (int i =0; i <threads.length ; i++){
            threads[i].start();
        }

        /**
         * join当作用是：
         * 只有当前线程执行完毕，才能执行下一个线程
         * thread[1]执行完result，才能执行thread[2]
         */
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("end join>>>>>>>>>>>>>");
    }
}
