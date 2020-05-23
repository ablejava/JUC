package com.juc.thread.synchronizedThread;

import java.util.concurrent.TimeUnit;

public class AccountingSync7 {

    synchronized void m(){
        System.out.println(" m start");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" m end");
    }


    public static void main(String[] args) {
        new Child().m();
    }
}
