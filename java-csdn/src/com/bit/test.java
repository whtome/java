package com.bit;


import org.omg.CORBA.Object;

class MyThread1 implements Runnable {

    @Override
    public void run() {
       test1();
       test2();
    }
    public synchronized void test1() {
        if (Thread.currentThread().getName().equals("A")){
            while(true) {} //互斥性

            //可重入性
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("A线程进入test1方法");
//            test2();
        }
    }
    public synchronized void test2() {
        if (Thread.currentThread().getName().equals("B")) {
            System.out.println("B线程进入该同步方法test2");
        }else{
            System.out.println(Thread.currentThread().getName()+"进入test2方法");
        }
    }
}

public class test {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 mt = new MyThread1();
        Thread threadA = new Thread(mt,"A");
        Thread threadB = new Thread(mt,"B");
        threadA.start();
        Thread.sleep(1000);
        threadB.start();

    }
}
