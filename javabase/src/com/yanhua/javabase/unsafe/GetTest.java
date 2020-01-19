package com.yanhua.javabase.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class GetTest {
    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
