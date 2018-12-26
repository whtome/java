内部类:
内部类与外部类可以互相访问彼此私有域
内部类可以实现多继承的概念

成员内部类-普通方法
定义在类中 不加static修饰符
能访问外部类的静态域，不能拥有静态域

静态内部类-静态方法
定义在类中，加static修饰符
能拥有普通域，但不能访问外部类普通域

方法内部类(局部内部类)-局部变量
定义在方法中
I.不能加任何权限修饰符 public private protected
II.仅能在本方法中使用
III.要使用方法形参，该形参必须是final(JDK8隐式final声明)



抽象类与接口

以后尽量不要直接继承实现好的类，而要继承抽象类或者实现接口。
普通类无法强制要求子类覆写方法，因此产生抽象类与接口。

1.抽象类的定义与使用。

抽象类就是比普通类多了一些抽象方法(0..N)而已。(抽象类是普通类的超集)。
抽象类使用abstract关键字定义。
抽象类不能直接产生实例化对象(抽象类是个半成品，无法直接使用)

抽象方法:使用abstract关键字定义并且没有方法体的方法。抽象方法所在的类一定是抽象类。
public abstract void print();

本地方法(调用C或者其他语言同名方法):public native void test();

2.抽象类的使用原则

I.抽象类必须有子类(abstract 与 final 不能同时出现)
II.抽象类的子类(不是抽象类)必须覆写抽象类的所有抽象方法.
III.抽象类可以使用子类向上转型为其实例化。抽象类一定不能直接实例化对象(无论是否有抽象方法)
IV.由于抽象类强制要求子类覆写抽象方法，因此abstract与private不能一起使用。

3.抽象类的相关规定

3.1 *****
抽象类也存在构造方法，并且子类也一定按照实例化流程，先调用抽象类的构造方法而后再调用子类构造方法。

3.2 
抽象类可以不定义任何抽象方法，但此时仍然无法直接实例化对象。

3.3
final与abstract不能同时出现;
private与abstract也不能同时出现

3.4
抽象类也分为外部抽象类与内部抽象类。

内部抽象类的抽象方法与外部抽象类的抽象方法无关。

当前直接继承哪个抽象类，就覆写其抽象方法。(若直接继承外部抽象类，则只需要覆写外部抽象类的所有抽象方法即可)

4.模板设计模式-加盟店

学习设计模式方法:找到第三方

开闭原则(OCP):一个软件实体如类、函数、接口应该对扩展开放、对修改关闭。

模板模式(模板方法模式):
模板方法定义了一个算法(封装算法)的步骤，并允许子类为一个或多个步骤提供具体实现。

第三方:模板类(模板方法)

abstract class CaffineBeverage {
    // 核心算法
    public final void prepare() {
        boilWater();
        // 冲泡流程在父类中未知
        // 此操作延迟到子类实现
        brew();
        pourInCup();
        // 加哪种调料依然未知
        if(shouldAddCondiments()) {
            addCondiments();
        }
    }
    public final void boilWater() {
        System.out.println("将水烧开");
    }
    public final void pourInCup() {
        System.out.println("将饮料倒入杯中");
    }
    public abstract void brew();
    public abstract void addCondiments();

    // 钩子函数
    // 子类可以选择性覆写此方法
    public boolean shouldAddCondiments() {
        return true;
    }
}