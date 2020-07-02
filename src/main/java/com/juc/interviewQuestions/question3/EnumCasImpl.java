package com.juc.interviewQuestions.question3;

/**
 * 使用布尔字段控制
 */
public class EnumCasImpl {

    enum ReadyToRun{ T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;
    public static void main(String[] args) {
        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() ->{
            for (char c : aI) {
                while (r != ReadyToRun.T1){}
                System.out.println(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();
        new Thread(() ->{

            for (char c : aC) {
                while (r != ReadyToRun.T2){}
                System.out.println(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();


    }
}
