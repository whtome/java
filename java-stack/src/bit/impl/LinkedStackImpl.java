package bit.impl;

import bit.Stack;

import java.util.prefs.NodeChangeEvent;

public class LinkedStackImpl<T> implements Stack<T> {

    private Node top;
    private int currentSize;

    class Node {
        T t;
        Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }
    }

    //入栈
    @Override
    public void push(T t) {
        Node newNode = new Node(t,null);
        if (currentSize == 0) {
            top = newNode;
        }else {
            newNode.next = top;
            top = newNode;
        }
        currentSize++;
    }

    //出栈
    @Override
    public T pop() {
        if (currentSize == 0) {
            System.out.println("栈已空");
            return null;
        } else {
            T t = top.t;
            top.t = null;
            top = top.next;
            currentSize--;
            return t;
        }
    }

    @Override
    public T peek() {
        if (currentSize == 0) {
            System.out.println("栈为空");
            return null;
        } else {
            return top.t;
        }
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }
}
