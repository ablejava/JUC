package com.juc.interviewQuestions.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Semphore实现
 */
public class SemphoreImpl {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }


    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {
        SemphoreImpl c = new SemphoreImpl();

        Semaphore s = new Semaphore(1);

        t1 = new Thread(() -> {

            try {
                // 限制其他线程运行
                s.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                }
                // 其他线程可以运行
                s.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                }

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "t1");


        t2 = new Thread(() ->{

            try {
                s.acquire();
                System.out.println("t2结束");
                s.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
    }

}
