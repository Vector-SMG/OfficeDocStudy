package com.cornucopia.kotlin.start.idioms.javaresource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 传统的io资源关闭方式
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/31
 *
 * 参考:https://www.cnblogs.com/itZhy/p/7636615.html
 */
public class CloseIOOfJava {

    public static void main(String[] args) {
        normal();
    }

    /**
     * 普通的方式
     * 缺陷：必须手动关闭外部资源
     */
    private static void normal() {
        FileInputStream inputStream=null;
        try {
            inputStream=new FileInputStream(new File("test"));
            System.out.println(inputStream.read());
        } catch (IOException e) {
             throw new RuntimeException(e.getMessage(),e);
        }finally {
            if(inputStream!=null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    throw new RuntimeException(e.getMessage(),e);
                }
            }
        }
    }

    /**
     * jdk7,`try-with-resource`语法
     * try-with-resource并不是JVM虚拟机的新增功能，只是JDK实现了一个语法糖
     * public static void main(String[] args) {
     *     try {
     *         FileInputStream inputStream = new FileInputStream(new File("test"));
     *         Throwable var2 = null;
     *
     *         try {
     *             System.out.println(inputStream.read());
     *         } catch (Throwable var12) {
     *             var2 = var12;
     *             throw var12;
     *         } finally {
     *             if (inputStream != null) {
     *                 if (var2 != null) {
     *                     try {
     *                         inputStream.close();
     *                     } catch (Throwable var11) {
     *                         var2.addSuppressed(var11);
     *                     }
     *                 } else {
     *                     inputStream.close();
     *                 }
     *             }
     *
     *         }
     *
     *     } catch (IOException var14) {
     *         throw new RuntimeException(var14.getMessage(), var14);
     *     }
     * }
     */
    private static void jdk7(){
        try(FileInputStream inputStream=new FileInputStream(new File("test"))){
            System.out.println(inputStream.read());
        }catch (IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
