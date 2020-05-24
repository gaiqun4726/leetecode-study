package multithread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gaiqun
 * @date 2020/4/21
 */
public class M1114_Print_In_Sequence {

    /**
     * Foo的每个方法直接调用的run，不是start线程，因此Foo的方法里没有启用线程。
     * 直接调用Foo的三个方法，就是单线程执行，会阻塞。
     * 因此测试用例必须用三个线程来做，分别调用Foo的三个方法，这样才能并发执行
     * @param args
     */
    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.third(new MyThread(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first(new MyThread(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(new MyThread(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 使用原子变量标记
     */
    static class Foo {

        private AtomicBoolean firstTag;
        private AtomicBoolean secondTag;

        public Foo() {
            this.firstTag = new AtomicBoolean(false);
            this.secondTag = new AtomicBoolean(false);
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstTag.set(true);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (!firstTag.get()) {

            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondTag.set(true);
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (!secondTag.get()) {
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    /**
     * 使用锁，有问题，不知道咋回事
     */
    static class Foo2 {

        private Lock firstTag;
        private Lock secondTag;

        public Foo2() {
            this.firstTag = new ReentrantLock();
            this.secondTag = new ReentrantLock();
            this.firstTag.lock();
            this.secondTag.lock();
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstTag.unlock();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            firstTag.lock();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            firstTag.unlock();
            secondTag.unlock();
        }

        public void third(Runnable printThird) throws InterruptedException {
            secondTag.lock();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            secondTag.unlock();
        }
    }

    static class MyThread implements Runnable {

        private int count;

        public MyThread(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            //try {
            //    Thread.sleep(3000);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            System.out.println(count);
        }
    }
}
