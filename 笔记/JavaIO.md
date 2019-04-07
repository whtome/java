JavaIO - BIO(阻塞式IO)  基于抽象类

java.io



核心掌握五个类（File，OutputStream，InputStream，Reader , Writer）+一个接口(Serializable)



1.File文件操作类  - 即可以描述具体文件也可以描述文件夹

   File类是唯一一个与文件本身操作（创建，删除，取得信息）有关的程序类

  产生File对象：

 public  File(String pathname) ： 根据文件的绝对路径来产生file对象

 public  File(URI uri) : 根据网络产生File对象

  1.1  常用操作方法

   创建新文件：public boolean creatNewFile() throws IOException

   判断文件是否存在：public boolean exists()

   删除文件：public boolean delate()

   文件分隔符：File.separator

   1.2  目录操作

  取得父路径的File对象：public File getParentFile()

  取得父路径：public String getParent()

  创建多级父路径(一次性创建多级不存在的父路径)：public boolean mkdirs()

  mkdir()只创建当前目录

   1.3 取得文件信息

   判断File对象是否是文件：public boolean isFile()

   判断File对象是否是文件夹  :  public boolean isDirectory()

   取得文件大小（字节为单位）：public long length()

   取得文件上次修改时间（字节为单位）：public long lastModified()

   列出一个目录的全部组成：public File[] listFiles()



2.字节与字符流

java.io包流分为两类：输入流与输出流。

字节流(byte)是原生操作，无需转换，可以处理文本文件，图像，音乐，视频等资源：

InputStream  ,   OutputStream

字符流(char)是经过处理后的操作，只能处理中文文本：

Reader  ,   Writer

流模型的操作流程：

a， 取得终端对象

b， 根据终端对象取得输入输出流

c， 根据输入输出流进行数据读取与写入

d， 关闭流

IO操作属于资源处理，所有的资源处理（IO操作，数据库操作，网络操作）在使用后一定要关闭



2.1 字节输出流 OutputStream

```
public abstract class OutputStream implements Closeable, Flushable 
```

核心方法

```
public void write(byte b[]) throws IOException ：将给定的字节数组全部输出
public void write(byte b[], int off, int len) throws IOException：将给定的字节数组以off位置开始输出len长度后停止输出
public abstract void write(int b) throws IOException：输出单个字节
```

使用 OutputStream输出数据时，若指定文件不存在，FileOutStream会自动创建文件（不包含创建目录）

使用FileOutputStream输出内容是，默认是文件内容的覆盖操作

若要进行文件内容的追加，使用如下的构造方法：

```
public FileOutputStream(File file, boolean append)
```

JDK1.7追加了AutoCloseable自动关闭接口，要使用此接口，必须使用try-catch块,推荐显式关闭

```
try(Message msg = new Message())  { // 必须在try中定义对象
msg.print();
} catch (Exception e) {
}
```



2.2 字节输入流 InputStream

```
public abstract class InputStream implements Closeable
```

```
public int read(byte b[]) throws IOException：将读取的内容放入自己数组中
```

返回值有如下三种情况：

a , 返回b.length ：未读取的数据>存放的缓冲区大小，返回字节数组大小

b. 返回大于0的整数，此整数小于b.length：未读取的数据<存放的缓冲区大小，返回剩余数据大小

c.  返回-1（终止标记）：此时数据已经读取完毕，返回-1



2.3 字符输出流Writer--适用于处理中文文本

```
public void write(String str) throws IOException：字符流可以直接支持字符串的输出
```

字符流若为关闭，数据在缓冲区存放，不会输出到目标终端。要想将输出流关闭，要么将输出流关闭要么使用flush()强制刷新缓冲区。



2.4 字符输入流 Reader

```
public int read(char cbuf[]) throws IOException
```



3.转换流（字节流->字符流）

OutputStreamWriter(字节输出流->字符输出流)

InputStreamReader(字节输入流->字符输入流)

字符流的具体子类大都是通过转换流将字节流转换为字符流（FileWriter继承转换流）



综合演练：文件拷贝



4.字符编码--就使用UTF-8

GBK,GB2312：GBK包含简体与繁体中文，GB2312只包含简体中文。

UNICODE：java提供的16进制编码，可以描述世界上任何语言，但编码进制数太高，编码体积较大

ISO-8859-1：国际通用编码，不支持中文，浏览器默认编码

UTF-8：结合UNICODE与ISO-8859-1，最常采用的是UTF-8

乱码产生原因（95%）：编码解码不一致

由于数据丢失造成的乱码（5%）

读取java运行属性：System.getProperties().list(System.out)



5.内存流（以内存为终端的输入输出流）

字节内存流：ByteArrayInputStream . ByteArrayOutputStream

字符内存流：CharArrayReader ,  CharArrayWriter

将指定的字节数组内容存放到内存中

```
public ByteArrayInputStream(byte buf[])
```

```
public ByteArrayOutputStream()
```



6.打印流（输出流的强化版本）

字节打印流：PrintStream

字符打印流：PrintWriter

打印流的设计模式属于装饰设计模式--基于抽象类

特点：核心依然是某个类（OutputStream提供的write()）的功能，但是为了得到更好的操作效果，让其支持的功能多一些，使用装饰类（PrintStream）

优点：很容易更换装饰类来达到不同的操作效果

缺点：造成类结构复杂



7.System类对IO的支持

标准输出（显示屏）：System.out

标准输入（键盘）:System.in

错误输出：System.err



7.1  系统输出

系统提供的out（输出到显示器，颜色为黑色），err（输出到显示器，颜色为红色）对象均是PrintStream的对象

以后输出采用日志（Log）——格式化输出



7.2 系统输入

System.in 是InputStream的直接对象



8.两种输入流

8.1 BufferedReader，BufferedInputStream

readline() 直接读取一行输入，默认以回车换行



8.2 java.util.Scanner

支持正则表达式

yyyy-MM-dd

\d{4}-\d{2}-\d{2}

public boolean hasNextxx()；判断是否有指定类型输入

public  数据结构  nextxx()：获取指定类型数据

public Scanner useDelimiter("指定分隔符")：定义分隔符



最主要的三个类：File，PrintStream，Scanner