package com.juc.thread.synchronizedThread;

public class AccountingSync6 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start...");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" m1 end...");
    }

    public  void m2(){
        System.out.println(Thread.currentThread().getName()+" m2 start...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" m2 end...");
    }


    public static void main(String[] args) {
        AccountingSync6 accountingSync6 = new AccountingSync6();
        /**
         * jdk1.8之前写法
         */
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                accountingSync6.m1();
            }
        });*/
        /**
         * jdk1.8之后
         */
        new Thread(() -> accountingSync6.m1(), "m1").start();
        new Thread(()-> accountingSync6.m2(), "m2").start();

        new Thread(accountingSync6 ::m1).start();
        new Thread(accountingSync6 ::m2).start();
    }
}
