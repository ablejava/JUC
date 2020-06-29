package com.juc.reference;

import com.juc.reference.Obj.M;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 */
public class PhantomReference_T {

    private static final List<Object> LIST = new LinkedList<>();

    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    /**
     * 虚引用的作用：
     *  虚引用是写给jvm人用的，虚引用的get()是get()不到的
     *  当引用M被回收时，会收到一个通知，往Queue里放一个值
     * 应用：
     *   NOI中有个DirectByteBuffer(也叫直接内存)，垃圾回收器无法回收DirectByteBuffer，使用虚引用，当检测到被垃圾回收器回收的时候
     *   去回收堆外内存。
     * @param args
     */
    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        new Thread(() ->{
            while (true) {
                LIST.add(new byte[1024*1024]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

                System.out.println(phantomReference.get());
            }
        }).start();


        new Thread(()->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("-----虚引用对象被jvm回收了------"+poll);
                }
            }
        }).start();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
