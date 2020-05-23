package com.juc.thread.lock.conditionTestManyToMany;

/**
 * Created by Administrator on 2017/4/26.
 */
public class Run {
    public static void main(String[] args) {
        MyService service = new MyService();
        MyThreadA[] threadA = new MyThreadA[10];
        MyThreadB[] threadB = new MyThreadB[10];
        for (int i = 0; i < 10; i++) {
            threadA[i] = new MyThreadA(service);
            threadB[i] = new MyThreadB(service);
            threadA[i].start();
            threadB[i].start();
        }
    }
}
