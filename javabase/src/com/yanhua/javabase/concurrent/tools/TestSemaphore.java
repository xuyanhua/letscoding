package com.yanhua.javabase.concurrent.tools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 测试信号量 Semaphore<br/>
 * 信号量用来控制同时访问某个资源的操作数量，或者同时执行指定操作的数量，<br/>
 * 计数信号量可以用来实现某种资源池，或者对容器施加边界<br/>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/13 下午1:57
 */
public class TestSemaphore {
    private final Set<String> pool;
    private final Semaphore sem;

    public TestSemaphore(int poolSize) {
        pool = Collections.synchronizedSet(new HashSet<String>());
        sem = new Semaphore(poolSize);
    }

    public boolean add(String s) throws InterruptedException {
        sem.acquire();//申请一个许可
        boolean isAdded = false;
        try {
            isAdded = pool.add(s);
            System.out.printf("添加：%s，结果为%s，当前池大小为%d，池内容为：%s\n", s, isAdded, pool.size(), pool.toString());
            return isAdded;
        } finally {
            if (!isAdded) {//释放一个许可
                sem.release();
            }
        }
    }

    public boolean remove(String s) {
        boolean isRemoved = false;
        try {
            isRemoved = pool.remove(s);
            System.out.printf("移除：%s，结果为%s，当前池大小为%d，池内容为：%s\n", s, isRemoved, pool.size(), pool.toString());
            return isRemoved;
        } finally {
            if (isRemoved) {
                sem.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final TestSemaphore testSemaphore = new TestSemaphore(3);
        new Thread(() -> {
            Random r = new Random();
            for (int i = 0; i < 100; i++) {
                try {
                    testSemaphore.add(r.nextInt(10) + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            Random r = new Random();
            for (int i = 0; i < 100; i++) {
                testSemaphore.remove(r.nextInt(10) + "");
            }
        }).start();

    }


}
