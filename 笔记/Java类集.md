Java类集（考点：数据结构-多线程）-java.util.*

—动态数组（当元素个数达到最大值是，动态增加容量）



考点：

- List接口：
- 1  ArrayList与Vector区别
- 2   ArrayList线程不安全的List集合，是否了解JUC包下的线程安全List（CopyOnWriteArrayList）
- 
- Set接口：
- 1  Set集合与Map集合的关系
- 2   hashCode，equals方法关系
- 3   Comparable，Comparator接口的关系
- 
- Map：
- 1   请对比HashMap，Hashtable关系
- 2   是否了解ConcurrentHashMap以及实现原理



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



3. Set接口：不允许数据重复（Set接口就是Value值相同的Map集合，先有Map才有Set）

Set接口没有扩充方法

Set接口常用子类：

HashSet(无序存储)：-HashMap

1 .底层使用哈希表+红黑树

2 .允许存放null，无序存储



TreeSet-TreeMap（有序存储）：Comparable ， Comparator

1.底层使用红黑树

2.不允许出现空值，有序存储

3.自定义类要想保存到TreeSet中，要么实现Comparable接口，要么向TreeSet传人比较器（Comparator接口）



Comparable接口与Comparator接口的区别：

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



Comparator（外部排序接口）

若要自定义某个自定义类的顺序，而该类本身不支持排序（类本身没有实现Comparable）。我们可以建立一个该类的“比较器”来进行排序。比较器实现Comparator接口即可。

“比较器”：实现了Compartor接口的类作为比较器，通过该比较器来进行类的排序。

int compare(T o1, T o2);

返回值与compareTo返回值完全一样

返回正数：表示o1 >o2

返回0：表示 o1 = o2

返回负数：表示o1 < o2

实现了Comparator接口进行第三方排序--策略模式，此方法更加灵活，可以轻松改变策略进行第三方的排序算法



Comparable接口与Compartor接口的关系：

Comparable是排序接口，若一个类实现了Comparable接口，意味着该类支持排序，是一个内部比较器（自己去和别人比）

Comparator接口是比较器接口，类本身不支持排序，专门有若干个第三方的比较器（实现了Comparator接口的类）来进行类的排序，是一个外部比较器（策略模式）



重复元素的判断：

TreeSet与TreeMap依靠Compartor后Comparable接口来区分重复元素。

自定义类要想保存在TreeSet或TreeMap中:

a. 要么该类直接实现Comparable接口，覆写compareTo方法

b. 要么实现一个比较器传人TreeSet或TreeMap来进行外部比较

而HashSet与HashMap并不依赖比较接口。此时想要区分自定义元素是否重复，需要同时覆写equals与hashCode方法。

首先覆写equals()方法来判定两个元素内容是否相等

覆写equals方法原则：

a.  自反性：对于任何非空引用值x , x.equals(x)都返回true

b.  对称性： 对于任何非空的x，y，当且仅当x.equals(y)返回true , y.equals(x)也返回true

c.  传递性：对于任何非空的x，y，z，如果x.equals(y)返回true，y.equals(z)返回true，一定有x.equals(z)返回true

d.  一致性：对于任何非空的x，y，若x与y中属性没有改变，则多次调用x.equals(y)始终返回true或false

e.  非空性：对于任何非空引用x，x.equals(null)一定返回false

先调用hashCode计算出对象hash码决定存放的数据桶，而后使用equals来比较元素是否相等，若相等，则不再放置元素；若equals返回flase，则在相同桶之后，使用链表将若干元素链起来



Object类提供的hashCode方法默认使用对象的地址进行hash.



若两个对象的equals方法返回true，他们的hashCode必然要保证相等。但是两个对象的hashCode相等，equals不一定相等。

当且仅当equals与hashCode方法均返回true，才认为两个对象真正相等。



4. 集合输出（迭代器输出） -  Iterator接口

迭代器：为了遍历集合而生。-- 迭代器模式

Iterator接口两个核心方法;

boolean hasNext();   判断是否还有元素

E  next();  取得下一个元素



4.1 迭代输出Iterator--只能从前向后输出（Collection接口提供）

调用Collection集合子类的Iterator方法取得内置的迭代器，使用一下输出格式

```
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```



4.2 双向迭代接口ListIterator--List接口支持，Set不支持

除了hasNext与next方法外

hasPrevious()：判断是否有上一个元素

previous()：取得上一个元素

要想使用从后往前遍历，首先至少要从前往后遍历一次才可使用。



