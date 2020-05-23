package com.juc.thread.synchronizedThread;

public class Child extends AccountingSync7 {
    @Override
    synchronized void m() {
        System.out.println("child m start");

        /**
         * 子类调用父类中的synchronized方法，是同一把锁
         */
        super.m();

        System.out.println("child m end");
    }

}
