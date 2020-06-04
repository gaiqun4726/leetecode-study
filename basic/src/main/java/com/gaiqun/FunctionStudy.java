package com.gaiqun;

import java.util.function.Consumer;

/**
 * @author gaiqun
 * @date 2020/6/1
 */
public class FunctionStudy {

    /**
     * 只有一个抽象方法的接口，是函数接口。
     * 用不用@FunctionalInterface注解都可以，可以用来更明确的标记。
     * 被标注了@FunctionalInterface的接口，强制只能有一个抽象方法
     */
    @FunctionalInterface
    interface MyInterface {
        void hello(String s);

        /**
         * 接口可以有多个default方法。
         * 那接口与抽象类就很像了。但是接口可以多重实现，而抽象类只能单继承
         */
        default void world() {
            System.out.println();
        }

        /**
         * 接口中可以有多个静态方法。属于接口
         */
        static void method() {
            System.out.println();
        }
    }

    class MyClass implements MyInterface {

        @Override
        public void hello(String s) {

        }

        /**
         * default方法可以重写，也可以不重写
         */
        @Override
        public void world() {

        }
    }

    static class MyConsumer {
        public void consume(MyInterface consumer, String s) {
            consumer.hello(s);
        }
    }

    static class MyConsumer2 {

        /**
         * 函数式接口，可以直接用Consumer、Predicate、Supplier来做。
         * 分别提供无返回值、bool返回值、无传入参数的函数式接口。
         * 或者通用的Function接口
         * @param consumer
         * @param s
         */
        public void consume(Consumer<String> consumer, String s) {
            consumer.accept(s);
        }
    }

    public static void main(String[] args) {
        MyConsumer consumer = new MyConsumer();
        // 函数式接口，可以直接传入lambda表达式。如果不是函数式接口，则不能直接传入lambda表达式
        consumer.consume(System.out::println, "hello");

        MyConsumer2 consumer2 = new MyConsumer2();
        consumer2.consume(System.out::println, "world");
    }
}
