package com;

import java.io.*;
import java.util.Scanner;

class PrintUtil {
    private OutputStream out;
    public PrintUtil(OutputStream out) {
        this.out = out;
    }
    public void print(String str) {
        try {
            //实际上调用输出流的write方法
            this.out.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void println(String str) {
        this.print(str+("\r\n"));
    }
    public void print(int data) {
        this.print(String.valueOf(data));
    }
    public void println(int data) {
        this.print(data+("\r\n"));
    }
    public void print(double data) {
        this.print(String.valueOf(data));
    }
    public void println(double data) {
        this.print(data+("\r\n"));
    }
    public void close() {
        try {
            this.out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class text2 {
    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年龄");
        scanner.useDelimiter("\n");
        if(scanner.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("年龄为："+scanner.next());
        }

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("请输入内容...");
//        //只能使用回车作为分割符
//        String result = bufferedReader.readLine();
//        System.out.println("输入内容为："+result);


//        //1.取得File对象
//        File file = new File("C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop"+File.separator+"testIO.txt");
//        //2.取得输出流
////        PrintUtil print = new PrintUtil(new FileOutputStream(file));
//        //系统提供的打印流
//        PrintStream print = new PrintStream(new FileOutputStream(file));
//        //3.数据输出
//        print.print(10);
//        print.println("haha");
//        print.print(12.3);
//        //4.关闭流
//        print.close();



        //内存流操作
//        String str = "hello world";
//        //1.取得终端对象
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        //2.数据的读取与写入
//        int len = 0;
//        while ((len = byteArrayInputStream.read()) != -1) {
//            byteArrayOutputStream.write(Character.toUpperCase(len));
//        }
//        //直接输出内存内容
//        System.out.println(byteArrayOutputStream);
//        //3.关闭流
//        byteArrayInputStream.close();
//        byteArrayOutputStream.close();

    }
}
