Socket编程



服务器端



客户端

客户端连接服务器是需要指定服务器的IP（标识电脑）与端口号（标识电脑上的具体某个应用）



ServerSocket：基站类。服务器Socket类

public ServerSocket（int port）：在本机根据指定端口好创建服务器

public Socket accept（）：侦听并接收到本服务的客户端连接。此方法会一直阻塞，直到有一个客户端成功连接，返回此连接。



Socket：客户端类

public Socket（String host，int port）：根据指定ip和端口号创建套接字并连接到远程服务器端。

public InputStream getInputStream（）：返回此套接字的输入流

public OutputStream getOutputStream（）：返回此套接字的输出流









