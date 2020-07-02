package com.juc.interviewQuestions.question3;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport实现
 */
public class LockSupportImpl {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        t1 = new Thread(() ->{
            for (char c : aI) {
                System.out.println(c);
                LockSupport.unpark(t2); // 叫醒t2
                LockSupport.park(); // T1阻塞
            }
        }, "t1");

        t2 = new Thread(() ->{
            for (char c : aC) {
                LockSupport.park(); // T2阻塞
                System.out.println(c);
                LockSupport.unpark(t1); // 叫醒t1
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
