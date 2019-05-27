package com.QueueImpl;

import com.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> lq = new ArrayLoopQueue<>(5);
        lq.enQueue(1);
        lq.enQueue(2);
        lq.enQueue(3);
        lq.enQueue(4);
        lq.enQueue(5);
        lq.deQueue();
        lq.enQueue(6);
//        while(!lq.isEmpty()){
//            System.out.println(lq.deQueue());
//        }
//        lq.deQueue();
        System.out.println(lq.peek());
        System.out.println(lq.deQueue());
        System.out.println(lq.getSize());
        System.out.println(lq.peek());
//        lq.deQueue();
//        lq.deQueue();
//        lq.deQueue();
//        lq.deQueue();
//        lq.deQueue();
//        lq.deQueue();
//        lq.deQueue();
//        System.out.println(lq.peek());

    }
}
