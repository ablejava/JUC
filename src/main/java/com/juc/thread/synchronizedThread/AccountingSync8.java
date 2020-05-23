package com.juc.thread.synchronizedThread;

import java.util.concurrent.TimeUnit;

public class AccountingSync8 {
    int count =0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName() + "start...");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName() + "count=" +count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * 第一个线程抛出异常，有异常要多加小心，不然可能会发生不一致的情况
             * 在第一个线程抛出异常，其他线程就会进入同步代码块，有可能会访问到异常产生到数据
             */
            if (count == 5){
                int i = 1/0;
                System.out.println(i);
            }
        }
    }


    public static void main(String[] args) {
        AccountingSync8 accountingSync8 = new AccountingSync8();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                accountingSync8.m();
            }
        };

        new Thread(r, "t1").start();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "r2").start();
    }
}
