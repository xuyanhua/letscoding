package com.yanhua.javabase.concurrent.base;

/**
 * 测试join方法，是Thread的成员方法。意思是让调用join方法的线程执行完成。再去干别的
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/15 上午11:18
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread child = new Thread(() -> {
            System.out.println("子线程正在运行...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程运行完成...");
        });


        child.start();
        child.join();//使调用join的线程执行完成后（Waits for this thread to die），再执行
        System.out.println("主线程正在运行...");

    }

}
