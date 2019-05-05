

4. lock体系

JDK1.5之后增加java.util.concurrent.locks提供了与内建锁完全不同的实现多线程共享资源访问机制。**失去了内建锁隐式的加锁与解锁过程，增加了可中断的获取锁以及超时获取锁以及共享锁等内建锁不具备的特性。**

lock锁的标准使用形式

```
Lock lock = new ReentrantLock();
try {
    lock.lock();
    //同步块
}finally {
    lock.unlock();
}
```



4.1  lock接口API

i. void lock(); //获取锁

ii. void lockInterruptibly()

​    throws InterruptedException;// 获取锁的过程能够响应中断（lock独有）

iii.  boolean tryLock(); //获取锁返回true，反之返回false。可以响应中断。

iiii.  boolean tryLock(long time , TimeUnit unit) : 在3的基础上增加了超时等待机制，规定时间内为获取到锁，线程直接返回（lock独有）

iiiii.  void unlock();



4.2 AbstractQueuedSynchronizer（同步器），lock体系最核心的存在

同步器是用来构建锁与其他同步组件的基础框架。它的实现主要是依赖一个int成员变量来表示同步状态以及通过一个FIFO队列构成同步队列。

要使用AQS，推荐使用静态内部类继承AQS，覆写AQS中的protected用来改变同步状态的方法，其他方法主要是实现排队与阻塞机制。状态更新使用getState() , setState() , compareAndSetState()。

**lock面向使用者，定义了使用者与锁交互的接口，隐藏了实现细节；**

**AQS面向锁的实现者，简化了锁的实现方式，屏蔽同步状态的管理，线程排队，线程等待与唤醒等底层操作**。



4.3 AQS提供的模版方法

独占锁：

1. void acquire(int arg)：独占是获取同步状态，如果获取失败则将当前线程插入同步队列进行等待。
2. void acquireInterruptibly(int arg)：在1的基础上增加响应中断
3. boolean tryAcquireNanos(int arg, long nanosTimeOut)：在2的基础上增加超时等待，在规定时间内未获取到同步状态返回false。
4. boolean tryAcquire(int arg)：获取状态成功返回true，否则返回flase
5. boolean release(int arg)：释放同步状态，该方法会唤醒在同步队列中的下一个节点。

共享锁：

AQS中的同步队列是一个带有头尾节点的双向链表，节点的组成为：

Node prev；

Node next；

Thread thread；

将线程封装为Node节点后进行入队与出队处理。



4.4  深入理解AQS

独占锁的获取 - acquire（int arg）

