package com.yanhua.javabase.concurrent.base.pc;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量Semaphore实现生产者消费者
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/15 上午10:16
 */
public class TestProductConsumerWithSemaphore {
    private final List<Integer> list = new LinkedList<>();
    private final Random r = new Random();
    private final Semaphore sem = new Semaphore(10);

    public void product() throws InterruptedException {
        sem.acquire();
        boolean add = false;
        try {
            int e = r.nextInt(10);
            System.out.println("生产者[" + Thread.currentThread() + "]：" + e);
            add = list.add(e);
            Thread.sleep(100);
        } finally {
            if (!add) {
                sem.release();
            }
        }
    }

    public void consumer() throws InterruptedException {
        boolean remove = false;
        try {
            int e = list.remove(0);
            Thread.sleep(200);
            System.out.println("消费者[" + Thread.currentThread() + "]：" + e);
            remove = true;
        } catch (Exception e) {
            System.out.println("没有空闲资源资源不存在");
        } finally {
            if (remove) {
                sem.release();
            }
        }
    }

    public static void main(String[] args) {
        final TestProductConsumerWithSemaphore pc = new TestProductConsumerWithSemaphore();
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
