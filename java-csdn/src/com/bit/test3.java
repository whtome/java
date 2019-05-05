package com.bit;

import java.util.ArrayList;
import java.util.List;

//商品类
class Goods {
    private String goodsName;
    private int count;
    //生产方法
    public synchronized void setGoods(String goodsName) {
        while(this.count > 0) {
            System.out.println("还有冰淇淋，不要急");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.goodsName = goodsName;
        this.count++;
        System.out.println(Thread.currentThread().getName()+"生产"+toString());
        //消费商品后唤醒生产者
        notify();
    }
    //消费方法
    public synchronized void getGoods() {
        while (count == 0){
            System.out.println("没有冰淇淋了，正在做");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count--;
        System.out.println(Thread.currentThread().getName()+"消费"+toString());
        //生产商品后唤醒消费者
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsName='" + goodsName + '\'' +
                ", count=" + count +
                '}';
    }
}

//生产类
class Producer implements Runnable{
    private Goods goods;
    public Producer(Goods goods ) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            this.goods.setGoods("一个冰淇淋");
        }
    }
}

//消费类
class Consumer implements Runnable{

    private Goods goods;

    public Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true) {
            this.goods.getGoods();
        }
    }
}

public class test3 {
    public static void main(String[] args) {
        Goods goods = new Goods();
        List<Thread> list = new ArrayList<>();
        Producer producer = new Producer(goods);
        Consumer consumer = new Consumer(goods);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(producer,"生产者"+i);
            list.add(thread);
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(consumer,"消费者"+i);
            list.add(thread);
        }
        for(Thread thread : list) {
            thread.start();
        }

    }
}
