package com.scaffold.simple.admin.other.jclass;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2021/1/29 15:05
 * @Eamil: 2695062879@qq.com
 */
public class AsmTest extends ClassLoader{

    public static void main(String[] args) throws Exception {
        List<String> a= Arrays.asList("12","213");
        System.out.println(a.get(-1));

        //生成一个类只需要ClassWriter组件即可
        ClassWriter cw = new ClassWriter(0);
        //通过visit方法确定类的头部信息
        String path="springboot.privateDemo.asm.helloworld.Comparable";
        String interfacePath="springboot.privateDemo.asm.helloworld.Mesurable";
        path.replace(".","/");
        interfacePath.replace(".","/");
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                path, null, "java/lang/Object",
                new String[] {interfacePath});
        // 定义类的属性
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "EQUAL", "I", null, new Integer(0)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC,
                "GREATER", "I", null, new Integer(1)).visitEnd();
        // 定义类的方法
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        //使cw类已经完成
        cw.visitEnd();
        //将cw转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        String savePath="D:\\vipkid\\private\\private\\src\\main\\java\\springboot\\privateDemo\\asm\\helloworld\\Generator.class";
//        savePath=savePath.replace(".","/");
//        savePath=savePath.concat("/Generator.class");
        File file = new File(savePath);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }
}
