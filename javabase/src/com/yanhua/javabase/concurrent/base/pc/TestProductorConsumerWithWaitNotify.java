package com.yanhua.javabase.concurrent.base.pc;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 用wait/notify实现生产者消费者
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/15 上午9:48
 */
public class TestProductorConsumerWithWaitNotify {
    private final List<Integer> list = new LinkedList<>();
    private final int MAX_SIZE = 10;
    private final Random r = new Random();

    public void product() throws InterruptedException {
        synchronized (list) {
            if (list.size() >= MAX_SIZE) {
                System.out.println("队列已满");
                list.wait();
            }
            int e = r.nextInt(10);
            System.out.println("生产者[" + Thread.currentThread() + "]：" + e);
            list.add(e);
            Thread.sleep(200);
            list.notify();
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (list) {
            if (list.size() <= 0) {
                System.out.println("队列已空");
                list.wait();
            }
            int e = list.remove(0);
            Thread.sleep(100);
            System.out.println("消费者[" + Thread.currentThread() + "]：" + e);
            list.notify();
        }

    }

    public static void main(String[] args) {
        final TestProductorConsumerWithWaitNotify pc = new TestProductorConsumerWithWaitNotify();
        Thread tProduct = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++)
                    pc.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread tConsumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++)
                    pc.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tProduct.start();
        tConsumer.start();
    }

}
