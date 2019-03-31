package www.bit;

import java.lang.reflect.Constructor;

class Person {
    private Integer age;
    private String name;
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
    public static void main(String[] args) throws Exception {
        Class<Person> cls = Person.class;
        Constructor constructor = cls.getDeclaredConstructor(Integer.class,String.class);
        //动态破坏封装，仅在一次JVM进程中，可以取得私有
        constructor.setAccessible(true);
        Person per = (Person) constructor.newInstance(18,"me");
        System.out.println(per);

//        Class<Person> cls = Person.class;
//        //getConstructor只能取得权限为Public的构造方法
//        //Constructor[] constructors = cls.getConstructors();
//
//        //getDeclaredConstructors可以取得类中所有的构造方法，与权限无关
//        Constructor[] constructors = cls.getDeclaredConstructors();
//        for(Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
    }
}
