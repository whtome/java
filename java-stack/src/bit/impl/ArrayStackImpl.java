package bit.impl;

import bit.Stack;

public class ArrayStackImpl<T> implements Stack<T> {


    private Object[] elementdata;
    private int currentSize;
    private int maxSize;

    public ArrayStackImpl(int maxSize) {
        this.maxSize = maxSize;
        elementdata = new Object[maxSize];
    }

    @Override
    public void push(T t) {
        if (currentSize < maxSize) {
            elementdata[currentSize++] = t;
        }else {
            System.out.println("栈已满");
        }
    }

    @Override
    public T pop() {
        if (currentSize > 0 ) {
            return (T) elementdata[--currentSize];
        }else {
            System.out.println("栈已空");
            return null;
        }
    }

    @Override
    public T peek() {
        if (currentSize == 0) {
            System.out.println("栈为空");
            return null;
        } else {
            return (T) elementdata[currentSize-1];
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
