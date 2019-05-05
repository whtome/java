package com.bit;


import java.util.ArrayList;
import java.util.List;

/*
*OOM异常
* -Xmx设置最大值   -Xms设置最小值
* */

class OOMTest{}

//public class text7 {
//    public static void main(String[] args) {
//        List<OOMTest> list = new ArrayList<>();
//        while(true) {
//            list.add(new OOMTest());
//        }
//    }
//}


/*
* SOF
* -Xss128k
* */
public class text7 {
    private int length = 1;
    public void stackTest(){
        length++;
        stackTest();
    }
    public static void main(String[] args) {
        text7 test = new text7();
        try{
            test.stackTest();
        }catch (Throwable e) {
            e.printStackTrace();
            System.out.println("栈深度："+test.length);
        }

    }
}