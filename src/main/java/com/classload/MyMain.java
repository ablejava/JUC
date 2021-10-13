package com.classload;

/**
 * @Author: xiazhongwei
 * @Date: 2021/10/13 17:56
 */
public class MyMain {


    public static void main(String[] args) throws Exception{
        //com/maoyan/show/hk/adminweb/biz/impl/MyClassLoader.class
        MyClassLoader classLoader = new MyClassLoader("/Users/shumagang/IdeaProjects/JUC/target/classes");


        Class aClass = classLoader.loadClass("com.classload.MyClassLoader");
        Object o = aClass.newInstance();
        System.out.println(aClass.getClassLoader());

    }
}
