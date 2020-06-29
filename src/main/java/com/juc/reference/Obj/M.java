package com.juc.reference.Obj;

public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("在类被回收时，会调用finalize()方法，");
    }
}
