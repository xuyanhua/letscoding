package com.yanhua.javabase.concurrent.juc;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试原子操作
 * <p>
 * <ul>
 * 对一个数值自增，有多种方法
 * <li>1、直接自增，不安全</li>
 * <li>2、使用synchronized控制自增，安全，性能不好</li>
 * <li>3、使用Lock控制自增，安全，性能不好</li>
 * <li>4、使用AtomicInteger自增，安全，性能较好</li>
 * <li>5、使用LongAdder自增，安全，性能非常好</li>
 * </ul>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/15 下午4:12
 */
public class TestAtomic {
    private int num = 0;
    private Integer numSyn = 0;
    private int numLock = 0;
    private AtomicInteger numAtomic = new AtomicInteger(0);
    private LongAdder num5 = new LongAdder();
    private CountDownLatch c = new CountDownLatch(10000);
    private Lock lock = new ReentrantLock();

    public void testIncWithNone() throws InterruptedException {
        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                num++;
                c.countDown();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(r1);
            t.start();
        }
        c.await();
        Assert.assertEquals(10000, num);


    }

    public synchronized void testIncWithSync() throws InterruptedException {
        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (this) {
                    numSyn++;
                    c.countDown();
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(r1);
            t.start();
        }
        c.await();
        Assert.assertEquals(10000, numSyn.intValue());
    }


    public synchronized void testIncWithLock() throws InterruptedException {
        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                numLock++;
                c.countDown();
                lock.unlock();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(r1);
            t.start();
        }
        c.await();
        Assert.assertEquals(10000, numLock);
    }

    public void testIncWithAtomic() throws InterruptedException {
        long time1 = System.currentTimeMillis();
        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                numAtomic.incrementAndGet();
                c.countDown();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(r1);
            t.start();
        }
        c.await();
        Assert.assertEquals(10000, numAtomic.intValue());
        long time2 = System.currentTimeMillis();
        System.out.println("原子测试耗时：" + (time2 - time1) + "ms");

    }

    public void testIncWithJdk8Adder() throws InterruptedException {
        long time1 = System.currentTimeMillis();
        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                num5.increment();
                c.countDown();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(r1);
            t.start();
        }
        c.await();
        Assert.assertEquals(10000, num5.intValue());
        long time2 = System.currentTimeMillis();
        System.out.println("Addr测试耗时：" + (time2 - time1) + "ms");
    }


    public static void main(String[] args) throws InterruptedException {
        final TestAtomic testAtomic = new TestAtomic();
        //测试一般情况
//        testAtomic.testIncWithNone();
        //测试同步情况
//        testAtomic.testIncWithSync();
//        testAtomic.testIncWithLock();
        //测试原子操作
//        testAtomic.testIncWithAtomic();
        //
        testAtomic.testIncWithJdk8Adder();
    }

}
