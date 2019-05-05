package com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Sync{
    public void test() {
        synchronized (this) {
            System.out.println("test方法开始，当前线程为"+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test方法结束，当前进程为"+Thread.currentThread().getName());
        }
//        System.out.println("test方法开始，当前线程为"+Thread.currentThread().getName());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("test方法结束，当前进程为"+Thread.currentThread().getName());
    }
}

class MyThread extends Thread {
    private Sync sync;
    public MyThread(Sync sync) {
        this.sync = sync;
    }
    @Override
    public void run() {
        Sync sync = new Sync();
        sync.test();
    }


}

public class Test {
    public static void main(String[] args) {
        Sync sync = new Sync();
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread(sync);
            thread.start();
        }

    }
}
