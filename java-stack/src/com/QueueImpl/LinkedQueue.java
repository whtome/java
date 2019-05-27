package com.QueueImpl;

import com.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private Node head;
    private Node tail;
    private int count;

    private class Node{
        private E data;
        private Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public void enQueue(E e) {
        Node newNode = new Node(e,null);
        if (head == null) {
            head = tail = newNode;
        }else {
            tail.next = newNode;
            tail = tail.next;
        }
        count++;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            System.err.println("队已空");
            return null;
        }

            E result = head.data;
            head = head.next;
            count--;
            return result;

    }

    @Override
    public E peek() {
        if (isEmpty()){
            System.err.println("队已空");
            return null;
        }else {
            return head.data;
        }
    }

    @Override
    public int getSize() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
