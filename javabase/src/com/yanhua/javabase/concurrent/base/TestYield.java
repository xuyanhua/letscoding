package com.yanhua.javabase.concurrent.base;

/**
 * 测试yield()方法。它会暗示调度器，我（当前线程）会放弃自己本次使用处理器的机会，
 * 当然调度器也可以选择忽略这个暗示
 * A hint to the scheduler that the current thread is willing to yield
 * its current use of a processor. The scheduler is free to ignore this
 * hint.
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/15 上午11:45
 */
public class TestYield {
    public static void main(String[] args) {

        Thread low = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                System.out.println("low....不要紧的线程，调用yield谦让别人先运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }

        });
        Thread high = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                System.out.println("high....重要的线程，希望先运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        low.setPriority(1);
        high.setPriority(10);
        low.start();
        high.start();


    }
}
