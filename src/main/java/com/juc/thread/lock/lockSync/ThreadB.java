package com.juc.thread.lock.lockSync;

/**
 * Created by Administrator on 2017/4/25.
 */
public class ThreadB extends Thread {
    private MyService service;

    public ThreadB(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
