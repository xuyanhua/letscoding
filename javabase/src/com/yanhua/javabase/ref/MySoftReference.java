package com.yanhua.javabase.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class MySoftReference {
    public static void main(String[] args) {
        String str = new String("abc");

        ReferenceQueue<String> referenceQueue = new ReferenceQueue();

        //只有内存不足时才回收
        SoftReference<String> soft = new SoftReference<>(str,referenceQueue);

        System.out.println(soft.get());

        Reference<? extends String> reference = referenceQueue.poll();
        System.out.println(reference); //null
    }
}
