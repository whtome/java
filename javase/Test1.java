package www.bit;

import javax.sound.midi.MetaEventListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Person {
    private Integer age;
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Person() {}

    private Person (Integer age) {
    this.age = age;
    }

    private Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }



    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}



public class Test1 {
    public static void code1() {
        //        Class<Person> cls = Person.class;
//        Constructor constructor = cls.getDeclaredConstructor(Integer.class,String.class);
//        //动态破坏封装，仅在一次JVM进程中，可以取得私有
//        constructor.setAccessible(true);
//        Person per = (Person) constructor.newInstance(18,"me");
//        System.out.println(per);

//        Class<Person> cls = Person.class;
//        //getConstructors只能取得权限为Public的构造方法
//        //Constructor[] constructors = cls.getConstructors();
//
//        //getDeclaredConstructors可以取得类中所有的构造方法，与权限无关
//        Constructor[] constructors = cls.getDeclaredConstructors();
//        for(Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
    }
    public static void code2() {
        //        正向处理
//        Person per = new Person();
//        per.setName("张三");
//        System.out.println(per.getName());
//
//        //反射
//        //1.取得Class对象
//        Class<Person> cls = Person.class;
//        //2.组装方法名称
//        String setMethodName = "set"+initCap(args[0]);
//        String getMethodName = "get"+initCap(args[0]);
//        //3.取得Method对象
//        Method setMethod = cls.getMethod(setMethodName,String.class);
//        Method getMethod = cls.getMethod(getMethodName);
//        //4.取得Person类的实例化对象而后调用方法
//        Person per = cls.newInstance();
//        setMethod.invoke(per,"张三");
//        System.out.println(getMethod.invoke(per));
//    }
//    private static String initCap(String str) {
//        return str.substring(0,1).toUpperCase()+str.substring(1);
//    }
    }
    public static void code3() {
//        Class<Person> cls = Person.class;
//        //取得属性值
//        Field field = cls.getField("age");
//        //取得Person实例化对象
//        Person per = cls.newInstance();
//        //set&get
//        field.set(per,20);
//        System.out.println(field.get(per));
//        System.out.println(field.getType());
    }
    public static void main(String[] args) throws Exception {
        Class<Person> cls = Person.class;
        Field field = cls.getDeclaredField("age");
        Person per = cls.newInstance();
        //动态破坏封装
        field.setAccessible(true);
        field.set(per,20);
        System.out.println(field.get(per));
    }
}

