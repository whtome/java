package www.bit;

import java.util.Date;

interface  IMessage{}
interface INews{}
class MyClass implements IMessage,INews {}


//interface IComputer {
//    void buyComputer();
//}
//class Macbook implements IComputer {
//
//    @Override
//    public void buyComputer() {
//        System.out.println("买一台苹果电脑");
//    }
//}
//class Surface implements IComputer {
//
//    @Override
//    public void buyComputer() {
//        System.out.println("买一台微软电脑");
//    }
//}
//class ComputerFactory {
//    public static IComputer getInstance(String computerClass) {
//        try {
//            Class<?> cls = Class.forName(computerClass);
//            IComputer computer = (IComputer) cls.newInstance();
//            return computer;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return null;


//        if (computerName.equals("mac")) {
//            return new Macbook();
//        }else if (computerName.equals("surface")) {
//            return new Surface();
//        }else {
//            return null;
//        }
//    }
//}

public class test {
    public static void main(String[] args) {
        Class<test> cls = test.class;
        Class[] classes = MyClass.class.getInterfaces();
        for(Class cls1 : classes) {
            System.out.println(cls1);
        }
//        System.out.println(cls.getSuperclass());


//        IComputer computer = ComputerFactory.getInstance("www.bit.Macbook");
//        computer.buyComputer();

//        反射
//        Class<Date> cls = Date.class;
//        //通过反射取得Date类对象
//        Date date = cls.newInstance();
//        System.out.println(date);

//        //正向处理
//        Date data = new Date();
//        //取得Date类的class对象
//        Class<Date> cls = (Class<Date>) data.getClass();
//        System.out.println(cls);
//
//        System.out.println(Date.class);
//
//        Class cls1 = Class.forName("java.util.Date");
//        System.out.println(cls1);
    }
}



//    //泛型
//class MyClass<T> {
//    private T t;
//    public T getT() {
//        return t;
//    }
//
//    public void setT(T t) {
//        this.t = t;
//    }
//}
//
//public class test {
//    public static void main(String[] args) {
//        MyClass<String> myClass = new MyClass<>();
////        myClass.setT("hello world");
//        fun(myClass);
//
//    }
//    //fun可接收
//    public static void fun(MyClass<? super String> myClass) {
//        //object obj  =  new String("hello bit")
//        myClass.setT("hello bit");
//        System.out.println(myClass.getT());
//    }
//}
