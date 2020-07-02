package com.juc.interviewQuestions.question3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionImpl {


    /**
     * 使用一个condition实现
     * @param args
     */
    public static void main(String[] args) {

        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() ->{
            try {
                lock.lock();
                for (char c : aI) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
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
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }

}
