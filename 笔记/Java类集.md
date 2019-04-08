Java类集（考点：数据结构-多线程）-java.util.*

—动态数组（当元素个数达到最大值是，动态增加容量）



1.  java类集产生-JDK1.2

动态数组：解决数组定长问题

Collection接口--单个对象保存的最顶层父接口

Collection接口以及其子接口，在每次进行数据操作是只能够对单个对象进行处理

```
public interface Collection<E> extends Iterable<E> 

Iterable<E>:迭代器接口（就是为了遍历集合）
Iterator<E> iterator()；（取得集合的迭代器，JDK1.5之前直接写在Collection接口中）
```

Collection接口中提供的核心方法：

add(T t)：向类集中添加元素

iterator()：取得类集的迭代器

Collection接口只定义了存储数据的标准，但是无法区分存储类型。因此在实际中我们往往使用两个子接口List（允许数据重复），Set（不运行数据重复）。一般不使用Collection接口。



2. List接口（80%）-- 允许数据重复

在进行单个集合处理是，优先考虑List接口

在List接口中，拓展了两个重要方法（List接口独有）

```
public E get (int index): 根据索引下标取得数据
public E set (int index,E element) : 根据索引下标更新数据，返回修改前的数据
```

List接口有三个重要子类 : ArrayList （90%）, Vector , LinkedList 

这三个子类在使用上没有任何区别，

List接口要想保存自定义类的对象，该类必须覆写equals()来使用contains() , remove()等方法



 **ArrayList , Vector , LinkedList 的区别**

 ArrayList  , Vector的区别：

1.1 出现版本：

ArrayList   JDK1.2

Vector   JDK1.0（出现在List ， Collection接口之前）

1.2  初始化策略区别：

Vector在无参构造执行后将对象数组大小初始化为10

ArrayList采用懒加载策略，在构造方法阶段并不初始化对象数组，在第一次添加元素是才初始化对象数组大小为10

1.3   扩容策略：

ArrayList扩容时，新数组大小变为原数组的1.5倍

Vector扩容时，新数组大小变为原数组的2倍

1.4  线程安全性：

ArrayList采用异步处理，线程不安全，效率较高

Vector采用在方法上加锁，线程安全，效率较低（即便要使用线程安全的List，也不用Vector）

1.5  遍历：

Vector支持较老的迭代器Enumeration，

ArrayList不支持。



ArrayList ， Vector共同点：

底层均使用数组实现



ArrayList与LinkedList区别：

LinkedList底层采用双向链表实现，ArrayList底层使用数组实现



3. Set接口：（不允许数据重复）

Set接口没有扩充方法

Set接口常用子类：

HashSet(无序存储)：

1 .底层使用哈希表+红黑树

2 .允许存放null，无序存储



TreeSet（有序存储）：Comperable ， Comperator

1.底层使用红黑树

2.不允许出现空值，有序存储

3.自定义类要想保存到TreeSet中，要么实现Comparable接口，要么向TreeSet传人比较器（Compartor接口）



Comparable接口与Compartor接口的区别：

在java中，若向实现自定义类的比较，提供了以下两个接口：



java.lang.Comparable接口（内部比较器）：

若一个类实现了Comparable接口，就意味着该类支持排序。

存放该类的Collection或数组，可以直接通过Collections.sort()或Array.sort进行排序。

实现了Comparable接口的类可以直接存放在TreeSet或TreeMap中。

public int compareTo (T o)

返回值三种情况：

返回正数：表示当前对象大于目标对象

返回0：表示当前对象等于目标对象

返回负数：表示当前对象小于目标对象

