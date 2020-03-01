package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.Exchanger;

/**
 * 测试Exchange，Exchang也是一种栅栏，它是一种两方栅栏，各方在栅栏上交换数据。<br/>
 * 当两方执行不对称的操作时，Exchange会非常有用，例如当一个线程向缓存区写入数据，而另<br/>
 * 一个线程从缓存区读取数据。这些线程可以使用Exchange来汇合，并将满的缓存区与空的缓存<br>
 * 区交换。
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/13 下午2:37
 */
public class TestExchanger {
    private Exchanger<String> exchanger = new Exchanger<String>();
    private Runnable worker1 = new Runnable() {
        public void run() {
            try {
                String current = "worker1";
                String s = Thread.currentThread().toString();
                System.out.println(current + "-before->" + s);
                Thread.sleep(1000);
                String exchange = exchanger.exchange(s);
                System.out.println(current + "-after->" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable worker2 = new Runnable() {
        public void run() {
            try {
                String current = "worker2";
                String s = Thread.currentThread().toString();
                System.out.println(current + "-before->" + s);
                Thread.sleep(1000);
                String exchange = exchanger.exchange(s);
                System.out.println(current + "-after->" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public void start() {
        for (int i = 0; i < 10; i++) {
            new Thread(worker1).start();
            new Thread(worker2).start();
        }
    }

    public static void main(String[] args) {
        TestExchanger testExchanger = new TestExchanger();
        testExchanger.start();
    }
}
