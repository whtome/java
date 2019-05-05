package com.bit;

/**
 * -XX:+PrintGC
 * */

public class testA {
    private Object instance;
    private static int _1MB = 1024*1024;
    private byte[]bigSize = new byte[2*_1MB];

    public static void main(String[] args) {
        //+1
        testA test1 = new testA();
        testA test2 = new testA();
        //循环引用
        test1.instance = test2;
        //+1
        test2.instance = test1;
        //-1
        test1 = test2 = null;
        System.gc();

    }
}
