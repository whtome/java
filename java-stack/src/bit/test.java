package bit;

import bit.impl.ArrayStackImpl;
import bit.impl.LinkedStackImpl;

import java.util.List;

public class test {
    public static void main(String[] args) {

        LinkedStackImpl<Integer> linkedStack = new LinkedStackImpl<>();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(5);
        linkedStack.push(6);
        System.out.println(linkedStack.getSize());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.peek());
        System.out.println(linkedStack.getSize());
        System.out.println(linkedStack.isEmpty());



//        ArrayStackImpl<Integer> arrayStack = new ArrayStackImpl<>(5);
//        arrayStack.push(1);
//        arrayStack.push(2);
//        arrayStack.push(3);
//        arrayStack.push(4);
//        arrayStack.push(5);
//        arrayStack.push(6);
//        System.out.println(arrayStack.getSize());
//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack.peek());
//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack.peek());
//        System.out.println(arrayStack.isEmpty());
}
}
