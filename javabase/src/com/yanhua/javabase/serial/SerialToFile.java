package com.yanhua.javabase.serial;

import java.io.*;

/**
 * java序列化到文件
 */
public class SerialToFile {
    public static void main(String[] args) throws IOException {
        serialToFile();
        Person p = unSerialFromFile();
        System.out.println(p);
    }

    /**
     * 反序列化
     *
     * @return
     */
    static Person unSerialFromFile() {
        try {
            InputStream in = new FileInputStream("./p");
            ObjectInputStream oin = new ObjectInputStream(in);
            return (Person) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 序列化
     */
    static void serialToFile() {
        try {
            Person p = new Person();
            p.setName("xuyh");
            p.setAge(10);
            OutputStream out = new FileOutputStream("./p");
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Serializable {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
