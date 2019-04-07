package com;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class text {
    public static void main(String[] args) throws Exception{


        //1.取得File对象
        File file = new File("C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop"+File.separator+"testIO.txt");

        //转换流
        //2.取得字符输出流
        Writer out = new OutputStreamWriter(new FileOutputStream(file));
        //3.数据输出
        out.write("你好比特111");
        //4.关闭流
        out.close();


//        //2.取得字符输入流
//        Reader reader = new FileReader(file);
//        //3.数据输入
//        char[] data = new char[1024];
//        int len = reader.read(data);
//        System.out.println("文件内容为："+new String(data,0,len));
//        //4.关闭流
//        reader.close();


//        //2.取得字符输出流
//        Writer writer = new FileWriter(file);
//        //3.数据输出
//        writer.write("你好比特");
//        //4.关闭流
//        writer.close();


//        //InputStream操作
//        //2  取得相应输入流
//        InputStream in = new FileInputStream(file);
//        //3  进行数据的读取
//        byte[] data = new byte[1024];
//        int len = in.read(data);
//        System.out.println(new String(data,0,len));
//        //4  关闭流
//        in.close();


//        //OutputStreamd操作
//        //2.取得指定文件的输出流
//        OutputStream out = new FileOutputStream(file,true);
//        //3.进行数据的输出
//        String str = "hello world";
//        out.write(str.getBytes(),6,5);
//        //4.关闭流
//        out.close();


        //将IO操作放在子线程中进行
//        long start = System.currentTimeMillis();
//        listAllFiles(file);
//        long end = System.currentTimeMillis();
//        System.out.println("文件遍历时间"+(end-start));

//        if (file.exists() && file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (File file1 : files) {
//                System.out.println(file1);
//            }
//        }
    }

//    public static void listAllFiles(File file) {
//        if(file.isFile()) {
//            System.out.println(file);
//        }else {
//            if(file.exists() && file.isDirectory()) {
//                File[] files = file.listFiles();
//                for(File file1 : files) {
//                    listAllFiles(file1);
//                }
//
//            }
//        }
//    }

//        c.文件信息操作
//        System.out.println(file.isFile());
//        System.out.println(file.getParentFile().isDirectory());

//        System.out.println(file.length());
//        Date date = new Date(file.lastModified());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(simpleDateFormat.format(date));
//
//        //表示文件确实存在
//        if(file.exists() && file.isFile()) {}



        //b.目录操作
        //2.判断父路径是否存在，不存在创建多级父目录
//        if(!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        //3.判断文件是否存在，不存在则创建文件
//        if(file.exists()) {
//            System.out.println("文件已存在");
//        }else{
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//        a.文件操作
//        2.判断是否存在
//        if(file.exists()) {
//            file.delete();
//            System.out.println(file.getParent());
//        }else {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


}
