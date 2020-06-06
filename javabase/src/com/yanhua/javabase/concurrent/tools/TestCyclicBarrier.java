package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试栅栏。栅栏和闭锁Latch非常像，都可以阻塞一组线程直到某个事件发生。<br/>
 * 区别在于所有线程必须同时到达栅栏位置，才能继续执行。<br/>
 * 闭锁用于等待事件（CoundDown事件），而栅栏用于等待其他线程。<br/>
 * CyclicBarrier周期栅栏，顾名思义，它可以使一定数量的参与者"反复"地<br/>
 * 在栅栏位置汇集。当线程达到栅栏位置时将调用await方法，这个方法奖阻塞<br/>
 * 直到所有线程都到达栅栏的位置。<br/>
 * 如果所有的线程都到达栅栏的位置，那么栅栏将打开，此时所有线程都被释放，<br/>
 * 而栅栏将被重置以便下次使用。<br/>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/13 下午2:29
 */
public class TestCyclicBarrier {
    final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    private Runnable worker = () -> {
        try {
            System.out.println(Thread.currentThread() + "线程准备开始工作");
            Thread.sleep(100);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + "开始执行工作");
    };

    public void start(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            new Thread(worker).start();
        }
    }

    public static void main(String[] args) {
        TestCyclicBarrier testCyclicBarrier = new TestCyclicBarrier();
        testCyclicBarrier.start(10);
    }
}