```
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

1. tryAcquire再次使用CAS尝试获取同步状态，若成功方法直接返回，当前线程为持有锁线程。若再次尝试失败，调用addWaiter()
2. addWaiter()源码 -- 将当前线程封装为Node节点后，尾插入同步队列

```
private Node addWaiter(Node mode) {
    //将当前线程以指定模式封装为Node节点
    Node node = new Node(Thread.currentThread(), mode);
    // 拿到当前队列的尾节点
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        //将当前节点使用CAS尾插入同步队列
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    //当前队列为空或CAS尾插失败是
    enq(node);
    return node;
}
```

3. enq() -- 当同步队列为空时完成队列初始化操作，以及不断CAS将当前节点尾插入同步队列

```
private Node enq(final Node node) {
    //死循环-不断自旋
    for (;;) {
        //拿到尾节点
        Node t = tail;
        //当前队列为空
        if (t == null) { // Must initialize
            //完成队列的初始化操作，lazy-load（懒加载模式）
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
            node.prev = t;
            //不断将当前节点使用CAS尾插入同步队列中直到成功为止
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}
```

4. accquireQueued()

```
final boolean acquireQueued(final Node node, int arg) {
    //设置失败状态，初始化为true
    boolean failed = true;
    try {
        //设置中断状态，默认为false
        boolean interrupted = false;
        //不断自旋
        for (;;) {
            //拿到当前节点前驱节点
            final Node p = node.predecessor();
            //当前节点前驱节点为头节点并且再次获取同步状态成功
            if (p == head && tryAcquire(arg)) {
                //将当前节点置为头节点
                setHead(node);
                //将前驱节点出队
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            //将当前节点置为取消状态，node.waitStutas = 1;
            cancelAcquire(node);
    }
}
```

节点从同步队列获取同步状态的前提条件：

只有当前驱节点为头节点是，线程才有集合获取同步状态

if (p == head && tryAcquire(arg))



5. 当前线程获取同步状态失败是，首先调用shouldParkAfterFailedAcquire(p, node)，尝试将前驱节点状态改为Node.SIGNAL,表示此时当前节点应该被阻塞

```
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    //获取前驱节点状态
    int ws = pred.waitStatus;
    if (ws == Node.SIGNAL)
        //表示应该将当前节点阻塞
        return true;
    //前驱节点取消
    if (ws > 0) {
        //一直向前找到前驱节点不是取消状态的节点
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    } else {
        //将前驱节点状态置为signal，-1
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}
```



节点状态值如下：

int INITIAL = 0;          //初始状态

int CANCELLED = 1;  //当前节点从同步状态中取消

int SIGNAL = -1;         //后继节点处于阻塞（WAIT）状态，如果当前节点是否同步状态会

int CONDITION = -2;  //节点处于等待队列中，当其他线程对Condition调用了signal()方法后，该节点将会从等待队列中转移到同步队列中，加入到对同步状态的获取中。

int PROPAGATE = -3;  //共享式同步状态会无条件的传播下去



独占式锁的释放 - release()

```
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        //获取到当前同步队列的头结点
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```

unparkSuccessor() ：唤醒距离头节点最近的一个非空节点

```
private void unparkSuccessor(Node node) {
    int ws = node.waitStatus;
    if (ws < 0)
        compareAndSetWaitStatus(node, ws, 0);
    Node s = node.next;
    if (s == null || s.waitStatus > 0) {
        s = null;
        //当头结点的下一个节点为空时
        //从同步队列尾部开始一直向前找到距离头节点最近的一个非空节点
        for (Node t = tail; t != null && t != node; t = t.prev)
            if (t.waitStatus <= 0)
                s = t;
    }
    if (s != null)
        LockSupport.unpark(s.thread);
}
```



独占锁特性学习

1.1 可中断获取锁

```
void lockInterruptibly() throws InterruptedException;
```

最终会调用AQS acquireInterruptibly（int arg）模版方法

```
public final void acquireInterruptibly(int arg)
        throws InterruptedException {
    //增加了对中断状态的判断，如果检查线程中断状态改变，抛出中断异常后方法直接退出
    if (Thread.interrupted())
        throw new InterruptedException();
    if (!tryAcquire(arg))
        doAcquireInterruptibly(arg);
}
```

doAcquireInterruptibly(arg)

```
private void doAcquireInterruptibly(int arg)
    throws InterruptedException {
    final Node node = addWaiter(Node.EXCLUSIVE);
    boolean failed = true;
    try {
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                //线程被阻塞时若检查到中断抛出中断异常后退出
                throw new InterruptedException();
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```



1.2 超时等待获取锁（在中断获取锁的基础上增加超时功能）

```
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
```

该方法本质调用AQS的 tryAcquireNanos(int arg, long nanosTimeout)

```
public final boolean tryAcquireNanos(int arg, long nanosTimeout)
        throws InterruptedException {
    if (Thread.interrupted())
        throw new InterruptedException();
    return tryAcquire(arg) ||
        doAcquireNanos(arg, nanosTimeout);
}
```



```
private boolean doAcquireNanos(int arg, long nanosTimeout)
        throws InterruptedException {
     //传入时间小于0，方法直接退出，线程获取锁失败
    if (nanosTimeout <= 0L)
        return false;
     //根据超出时间算出截至时间
    final long deadline = System.nanoTime() + nanosTimeout;
    final Node node = addWaiter(Node.EXCLUSIVE);
    boolean failed = true;
    try {
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return true;
            }
            //再次计算截至时间-当前时间值
            nanosTimeout = deadline - System.nanoTime();
            //已超出，线程直接退出
            if (nanosTimeout <= 0L)
                return false;
            if (shouldParkAfterFailedAcquire(p, node) &&
                nanosTimeout > spinForTimeoutThreshold)
                //在超时时间内仍未被唤醒，线程退出
                LockSupport.parkNanos(this, nanosTimeout);
            if (Thread.interrupted())
                throw new InterruptedException();
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

AQS源码：

1. 独占锁的获取（acquire）与释放（release）
2. 独占锁的特性：

响应中断lockInterruptibly() - acquireInterruptibly()

超时获取锁tryLock(long time , TimeUnit unit) - doAcquireNanos(arg , nanosTimeout)



2. ReentrantLock（Lock中使用频率最高的一个类） -  可重入锁

内键锁隐式支持重入性，sychronized通过获取自增，释放自减的方式实现重入。

2.1 重入性实现原理

重入性锁的特点：

i. 线程获取锁的时候，如果已经获取锁的线程是当前线程直接再次获取。

ii. 由于锁会被获取N次，因此锁只有被是否N此之后才算真正是否成功。

```
final boolean nonfairTryAcquire(int acquires) {
    //拿到当前线程
    final Thread current = Thread.currentThread();
    //获取当前同步状态
    int c = getState();
    if (c == 0) {
        //当前同步状态还未被获取
        //当前线程使用CAS尝试获取同步状态
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    //此时同步状态不为0，表示已经有线程获取到了同步状态
    //判断持有线程是否是当前线程
    else if (current == getExclusiveOwnerThread()) {
        //若是当前线程，同步状态再次+1
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        //将再次+1后的状态写会内存
        setState(nextc);
        return true;
    }
    return false;
}
```


2.2 公平锁与非公平锁

锁的获取顺序一定满足时间上的绝对顺序，等待时间最长的线程一定最先获取到锁。

ReentrantLock默认使用非公平锁



非公平锁：nonFairSync

```
final void lock() {
    不再队列中的线程可能会直接获取锁
    if (compareAndSetState(0, 1))
        setExclusiveOwnerThread(Thread.currentThread());
    else
        acquire(1);
}
```

```
final boolean nonfairTryAcquire(int acquires) {
    if (c == 0) {
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
}
```



公平锁：FairSync

```
final void lock() {
    //少了一次CAS过程
    acquire(1);
}
```

```
protected final boolean tryAcquire(int acquires) {
    if (c == 0) {
        //增加了!hasQueuedPredecessors()
        //当同步队列中存在非空节点，当前线程直接封装为Node节点排队
        if (!hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
}
```

对比：

公平锁保证每次获取锁均为同步队列的第一个节点，保证了请求资源时间上的绝对顺序，但是效率低，需要频繁的进行上下文切换。

非公平锁会降低性能开销，降低一定的上下文切换，但是可能导致其他线程永远无法获取到锁，造成线程“饥饿”现象。

通常来讲，没有特定的公平性要求尽量选择非公平锁（ReentrantLock默认选择）



3. ReentrantReadWriterLock

读写者模型：

读写锁允许同一时刻被多个线程访问，但是在写线程访问是，所有的读线程以及其他线程均会被阻塞。

3.1 写锁操作 - 独占锁

写锁的获取 - tryAcquire（）

```
protected final boolean tryAcquire(int acquires) {
    Thread current = Thread.currentThread();
    //获取读写锁状态
    int c = getState();
    //获取独占式锁状态 - 即写锁状态
    int w = exclusiveCount(c);
    if (c != 0) {
        //表示当前有读线程拿到读锁，写线程无法获取同步状态
        if (w == 0 || current != getExclusiveOwnerThread())
            return false;
        //写锁的可重入次数已达最大值
        if (w + exclusiveCount(acquires) > MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        //写锁可重入
        // Reentrant acquire
        setState(c + acquires);
        return true;
    }
    if (writerShouldBlock() ||
        !compareAndSetState(c, c + acquires))
        return false;
    //此时读写状态为0，写锁可以正常获取到同步状态
    //将当前线程置为只有写锁线程
    setExclusiveOwnerThread(current);
    return true;
}
```

如何区分读状态与写状态：

同步状态的高16位表示读锁获取次数；

同步状态低16位表示写锁获取次数。



3.2 读锁 - 共享锁（一般与独占锁搭配使用实现读写者模型）

读锁获取：

只要当前没有写线程获取到写锁并且读锁的获取次数不超过最大值，读锁就能获取成功。



读写锁的应用场景：缓存的实现



3.3 写锁的降级：

写锁可以降级为读锁，但是读锁不能升级为写锁



4. Condition的await与signal   等待/通知机制

Object的wait与notify是与内建锁（对象监视器）搭配使用，完成线程的等待与通知机制。

Condition的await，signal是与Lock体系配合实现线程的等待与通知，Java语言层面实现，具有更高的控制与扩展性。

Condition有一下三个独有特性：

a. Condition await支持不响应中断，而Object wait只有一个等待队列

b. Condition支持多个等待队列，而Object wait只有一个等待队列

c. Condition支持设置截至时间，而Object wait只支持设置超时时间



等待方法await

i. void await() throws InterruptedException；（同wait()）

ii. void awaitUninterruptibly()；  特性1，等待过程中不响应中断

iii. boolean await(long time, TimeUnit unit) throws InterruptedException；

在i的基础上增加了超时等待功能，可以自定义时间单位。

iiii. boolean awaitUntil(Date deadline) throws InterruptedException;

特性3，支持设置截至时间



唤醒方法signal，signalAll()



4.1 Condition等待队列

Condition对立与AQS中的同步队列共享节点（Node）数据结构

带有头尾指针的单向队列

每当调用lock.newCondition()就会在绑定的lock锁上新增一个等待队列。（特性2）

实现场景：使用Condition实现有界队列



4.2 await实现原理

```
public final void await() throws InterruptedException {
    //判断中断
    if (Thread.interrupted())
        throw new InterruptedException();
    //将当前线程封装为Node节点后入等待队列
    Node node = addConditionWaiter();
    //释放拿到的同步状态
    int savedState = fullyRelease(node);
    int interruptMode = 0;
    while (!isOnSyncQueue(node)) {  
        //当线程不在同步队列后将其阻塞，置为WAIT状态
        LockSupport.park(this);
        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
            break;
    }
    //在同步队列中排队获取锁
    if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
        interruptMode = REINTERRUPT;
    if (node.nextWaiter != null) // clean up if cancelled
        unlinkCancelledWaiters();
    if (interruptMode != 0)
        reportInterruptAfterWait(interruptMode);
}
```

如何将当前线程插入等待队列  addConditionWaiter();

```
private Node addConditionWaiter() {
    Node t = lastWaiter;
    // If lastWaiter is cancelled, clean out.
    if (t != null && t.waitStatus != Node.CONDITION) {
        //清空所有等待队列中状态不为Condition的节点
        unlinkCancelledWaiters();
        //将最新的尾节点赋值
        t = lastWaiter;
    }
    //将当前线程包装为Node节点且状态为Condition
    Node node = new Node(Thread.currentThread(), Node.CONDITION);
    if (t == null)
        firstWaiter = node;
    else
        t.nextWaiter = node;
    //尾插入等待队列
    lastWaiter = node;
    return node;
}
```

将线程包装为Node节点尾插入等待队列后，线程释放锁过程fullyRelease()

```
final int fullyRelease(Node node) {
    boolean failed = true;
    try {
        //获取当前同步状态
        int savedState = getState();
        //调用release方法释放同步状态
        if (release(savedState)) {
            failed = false;
            return savedState;
        } else {
            throw new IllegalMonitorStateException();
        }
    } finally {
        //若在释放过程中出现异常，将当前节点取消
        if (failed)
            node.waitStatus = Node.CANCELLED;
    }
}
```

线程如何能从await()方法中退出?

    while (!isOnSyncQueue(node)) {  
        //阻塞在此处
        LockSupport.park(this);
        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
            break;
    }
i. 在等待时被中断，通过break退出循环。

ii. 被唤醒后置入同步队列，退出循环。



4.4  signal实现原理

```
public final void signal() {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    //拿到当前等待队列的头节点
    Node first = firstWaiter;
    if (first != null)
        //唤醒头结点
        doSignal(first);
}
```

```
private void doSignal(Node first) {
    do {
        if ( (firstWaiter = first.nextWaiter) == null)
            lastWaiter = null;
        //将头节点从等待队列中移除
        first.nextWaiter = null;
      //transferForSignal()方法对头结点做
    } while (!transferForSignal(first) &&
             (first = firstWaiter) != null);
}
```



5. LockSupport

在之前介绍AQS的底层实现，以及在介绍线程间等待/通知机制使用的Condition时都会调用LockSupport.park()方法和LockSupport.unpark()方法。而这个在同步组件的实现中被频繁使用的LockSupport到底是何方神圣，现在就来看看。

LockSupport位于java.util.concurrent.locks包下。
LockSupprot是线程的阻塞原语，用来阻塞线程和唤醒线程。每个使用LockSupport的线程都会与一个许可关联，如果该许可可用，并且可在线程中使用，则调用park()将会立即返回，否则可能阻塞。如果许可尚不可用，则可以调用unpark 使其可用。但是注意许可不可重入，也就是说只能调用一次park()方法，否则会一直阻塞。



阻塞线程：

1. void park()：阻塞当前线程，如果调用unpark方法或者当前线程被中断，从能从park()方法中返回
2. void park(Object blocker)：功能同方法1，入参增加一个Object对象，用来记录导致线程阻塞的阻塞对象，方便进行问题排查；
3. void parkNanos(long nanos)：阻塞当前线程，最长不超过nanos纳秒，增加了超时返回的特性；
4. void parkNanos(Object blocker, long nanos)：功能同方法3，入参增加一个Object对象，用来记录导致线程阻塞的阻塞对象，方便进行问题排查；
5. void parkUntil(long deadline)：阻塞当前线程，知道deadline；
6. void parkUntil(Object blocker, long deadline)：功能同方法5，入参增加一个Object对象，用来记录导致线程阻塞的阻塞对象，方便进行问题排查；



唤醒线程：

void unpark(Thread thread):唤醒处于阻塞状态的指定线程



与内建锁的阻塞唤醒区别：

内建锁阻塞进入BLOCKED

LockSupport.park()进入WAIT

唤醒：

内建锁有JVM随机挑选一个线程唤醒

LockSupport.unPark(Thread thread)唤醒指定线程



6. 死锁

死锁产生原因：对共享资源的上锁成环

死锁解决算法：银行家算法