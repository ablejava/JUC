package com.juc.interviewQuestions.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LockImpl {
    // 添加volatile，使得t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        LockImpl c = new LockImpl();

        final Object lock = new Object();

        new Thread(() ->{
            synchronized (lock) {
                System.out.println("t2 启动。。。。");

                if (c.size() != 5) {
                    try {
                        // 上来 先给t2锁住，让t2处于等待校验
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                // 此时t2打印结束， t1已经添加了5个元素
                System.out.println("t2 结束。。。");
            }
            // 通知t1 继续执行
            lock.notify();
        }, "t2").start();


        new Thread(() ->{
            System.out.println("t1 启动。。。。");

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    // 打印添加了几个元素
                    System.out.println("add "+ i);
                    if (c.size() == 5) {
                        lock.notify();// 当添加了5个元素，让t2继续执行

                        try {
                            // 再给自己锁住，防止自己 再执行添加了元素
                            lock.wait();
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
            }
        }, "t1").start();




    }
}
