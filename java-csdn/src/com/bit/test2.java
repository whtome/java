package com.bit;

class MyThread2 implements Runnable{

    private boolean flag;
    //锁对象
    private Object object;

    public MyThread2(boolean flag, Object object) {
        this.flag = flag;
        this.object = object;
    }


    public void waitMethod(){
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+" wait begin...");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" wait end...");
        }
    }
    public void notifyMethod(){
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+" notify begin...");
            object.notify();
            System.out.println(Thread.currentThread().getName()+" notify end...");
        }
    }

    @Override
    public void run() {
        if (flag){
            waitMethod();
        }else{
            notifyMethod();
        }
    }
}



public class test2 {
    public static void main(String[] args) {
        Object object = new Object();
        MyThread2 mt = new MyThread2(true,object);
        MyThread2 mt2 = new MyThread2(false,object);
        new Thread(mt,"wait").start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(mt2,"notify").start();
    }
}
