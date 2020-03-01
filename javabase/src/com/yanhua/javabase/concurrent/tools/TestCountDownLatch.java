package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.CountDownLatch;

/**
 * 测试闭锁。闭锁是一种同步工具，可能延迟线程的进度直到其到达终止状态。闭锁的作用相当于一扇门：<br/>
 * 在闭锁到达结束状态之前，这扇门一直关闭的，并且没有任何线程能通过，当到达结束状态时，这扇<br/>
 * 门会打开并允许所有的线程通过，可以用于：<br/>
 * <li>确保某个计算在其需要的所有资源都被初始化之后才继续执行。</li>
 * <li>确保某个服务在其依赖的所有服务都启动之后才启动</li>
 * <li>等待直到某个操作的所有参与者都就绪再继续执行</li>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/13 下午12:21
 */
public class TestCountDownLatch {

    /**
     * startGate可以控制所有线程一起开始执行
     * endGate可以控制，所有线程都执行完以后才返回结果
     *
     * @param nThreads 线程数
     * @return
     */
    public long timeTask(int nThreads) {
        //开启门栓
        final CountDownLatch startGate = new CountDownLatch(1);
        //结束门栓
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    //等待
                    try {
                        startGate.await();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //执行
                    System.out.println(Thread.currentThread() + "-执行");
                    endGate.countDown();
                }
            };
            new Thread(runnable).start();
        }
        System.out.println("一起执行->");
        long time1 = System.currentTimeMillis();
        startGate.countDown();//开始执行指令
        try {
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        long time = time2 - time1;
        System.out.println("执行完成->" + time + "ms");
        return time;
    }

    public static void main(String[] args) throws InterruptedException {
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        testCountDownLatch.timeTask(30);
    }
}
