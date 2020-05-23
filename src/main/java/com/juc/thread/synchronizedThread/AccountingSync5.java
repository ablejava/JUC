package com.juc.thread.synchronizedThread;

public class AccountingSync5 implements Runnable{

    private /*volatile*/ int count = 100;

    /**
     * 加了synchronized 就不用加volatile，
     * synchronized 既保证了原子性，又保证了可见性
     */
    @Override
    public synchronized void run() {
        count --;
        System.out.println(Thread.currentThread().getName()+ "count="+ count);

    }

    public static void main(String[] args) {
        AccountingSync5 accountingSync5 = new AccountingSync5();
        for (int i = 0; i<100; i++){
            new Thread(accountingSync5, "thread"+i).start();
        }
    }
}
