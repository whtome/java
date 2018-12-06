class Person{
    public void print(){
        System.out.println("[PRINT]方法:"+this);  //this表示当前对象
    }
}
public class TextThis{
    public static void main(String[] args) {
        Person p1 = new Person();
        System.out.println("[MAIN]方法:"+p1);
        p1.print();
        System.out.println("======================");
        Person p2 = new Person();
        System.out.println("[MAIN]方法:"+p2);
        p2.print();
    }
}


// class Person{
//     private String name;
//     private int age;
//     public Person(){
//         System.out.println("*****产生一个新的Person类对象*****");
//     }    
//     public Person(String name){
//         System.out.println("*****产生一个新的Person类对象*****");   //this();调用本类无参构造
//         this.name = name;
//     }   
//     public Person(String name, int age){
//         System.out.println("*****产生一个新的Person类对象*****");   
//         this.name = name;                                           //this(name);调用本类有参构造
//         this.age = age;
//     }   
//     public String getPersonInfo(){
//         return "姓名:"+name+",年龄:"+age;
//     }
// }
// public class TextThis{
//     public static void main(String[] args){
//         Person per1 = new Person();
//         Person per2 = new Person("张三");
//         Person per3 = new Person("李四",20);
//         System.out.println(per1.getPersonInfo());
//         System.out.println(per2.getPersonInfo());       
//         System.out.println(per3.getPersonInfo());
//     }



// class Person{
//     private String name;
//     private int age;
//     public Person(String name, int age){
//         this.name = name;
//         this.age = age;
//     }
//     public String getPersonInfo(){
//         return "姓名:"+name+",年龄:"+age;
//     }
// }
// public class TextThis{
//     public static void main(String[] args) {
//         Person per = new Person("张三",20);
//         System.out.println(per.getPersonInfo());
//     }
// }