package com.QueueImpl;

import com.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private Object[] elementData;
    private int head;
    private int tail;

    public ArrayQueue(int maxSize) {
        elementData = new Object[maxSize];
    }

    @Override
    public void enQueue(E e) {
        if (tail == elementData.length) {
            if (head == 0) {
                System.err.println("当前队列已满");
                return;
            }else {
                //数据搬移
                for (int i = head; i < tail; i++) {
                    elementData[i - head] = elementData[i];
                }
                //重新赋值首尾指针
                tail = tail-head;
                head = 0;
            }
        }
        elementData[tail++] = e;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            System.err.println("当前队列为空");
            return null;
        }
        return (E) elementData[head++];
    }

    @Override
    public E peek() {
        if (isEmpty()){
            System.err.println("当前队列为空");
            return null;
        }
        return (E) elementData[head];
    }

    @Override
    public int getSize() {
        return tail-head;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }
}
