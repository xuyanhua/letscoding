package com.yanhua.javabase.classloader;

/**
 * 测试加载顺序
 */
public class TestLoadOrder {

    public static void main(String[] args) {
        new Child();
    }
}

class Father {
    public Father() {
        System.out.println("7.父类构造方法");
    }

    static {//1
        System.out.println("1.父类静态块");
    }

    {
        System.out.println("5.父类代码块");
    }


    public static int i = i();//2
    public int j = j();//6

    public static int i() {
        System.out.println("1.父类静态变量");
        return 0;
    }

    public int j() {
        System.out.println("2.父类成员变量");
        return 0;
    }
}

class Child extends Father {
    public static int i = i();     // 3
    public int j = j();     // 8

    public Child() {     // 10
        System.out.println("10.子类构造方法");
    }

    {
        System.out.println("9.子类代码块");
    }

    static {
        System.out.println("4.子类静态块");
    }

    public static int i() {
        System.out.println("3.子类静态变量");
        return 0;
    }

    public int j() {
        System.out.println("8.子类成员变量");
        return 0;
    }

}
