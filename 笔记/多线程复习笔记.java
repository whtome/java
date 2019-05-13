多线程
1.进程与线程
进程:OS中资源分配的最小单元
线程:进程中的独立任务,OS中任务调度的最小单元，依赖进程存在。
在一个进程中的线程共享资源。

2.Java中多线程实现
继承Thread类
实现Runnable
实现Callable
线程池-推荐

3.常用操作方法
sleep() : 不释放锁，立即交出CPU
yield() : 让步,交出CPU时间不确定，由系统调度决定，
只会让拥有相同优先级的线程有获取CPU的机会，不释放锁
join() : 线程等待,会释放对象锁
线程停止:
	-设置标志位
	-调用stop()
	-调用interrupt

用户线程与守护线程

4.线程同步与死锁

线程同步:
synchronized(内建锁) - 对象锁

使用:
同步代码块
synchronized(对象) {
	// 需要保护的代码块
}
同步方法
	-普通方法 : 锁定当前对象this
	public synchronized void test() {
		// 代码
	}
	-静态方法 : 锁定当前类.class

synchronized实现:对象的Monitor机制
可重入锁
JDK1.6之后内建锁的优化
CAS(Compare And Swap)
自适应自旋

偏向锁 - 默认 - 在任意时刻只有同一个线程在获取锁
轻量级锁 - 在不同时刻有不同线程在获取锁
重量级锁 - 在同一时刻有多个线程在尝试获取锁

锁粗化
锁消除

死锁:共享资源上锁成环
银行家算法

线程池
1.推荐使用线程池原因 : 3
2.线程池工作流程:
corePoolSize
BlockingQueue
MaxPoolSize
RejectPolicy

ExecutorService : 接口 普通调度池
...定时调度池

ThreadPoolExector

Executors : 内置四大线程池
单线程池
固定大小
缓存池
定时调度池






