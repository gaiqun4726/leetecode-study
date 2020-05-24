package com.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author gaiqun
 * @date 2020/5/6
 *
 * 读较多的情形使用乐观锁；写较多的情形使用悲观锁，否则乐观锁反而损耗性能
 */
public class CasBasic {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        // 先返回再增加。内部使用乐观锁，不断自旋直到可以增加。
        System.out.println(i.getAndIncrement());
        System.out.println(i.incrementAndGet());
        System.out.println(i.addAndGet(3));
        System.out.println(i.getAndAdd(3));
        // 与expected一致则修改，否则什么也不做。返回布尔值
        System.out.println(i.compareAndSet(7,10));
        System.out.println(i.compareAndSet(8,10));
        System.out.println(i.get());

        System.out.println("-----AtomicStampReference-----");
        String str1 = "sss";
        String str2 = "ssy";
        AtomicStampedReference<String> ref = new AtomicStampedReference<>(str1, 1);
        System.out.println(ref.attemptStamp(str2, 0));
        System.out.println(ref.compareAndSet(str1, str2, 1, 2));
        System.out.println(ref.getReference());
        System.out.println(ref.getStamp());
    }
}
