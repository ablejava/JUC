package com.juc.thread.threadGroupException;

/**
 * Created by Administrator on 2017/4/28.
 */
public class ObjectUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(" 对象的异常处理 ");
        e.printStackTrace();
    }
}
