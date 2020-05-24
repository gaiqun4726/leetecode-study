package com.example;

/**
 * @author gaiqun
 * @date 2020/4/4
 *
 * 总结
 *
 * 单例模式，直接使用双重检查或内部静态类的线程安全版本。
 */
public class Singleton {
    /**
     * 懒汉模式，线程不安全
     */
    static class Singleton1 {
        private static Singleton1 instance = null;

        public static Singleton1 getInstance() {
            if (instance == null) {
                instance = new Singleton1();
            }
            return instance;
        }

        private Singleton1() {

        }
    }

    /**
     * 懒汉模式，方法加锁。线程安全，但是每次获取实例都要加锁，效率低下
     */
    static class Singleton2 {
        private static Singleton2 instance = null;

        public static synchronized Singleton2 getInstance() {
            if (instance == null) {
                instance = new Singleton2();
            }
            return instance;
        }

        private Singleton2() {

        }
    }

    /**
     * 懒汉模式，双重检查。线程安全。只有第一次需要加锁，后续直接获取，不会再执行同步代码块
     */
    static class Singleton3 {
        private static volatile Singleton3 instance = null;

        public Singleton3 getInstance() {
            if (instance == null) {
                synchronized (this) {
                    if (instance == null) {
                        instance = new Singleton3();
                    }
                }
            }
            return instance;
        }

        private Singleton3() {

        }
    }

    /**
     * 饿汉模式，线程安全。但是一开始就持有对象
     */
    static class Singleton4 {
        private static Singleton4 instance = new Singleton4();

        public static Singleton4 getInstance() {
            return instance;
        }

        private Singleton4() {

        }
    }

    /**
     * 内部静态类。只有在第一次获取的时候会实例化内部静态类。线程安全。
     * 参考JVM定义的类的实例化规则
     */
    static class Singleton5 {
        private static class InstanceHolder {
            private static final Singleton5 instance = new Singleton5();
        }

        public static Singleton5 getInstance() {
            return InstanceHolder.instance;
        }

        private Singleton5() {

        }
    }
}
