package com.yanhua.javabase.serial;

import java.io.*;

public class SerialTest {
    public static void main(String[] args) {
        try {
            Child0 p = new Child0();
            p.setName("xuyh");
            p.setStudent("student");
            OutputStream out = new FileOutputStream("./c");
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream in = new FileInputStream("./c");
            ObjectInputStream oin = new ObjectInputStream(in);
            Child0 c = (Child0) oin.readObject();
            System.out.println(c);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Parent0 implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Child0 extends Parent0 implements Serializable {
    private String student;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

}
