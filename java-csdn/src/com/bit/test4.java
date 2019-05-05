package com.bit;

//写两个线程，一个线程打印1~52，一个线程打印A~Z
//打印顺序为12A34B....5152Z

class Print{
    private boolean flag = true;
    private int count = 1;
    public synchronized void printNum(){
        if (flag == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(2*count - 1);
        System.out.print(2*count);
        flag = false;
        notify();
    }
    public synchronized void printChar(){
        if (flag == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print((char) ('A'+count));
        flag = true;
        notify();
        count++;
    }
}

public class test4 {
    public static void main(String[] args) {
        Print print = new Print();
        //打印数字线程
        new Thread(()->{
            for (int i = 0; i < 26; i++) {
                print.printNum();
            }
        }).start();
        //打印字母线程
        new Thread(()->{
            for (int i = 0; i < 26; i++) {
                print.printChar();
            }
        }).start();
    }
}
