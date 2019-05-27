package com.QueueImpl;

import com.Queue;

public class ArrayLoopQueue<E> implements Queue<E> {

    private Object[] elementDta;
    private int head;
    private int tail;
    private int size;

    public ArrayLoopQueue(int maxSize) {
        elementDta = new Object[maxSize+1];
    }

    @Override
    public void enQueue(E e) {
        if ((tail+1)%elementDta.length == head) {
            System.err.println("队列已满");
            return;
        }else {
            elementDta[tail] = e;
            tail = (tail+1)%elementDta.length;
        }
        size++;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            System.err.println("队已空");
            return null;
        }
        E result = (E) elementDta[head];
        head = (head+1)%elementDta.length;
        size--;
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            System.err.println("队已空");
            return null;
        }
        return (E) elementDta[head];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }
}
