package com.bit;

//编写一个程序，启动三个线程，名称分别为A,B,C,
//每个线程将自己的名称在屏幕上打印三遍，顺序为ABCABC...


//输出有问题

class Print1{
    private int flag = 1;
    private int count = 0;

    public int getCount() {
        return count;
    }

    public synchronized void printA(){
        while (flag != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = 2;
        notifyAll();
        count++;
    }
    public synchronized void printB(){
        while (flag != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = 3;
        notifyAll();


    }
    public synchronized void printC(){
        while (flag != 3){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = 1;
        notifyAll();

    }
}
class MyThread5 implements Runnable{

    private Print1 print;

    public MyThread5(Print1 print) {
        this.print = print;
    }

    @Override
    public void run() {
        while (print.getCount() < 5){
            if(Thread.currentThread().getName().equals("A")){
                print.printA();
            }else if (Thread.currentThread().getName().equals("B")){
                print.printB();
            }else if (Thread.currentThread().getName().equals("C")){
                print.printC();
            }
        }
    }
}

public class test5 {
    public static void main(String[] args) throws InterruptedException {
        Print1 print = new Print1();
        MyThread5 mt = new MyThread5(print);
        new Thread(mt,"A").start();
        new Thread(mt,"B").start();
        new Thread(mt,"C").start();

    }
}
