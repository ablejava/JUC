package com.juc.interviewQuestions.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 采用CountDownLatch实现
 */
public class CountDownLatchImpl {

    volatile List lists = new ArrayList();


    public void add(Object o) {
        lists.add(o);
    }




    public int size() {
        return lists.size();
    }


    public static void main(String[] args) {
        CountDownLatchImpl c = new CountDownLatchImpl();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() ->{
            System.out.println("t2 启动。。。。");

            if (c.size() != 5) {
                try {

                    // 先让t2等待
                    latch.wait();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println(" t2 结束");
        }, "t2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        new Thread(()-> {
            System.out.println("t1 启动。。。。。");

            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " +i);

                if (c.size() == 5) {
                    // 打开门栓，让t2得以执行
                    latch.countDown();


                    try {
                        // 给t1上门栓， 让t2有机会执行
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }, "t1").start();
    }
}
