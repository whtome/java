一、String类
1.其他操作方法
trim():去掉字符串左右空格

toUpperCase():将字符串转为全大写
toLowerCase():将字符串转为全小写

length():返回字符串长度
isEmpty():返回是否是空字符串,不包含null的情况

判断空字符串:
if(str == null || str.isEmpty()) {}

2.两个sb(StringBuffer,StringBuilder)类

由于字符串常量不可变更，为了方便进行字符串内容的修改，引入了两个SB类。

在字符串中使用"+"进行字符串内容的拼接操作，这种拼接操作会产生大量垃圾空间。

引入两个SB类后，字符串拼接操作改为调用append(各种数据类型):StringBuffer

String与SB的相互转换

String -> StringBuffer

I.调用StringBuffer的构造方法
II.调用append()

StringBuffer -> String
I.调用toString()

public synchronized StringBuffer append(Object obj)
public StringBuilder append(Object obj)

面试题:请解释String类与StringBuffer、StringBuilder的区别
I.字符串常量不可变更，而StringBuffer与StringBuilder内容可以修改(append).String变量在使用"+"进行字符串拼接时，javac编译器会将String变量变为StringBuilder而后进行append处理。

II.StringBuffer采用同步处理，线程安全，性能较低;
StringBuilder采用异步处理，线程不安全，性能较高。

字符串反转reverse();

内容删除delete(int start,int end);

插入数据insert(int offset,插入内容);

二、Object类
Object类是JDK默认提供的一个类，所有类默认继承Object类。
Object类是所有类的父类，使用Object可以接收所有类的对象。

2.1取得对象信息toString()
使用系统输出输出对象时，默认输出的是一个地址编码。而使用系统输出输出String常量时，输出的是字符串内容。本质原因就在于Object类提供的toString()有没有被类所覆写。

系统输出(print/println)输出对象时，默认调用对象的toString()

*****2.2对象比较equals() *****

"=="比较的是值是否相等(对于基本数据类型而言，比较的就是存放的数据大小;对于引用类型而言，比较的是存放的地址是否相等)

要进行两个引用类型内容比较，使用equals(),必须覆写equlas()

public boolean equals(Object obj) {
	
}

2.3 Object除了是所有类的父类外，Object类还可以接收数组与接口。
Object可以接收所有引用数据类型

三、包装类
包装类就是将基本数据类型封装到类中。

Java中的包装类
数值型包装类(Number类的子类):NumberFormatException
Byte、Double、Short、Long、Float、Integer(int)

对象型包装类(Object类的直接子类)
Boolean、Character(char)

3.1装箱与拆箱
装箱:将基本数据类型变为包装类对象，利用每个包装类提供的构造方法实现装箱处理。

拆箱:将包装类中包装的基本数据类型的值取出。

JDK1.5之后提供了自动拆装箱机制,使用包装类对象就和使用基本数据类型一模一样

对于Integer var = ?(自动装箱) ?在-128-127之间的赋值，Integer对象在Integer常量池产生，会复用已有对象。在这个区间外的所有数据在堆上产生，不会复用已有对象。

判断两个包装类对象是否相等,使用equals方法比较

到底选择包装类还是基本类型?
1.强制要求:所有POJO类(自己定义的Java类)的属性均使用包装类
2.推荐:所有局部变量使用基本类型

3.2 字符串与包装类型数据类型的转换

String -> 基本类型

使用包装类提供的parseXXX方法
eg: Integer.parseInt("123");
NumberFormatException产生原因:
存在非数字的字符串转为数值类型
eg:Integer.parseInt("123a");

以后在进行字符串与数值转换时，首先判断字符串是否由纯数字组成。

基本类型->String
I.""+基本类型
II.调用String.valueOf(各种数据类型)
