package multithread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author gaiqun
 * @date 2020/5/24
 */
public class M1195_Fizz_Buzz {
    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fb = new FizzBuzz(15);
        new Thread(() -> {
            try {
                fb.fizzbuzz(() -> {
                    System.out.print("fizzbuzz ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.buzz(() -> {
                    System.out.print("buzz ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.fizz(() -> {
                    System.out.print("fizz ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.number(value -> System.out.print(value + " "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    static class FizzBuzz {
        private int n;
        private Semaphore f = new Semaphore(0);
        private Semaphore b = new Semaphore(0);
        private Semaphore fb = new Semaphore(0);
        private Semaphore num = new Semaphore(1);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    f.acquire();
                    printFizz.run();
                    num.release();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0 && i % 3 != 0) {
                    b.acquire();
                    printBuzz.run();
                    num.release();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0 && i % 3 == 0) {
                    fb.acquire();
                    printFizzBuzz.run();
                    num.release();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                num.acquire();
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                    num.release();
                } else {
                    if (i % 3 == 0 && i % 5 == 0) {
                        fb.release();
                    } else if (i % 3 == 0) {
                        f.release();
                    } else {
                        b.release();
                    }
                }
            }
        }
    }
}
