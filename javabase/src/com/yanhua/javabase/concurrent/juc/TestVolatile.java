package com.yanhua.javabase.concurrent.juc;

/**
 * 测试volatile关键字，volatile关键字有两个特性：
 * <ul>
 * <li>内存可见性</li>
 * <li>禁止指令重排</li>
 * </ul>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/15 下午2:14
 */
public class TestVolatile {

    public static void main(String[] args) {
        TestVolatile t = new TestVolatile();
//        t.testMemVisibilityUnsafe();
        t.testMemVisibilitySafe();
    }


    /**
     * 测试内存可见性-未添加volatile不安全
     */
    public void testMemVisibilityUnsafe() {
        //第一步，初始化对象
        ShutdownCtrolUnsafe sc = new ShutdownCtrolUnsafe();
        //模拟多个线程调用初始化方法
        //注意只有线程足够多时才能测出效果来
        for (int i = 0; i < 1000; i++) {
            Runnable r = () -> {
                sc.doWork();
            };
            new Thread(r).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sc.shutdown();
    }

    static class ShutdownCtrolUnsafe {
        private boolean endFlag = false;

        public void doWork() {
            while (!endFlag) {
                System.out.println(Thread.currentThread() + "-工作中..." + System.currentTimeMillis());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            endFlag = true;
            System.out.println(Thread.currentThread() + "------------------------工作完：" + System.currentTimeMillis());
        }
    }

    /**
     * 测试内存可见性-添加了volatile安全
     */
    public void testMemVisibilitySafe() {
        //第一步，初始化对象
        ShutdownCtrolSafe ic = new ShutdownCtrolSafe();
        //模拟多个线程调用初始化方法
        //注意只有线程足够多时才能测出效果来
        for (int i = 0; i < 1000; i++) {
            Runnable r = () -> {
                ic.doWork();
            };
            new Thread(r).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ic.shutdown();
    }

    static class ShutdownCtrolSafe {
        private volatile boolean endFlag = false;

        public void doWork() {
            while (!endFlag) {
                //如果结束后扔有打印，说明没有控制住
                System.out.println(Thread.currentThread() + "-工作中..." + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            endFlag = true;
            System.out.println(Thread.currentThread() + "----------------------工作完：" + System.currentTimeMillis());
        }
    }
}
