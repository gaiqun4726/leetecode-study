package multithread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author gaiqun
 * @date 2020/5/24
 *
 * 总结
 *
 * leetcode多线程部分，如果结果不对，就会输出“内部错误”或者“输入不对”。
 * 这个时候只能本地debug了。
 *
 * 本题的意思是，每个方法会被一个线程调用一次。因此我们要在方法内部打印2n个数字。
 */
public class M1116_Print_Zero_Even_Odd {

    static class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        Semaphore z = new Semaphore(1);
        Semaphore e = new Semaphore(0);
        Semaphore o = new Semaphore(0);

        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                z.acquire();
                printNumber.accept(0);
                if ((i & 1) == 0) {
                    o.release();
                } else {
                    e.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                e.acquire();
                printNumber.accept(i);
                z.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                o.acquire();
                printNumber.accept(i);
                z.release();
            }
        }
    }
}
