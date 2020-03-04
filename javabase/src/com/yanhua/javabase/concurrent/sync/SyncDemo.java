package com.yanhua.javabase.concurrent.sync;

public class SyncDemo  {
    private Integer count = new Integer(0);

    //获取方法区常量池中的ACC_SYNCHRONIZED访问标志有没有设置。
    public synchronized void add1() {
        for (int i = 0; i < 10; i++) {
            count = count + 1;
        }
    }

    public void add2() {
        //同步语句块是通过monitorenter和monitorexit两个指令实现的
        synchronized (count) {
            for (int i = 0; i < 10; i++) {
                count = count + 1;
            }
        }
    }

    public Integer getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncDemo syncDemo = new SyncDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    syncDemo.add1();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(syncDemo.getCount());

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    syncDemo.add2();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(syncDemo.getCount());
    }
}
