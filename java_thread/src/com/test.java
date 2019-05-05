package com;


public class test {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("hello world")).start();
    }
}


//import javafx.scene.input.DataFormat;
//
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//class MyThread implements Runnable {
//    private int ticket = 10;
//
//    @Override
//    public void run() {
//
//
//
////        System.out.println("子线程开始执行...");
////        test.printTime();
////        try {
////            Thread.sleep(5000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//////        Thread.yield();
////        test.printTime();
////        System.out.println("子线程run方法结束...");
//
//
////        for(int i = 0; i < 10; i++) {
//////            Thread.yield();
////            System.out.println(Thread.currentThread().getName()+i);
////        }
//        while (this.ticket > 0) {
//            System.out.println(Thread.currentThread().getName()+"剩余票数"+this.ticket--);
//        }
//    }
//}
//
//public class test {
//    public static void main(String[] args) throws InterruptedException {
//
//
//
//
////        System.out.println("main方法开始...");
////        MyThread mt = new MyThread();
////        Thread thread = new Thread(mt);
////        thread.start();
//////        thread.join();
////
////        System.out.println("main方法结束...");
//
//
//        MyThread mt = new MyThread();
//        Thread thread1 = new Thread(mt,"A线程");
//        Thread thread2 = new Thread(mt,"B线程");
//        Thread thread3 = new Thread(mt,"C线程");
//////        thread1.setPriority(Thread.MAX_PRIORITY);
////        thread2.setPriority(Thread.NORM_PRIORITY);
////        thread3.setPriority(Thread.MIN_PRIORITY);
////
//        thread1.start();
//        thread2.start();
//        thread3.start();
//
//
//    }
//    public static void printTime() {
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String str = dateFormat.format(date);
//        System.out.println(str);
//
//    }
//}
