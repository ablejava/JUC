package com.juc.reference;

import java.lang.ref.SoftReference;

public class SoftReference_T {

    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]); // 分配10M空间
        System.out.println(m.get());
        System.gc();

        Thread.sleep(500);

        System.out.println(m.get());

        // 再分配15M内存，内存不够，软引用会被干掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }


}
