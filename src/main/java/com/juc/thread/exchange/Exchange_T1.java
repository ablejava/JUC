package com.juc.thread.exchange;

import java.util.concurrent.Exchanger;

public class Exchange_T1 {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";

            try {
                exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() +" "+s);
        }, "t1").start();


        new Thread(()->{
            String s = "T2";

            try {

                exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() +" "+ s);
        }, "t2").start();

    }
}
