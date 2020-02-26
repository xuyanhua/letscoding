package com.yanhua.javabase.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionApp {
    public static void main(String[] args) {

        //正常的创建对象，调用方法
        SayHello sayHello = new SayHello();
        sayHello.sayHello("jack");
        try {
            //通过反射
            Class<?> sayHelloClass = Class.forName("com.yanhua.javabase.reflection.SayHello");
            //实例化对象
            Object newInstance = sayHelloClass.newInstance();
            //获取任意方法（包括私有方法，设置为可以访问）
            Method sayHello2 = sayHelloClass.getDeclaredMethod("sayHello2", String.class);
            sayHello2.setAccessible(true);
            //调用方法
            sayHello2.invoke(newInstance, "jack");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("java.ext.dirs"));
    }
}
