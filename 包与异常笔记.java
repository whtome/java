一、包的定义与使用

1.1 包(package)的定义
以后在进入源文件时先定义包名称。

Java包的本质就是一个文件夹,避免类名重复的问题。

在源文件首行使用package定义包名

编译时加上-d参数
javac -d 源文件所在路径-绝对路径(当前路径 .) 源文件名称
javac -d . Test.java

运行时使用类的全名称(包名.类名)
java www.bit.java.Test

2.包的导入
使用import语句进行包的导入

自动编译:编译器会将当前路径下所有*.java源文件按照使用顺序进行一次性的编译。
javac -d . ./*.java

3.jar包的概念

jar包实际上就是所有class文件的压缩包

cmd切换到当前路径的两种方式
1. shift+右键

二、访问控制权限
private(私有访问权限，仅供本类使用)<default(包访问权限)<protected(继承访问权限)<public(公共访问权限)

2.1包访问权限default
在一个包中的所有源文件均可以访问

2.2继承访问权限protected
仅供子类使用

三、单例模式:类的对象有且只有一个

首先控制对象的产生数量-将构造方法私有化(从源头控制对象数量)

类构造方法私有化:任何其他类均无法产生此类对象

唯一的一个对象产生于类内部

唯一的属性为静态属性，并且类中提供静态方法取得此对象(类的外部无法产生对象，因此无法调用对象方法)

// 饿汉式单例
class Singleton {
    // 类中提供了一个对象
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    // 静态方法
    public static Singleton getInstance() {
        return singleton;
    }
}
// 懒汉式单例
class Singleton {
    // 类加载时产生
    private static Singleton singleton;
    private Singleton(){}
    // 静态方法
    public static Singleton getInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}

饿汉:上来就new(就写饿汉式)
懒汉:用时再new(存在线程安全问题,在多线程并发下可能会产生不止一个对象)


三个核心组成:
构造方法私有化
类内部提供静态私有对象
类内部提供静态方法返回唯一对象

四、Java异常

4.1 异常类体系

Error类描述Java运行时内部错误或资源耗尽错误(OOM,StackOverFlowError)。

Exception:

RuntimeException:程序出错(ClassCastException,NullPointerException,NumberFormatException,ArrayIndexOutofBoundsException)

IOException:程序本身没有问题，I/O异常(打开一个不存在的文件)

受查异常:所有其他异常,必须强制用户进行异常处理

非受查异常:所有Error以及RuntimeException直接子类,不强制进行异常处理。

4.2异常处理格式
try {
	// 可能出现异常的代码
}[catch.....]{
	// 出现异常后咋办
}[finally] {
	// 异常出口
}

try-catch(可以有多个catch)
try-finally
try-catch(可以有多个catch)-finally

finally代码块无论如何都会执行(若try或catch块中有return语句，则在return语句执行之前一定会执行finally代码块)
