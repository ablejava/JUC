package com.juc.interviewQuestions.question3;

import java.util.concurrent.CountDownLatch;

/**
 * 使用布尔字段控制
 */
public class BooleanCasImpl {

    private static volatile boolean t2Started = false;

    // private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() ->{
            // latch.await();
            synchronized (o) {
                while (!t2Started) {

                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (char c : aI) {
                    System.out.println(c);

                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                o.notify();
            }
        }, "t1").start();
        new Thread(() ->{
            // latch.await();
            synchronized (o) {
                for (char c : aC) {
                    System.out.println(c);
                    // latch.countDown();
                    t2Started = true;
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }
        }, "t2").start();


    }
}
