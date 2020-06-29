package com.juc.reference;

import com.juc.reference.Obj.M;

import java.lang.ref.WeakReference;

public class WeakReference_T {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


        // ti是个强引用指向ThreadLocal对象，而ThreadLocalMap中当key是弱引用指向了ThreadLocal对象，

        ThreadLocal<M> t1 = new ThreadLocal<>();
        /**
         * set的时候放的是Entry对象，entry的key是threadlocal，value是M
         * Entry是一个弱引用类型对象，
         * 当方法执行完毕，t1是一个局部变量，方法结束t1就消失了，如果ThreadLocal还被一个强引用指向就不回被gc回收
         * 对象就会长期存在，就会有内存泄漏，所以如果key是个弱引用就不回存在这个问题了
         */
        t1.set(new M());
        /**
         * 使用完，t1被回收，key被置为null，value指向当内存数据还会存在，但是访问不到
         * 使用完必须remove，不然还会有内存泄漏
          */

        t1.remove();
    }
}
