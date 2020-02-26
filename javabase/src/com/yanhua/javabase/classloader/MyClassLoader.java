package com.yanhua.javabase.classloader;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {
    private String path;
    private String package0;

    public MyClassLoader(String path, String package0) {
        this.path = path;
        this.package0 = package0;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String path = "/Users/xuyanhua/IdeaProjects/letscoding/javabase/target/classes/com/yanhua/javabase/classloader/";
        String package0 = "com.yanhua.javabase.classloader";
        Class<?> personClazz = new MyClassLoader(path, package0).findClass("Person");
        System.out.println(personClazz);
        Constructor con = personClazz.getConstructor(String.class);
        Object instance = con.newInstance("xuyh");
        Method howOldAreYouMethod = personClazz.getDeclaredMethod("howOldAreYou");
        howOldAreYouMethod.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = readClassData(name);
        String fullName = package0 + "." + name;
        return defineClass(fullName, bytes, 0, bytes.length);
    }

    private byte[] readClassData(String name) {
        String path = this.path + name + ".class";
        InputStream in = null;
        ByteOutputStream out = null;
        try {
            in = new FileInputStream(path);
            out = new ByteOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
            byte[] bytes = out.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
