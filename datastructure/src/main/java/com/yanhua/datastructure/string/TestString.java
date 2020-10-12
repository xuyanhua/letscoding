package com.yanhua.datastructure.string;

public class TestString {
    public static void main(String[] args) {
        String a = "hello world";
        String b = "hello world";
        System.out.println(a == b);//true
        String c = new String("hello world");
        System.out.println(c==a);//false
    }
}