4.3 Enumeration（JDK1.0）枚举输出--Vector类支持

hasMoreElements()：判断是否有下一个元素

nextElements()：取得下一个元素



4.4  for-each输出（所有子类都可输出）

能使用foreach输出的本质在于各个集合类都内置了迭代器



fail-fast机制：

ConcurrentModificationException发生在Collection集合使用迭代器遍历时，使用了集合类提供的修改集合内容方法报错。而如果使用Iterator迭代器的remove()不会出现此错误

```
final void checkForComodification() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
}
```

Collection集合中的modCount表示当前集合修改的次数

expectedModCount是迭代器中记录当前集合的修改次数

当取得集合迭代器是（即调用list.iterator()）,expectedModCount = modCount，换言之，迭代器就是当前集合的一个副本

快速失败策略保证了所有用户在进行迭代遍历集合时，拿到的数据一定是最新的数据（避免了“脏读”产生）



fail - safe：不产生ConcurrentModificationException异常

juc包下的所有线程安全集合（CopyOnWriteArrayList）



总结：以后在迭代器遍历是，不要修改集合内容



5. Map集合

Map接口是Java中保存二元偶对象（键值对）的最顶层接口

```
public interface Map<K,V>
```

key值唯一，通过一个key值一定能唯一找到一个value值

Map接口下核心方法：

```
public V put(K key, V value) : 向Map中添加数据

public V get(K key) : 根据指定的key值取得相应的value值，若没有此key值，返回null

public Set<Map.Entry<K,V>> entrySet() : 将Map集合变为Set集合

public Set<K> keySet() : 返回所有key值集合，key不能重复

public Collection<V> values() : 返回所有value值，value可以重复

```

Map接口中有如下四个常用子类：

HashMap（使用频率最高-必考），TreeMap，Hashtable，ConcurrentHashMap

Map接口的使用



5.1 HashMap - 类比HashSet:

a.  允许key和value为null，且key值有且只有一个为null，value可以有任意多个为null

b.  JDK1.2产生

c.  异步处理，效率高，线程不安全

d.  底层使用哈希表+红黑树（JDK8）



5.2 Hashtable（古老类）：

a.  key与value均不为null

b.  JDK1.0

c.  使用方法上加锁，效率低，线程安全

d.  底层哈希表



5.3  Map集合使用迭代器（Iterator）输出

public Set<Map.Entry<K,V>> entrySet() : 将Map集合变为Set集合

```
//1.Map->Set
Set<Map.Entry<Integer,String>> set = map.entrySet();
//2.取得Set接口迭代器
Iterator<Map.Entry<Integer,String>> iterator = set.iterator();
```



6. 栈与队列

栈：先入后出

函数栈帧，浏览器标签页的后退，编辑器撤销

Stack类:

入栈：push()

出栈：pop()

返回栈顶元素但不出栈：peek()

自己实现一个html识别器



队列FIFO：先入先出

消息队列：kafka，RobitMQ

Queue接口：-- 子类LinkedList

```
Queue<Integer> queue = new LinkedList<>();
```

入队列：add()

出队列：poll()

返回队列头元素，不出队：peek()



7. 资源文件操作（Properties属性文件）

资源文件内容都是k-v格式，并且key，value都是String类型

设置属性：setProperty(String key, String value)

取得属性：getProperty(String key)：String 若没有指定key，返回null

​                  getProperty(String key, String defaultValue) : 若没有指定key值，返回默认值

将资源内容输入输出到目标终端：store（OuptStream out，String comments）

从目标终端中读取数据：load（InputStream in）



8. Collections工具类

8.1   将线程不安全集合包装为线程安全集合（不推荐）

在add，remove等修改方法中使用同步代码块保证线程安全，效率较低。要使用线程安全集合，推荐使用juc包下的并发集合类（ConcurrentHashMap，CopyOnWriteArrayList）

8.2   集合排序

Collections.sort(集合名称)

8.3   集合反转

Collections.reverse（集合名称）



9. Stream数据流（JDK8新增）：Collectionjiekou

核心方法：取得Stream流

Stream<E> stream()

常用方法;

forEach : 集合输出

filter：数据过滤

取得最大最小值：max/min



Map/Reduce模型

map()：数据前期处理

double allPrice = orderList.stream().map((obj) -> obj.getPrice() *obj.getAmount()).reduce((sum, x) -> sum + x).get();

reduce()：数据处理后的收集操作



