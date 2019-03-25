throws:用在方法声明上，明确告诉调用者本方法可能产生的异常，但方法本身不处理，将异常抛回给调用方。

受查异常:强制用户进行异常处理(要么try/catch要么再给上层抛出)

非受查异常:Error、RuntimeException及其子类，不强制用户进行异常处理。
(NullPointerException、ClassCastException、ArrayOutofBoundsException)

throw:用在方法体代码中，表示人为进行异常的抛出。
如果希望自己产生异常类对象而非由JVM产生，就可以在代码块中使用throw来抛出异常(一般与分支语句搭配使用来抛出自定义异常)


自定义异常:用户可以继承Exception或RuntimeException来实现自定义异常

断言assert

assert 布尔表达式 : "出错时语句";

默认不启用，要启用断言 使用参数-ea

IDEA插件:
1.背景插件:Background Image Plus: view-
2.花括号:Rainbow Brackets