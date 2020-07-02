package com.juc.interviewQuestions.question3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionI2mpl {


    /**
     * 使用一个condition实现
     * @param args
     */
    public static void main(String[] args) {

        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();



        new Thread(() ->{
            try {
                lock.lock();
                for (char c : aI) {
                    System.out.println(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() ->{
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.println(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }

}
