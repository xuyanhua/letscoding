package com.yanhua.javabase.ref;

import java.util.ArrayList;
import java.util.List;

public class StrongReferemce {
    public static void main(String[] args) {
        test();
    }

    /***
     * 一般的引用都是强引用。
     * 如果是方法内部的变量，方法执行完后，即可以清理
     * 如果是全局的变量，则需要手动置空以后才能被回收
     */
    public static void test() {
        List<String> list = new ArrayList();
    }
}
