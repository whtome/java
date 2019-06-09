堆
数据结构:堆(完全二叉树-二叉堆)
优先级队列:以堆为底层结构

优先级队列:按照优先级大小先入先出
经典问题:
如何在O(nlogn)时间内找到100w个元素中的前100个元素

操作系统:堆 底层可以使用链表
JVM堆 : 存放所有类的对象以及数组对象

堆:完全二叉树
只有完全二叉树可以用数组表示(顺序存储)
完全二叉树中任何一个节点的值不大于其父节点的值(最大堆 或 大顶堆)

堆中层数越高，值一定越大吗?不一定
但是在最大堆中，根节点的值一定是堆的最大值

堆是用数组存储时，下标为0存储第一个元素
若当前节点的下标为x,
则左孩子下标 2x+1
则右孩子下标 2x+2
其父节点的数组下标 (x-1)/2

添加元素
add(E e) : 默认在数组末尾添加元素，此时添加元素之后可能会破坏堆的定义

siftUp 元素上浮(判断当前元素与父节点的元素大小)

取出堆的最大值extractMax()
将最大值删除:
	-将堆顶元素与堆的最后一个元素交换
	-在完全二叉树中判断一个节点为叶子节点:左孩子的节点下标 > 当前二叉树节点个数

类的对象大小比较

外部比较器Comparator : 策略模式
int compare(T o1, T o2);
> 0
= 0
< 0

内部比较器Comparable 类必须实现此接口
public int compareTo(T o);
> 0
= 0 相等
< 0

class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

/**
 * 基于姓名的Person比较器
 */
class PersonCompare implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

/**
 * 基于年龄的Person比较器
 */
class PersonAgeCompare implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}

public class HeapTest {
    public static void main(String[] args) {
        Person per = new Person(20,"张三");
        Person per1 = new Person(21,"王麻子");
        PersonCompare personCompare = new PersonCompare();
        PersonAgeCompare ageCompare = new PersonAgeCompare();
        System.out.println(personCompare.compare(per,per1));
        System.out.println(ageCompare.compare(per,per1));
    }
}



