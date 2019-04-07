package com;


import java.io.*;

public class text1 {
    public static void main(String[] args) throws Exception {
        //源文件路径
        String sourceFilePath = "C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop"+File.separator+"qq.JPG";
        //目标文件路径
        String destFilePath = "C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop"+File.separator+"qq2.JPG";
        copyFile(sourceFilePath,destFilePath);
    }
    public static void copyFile(String sourceFilePath,String destFilePath) throws Exception {

        //1.取得源文件与目标文件的File对象
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        //2.取得输入输出流
        InputStream in = new FileInputStream(sourceFile);
        OutputStream out = new FileOutputStream(destFile);
        //3.数据输入输出
        int len = 0;
        byte[] data = new byte[1024];
        long start = System.currentTimeMillis();
        while ((len = in.read(data)) != -1) {
            out.write(data,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时"+(end - start));

    }
}
