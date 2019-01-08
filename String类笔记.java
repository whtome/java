接口:
接口优先
接口:
1.全局常量与抽象方法(JDK8)
2.接口只有public权限
public static final abstact
3.抽象类可以实现(implements)若干个接口,
接口不能继承抽象类
4.接口可以使用extends继承多个父接口

接口应用场景:
定义标准USB接口
表示行为、能力(购买商品)

工厂模式-第三方(工厂类)

1.简单工厂
组成:
一个抽象产品类/接口
多个具体的产品类
一个工厂类(所有产品都由这个工厂类来生产)


2.工厂方法-产品呈现出家族式特征
组成:
一个抽象产品类/接口
多个具体的产品类
一个抽象工厂
多个具体工厂

代理模式-第三方(代理类)
两个子类共同实现一个接口，其中一个子类负责真实业务实现，另外一个子类完成辅助操作。

3.String类详解

3.1 实例化方式

3.1.1 直接赋值(用到最多)
String str = "hello";

3.1.2 通过构造方法赋值
String str = new String("hello");

3.2 字符串相等比较

"=="比较的两个操作数的值，对于基本类型而言比较的就是值的大小，对于引用类型而言，比较的是两者指向的堆内存地址是否相同。

比较字符串内容是否相等时，使用equals()

3.3 字符串常量是String类的匿名对象

字符串常量:使用""括起来的内容字符串常量。所有字符串 常量都是String的匿名对象。

在进行指定字符串内容与字符串对象比较时，把字符串常量写在equals前，避免NullPointerException

3.4 两种实例化模式的区别
推荐使用直接赋值的方式实例化String对象

String类采用共享设计模式:
JVM底层会维护一个字符串常量池(字符串数组)，若采用直接赋值的形式进行String类的实例化操作。那么该对象会自动保存在对象池中。如果下次即系采用直接赋值的形式，首先在对象池中查看是否有指定内容，若有，直接引用。若没有，产生新的对象后入池。

采用构造方法:

1.采用构造方法实例化的String对象不会入池。
手工入池:intern()

2.使用构造方法实例化String对象时会产生两块内存空间，其中一块为垃圾空间。

3.5字符串不可变更

字符串常量不可变。

字符串的+操作不要出现太多次数,会产生大量垃圾空间。


3.6 字符与字符串String""<->char''
字符串就是一个字符数组

I.将char[]->String
调用String的构造方法实现
public String(char value[]):将字符数组所有内容转为String
public String(char value[],int offset,int count):
将字符数组部分转为字符串。

II.String -> char
public char charAt(int index):取得字符串指定索引的字符
public char[] toCharArray();

******判断一个字符串是否全部由数字组成?*****
public static boolean isAllNumber(String str) {
        // 1.将String转为char[]
        char[] data = str.toCharArray();
        for(int i = 0;i < data.length;i++) {
            // 取出每个字符
            char c = data[i];
            if(c < '0' || c > '9') {
                return false;
            }
        }
       return true; 
    }

3.7 字符串与字节的转换 String <-> byte

I.字节数组 -> 字符串
构造方法

II.String -> byte
public byte[] getBytes(); 将字符串全部转为字节数组

public byte[] getBytes(String charSetName):将字符串以指定编码转为字节数组

3.8 字符串比较
boolean equals():区分大小写比较
boolean equalsIgnoreCase():不区分大小写比较
int compareTo(String anotherString):当碰到第一个不相等的字符时，终止比较，返回两个字符的ASCII差值。
>0 : 表示当前字符串 > 目标字符串
=0 : 表示当前字符串等于目标字符串
<0 : 表示当前字符串 < 目标字符串

3.9******字符串查找******
从完整的字符串中判断指定内容是否存在

public boolean contains(String str) : 判断一个子字符串是否存在

public int indexOf(String str) : 从头开始查找子字符串的位置，若找到返回开始索引，否则返回-1

public int lastIndexOf(String str) : 从后向前查找指定字符串，若找到返回开始索引，否则返回-1

public boolean startsWith(String prefix) :
判断是否以指定字符串开头

public boolean endsWith(String suffix) :
判断是否以指定字符串结尾

3.10字符串替换
public String replaceAll(String regex,String replacment):
将regex内容的字符串全部替换为replacment

public String replaceFirst(String regex,String replacment) :
替换首个内容

3.11字符串拆分
将一个完整的字符串按照指定的格式拆分为若干个子字符串
public String[] split(String regex) :
将字符串全部拆分

public String[] split(String regex,int limit):
将字符串按照指定格式拆分为limit个子字符串

若拆分结果为空，指定字符串内容需要转义\\

3.12字符串截取[)
public String substring(int beginIndex) : 从指定索引位置开始截取到字符串结尾

public String substring(int beginIndex,int endIndex) :
截取部分内容

首字母大写处理
public static String upperCaseFirstChar(String str) {
        return str.substring(0,1).toUpperCase() +
            str.substring(1);
    }

    
