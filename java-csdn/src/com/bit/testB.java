package com.bit;

/**
 * finzlize()自我拯救
 * 只会被调用一次
 */
/
public class testB {
    private static testB test;
    public void isAlize(){
        if (test != null) {
            System.out.println("I am alize");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method execute");
        test = this;
    }

    public static void main(String[] args) {
        test = new testB();
        test = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (test != null) {
            test.isAlize();
        }else{
            System.out.println("I am die");
        }

        //----------------

        test = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (test != null) {
            test.isAlize();
        }else{
            System.out.println("I am die");
        }
    }
}
