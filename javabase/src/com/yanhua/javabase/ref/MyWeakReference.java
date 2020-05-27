package com.yanhua.javabase.ref;

import java.lang.ref.WeakReference;

/*
只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，
不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
弱引用可以和一个引用队列(ReferenceQueue)联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。

简单测试：
 */
public class MyWeakReference {
    public static void main(String[] args) {
        String str = new String("abc");
        //如果一个对象是偶尔(很少)的使用，并且希望在使用时随时就能获取到，但又不想影响此对象的垃圾收集，那么你应该用Weak Reference来记住此对象。
        WeakReference<String> weakReference = new WeakReference<>(str);
        // 弱引用转强引用
//        String strongReference = weakReference.get();
        System.out.println(weakReference.get());
    }
}
