package com.juc.thread.synchronizedThread;

/**
 * Created by Administrator on 2018/7/8.
 */
public class AccountingSync2 implements Runnable {
    static AccountingSync2 instance = new AccountingSync2();
    static int i =0;

    /**
     * synchronized 方法和synchronized (this)代码等值
     */
    public synchronized void increase() {
        i++;
    }

    /*public void increase() {
        synchronized (this){
            i++;
        }
    }*/

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
