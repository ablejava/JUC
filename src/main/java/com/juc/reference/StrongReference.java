package com.juc.reference;

import com.juc.reference.Obj.M;

import java.io.IOException;

/**
 * java中的强引用
 */
public class StrongReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null; // 必须显示指定为null，这时对象就不再有引用了
        System.gc();

        System.in.read();// 当当前main线程阻塞在这，gc线程和main是独立线程，如果main线程退出了，那gc就没有意义了



    }


}
