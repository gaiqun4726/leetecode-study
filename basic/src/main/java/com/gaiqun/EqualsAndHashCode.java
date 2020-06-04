package com.gaiqun;

/**
 * @author gaiqun
 * @date 2020/5/25
 *
 * equals有个约定，就是如果equals为true，则hashCode必须相同。，
 * 默认的时候，equals方法比较的是内存地址，只有对象和他自身比较才会相同。
 * 每个Object的hashCode是native方法生成的，按理都是不同的。
 * 因此没有复写equals和hashCode时，满足equals的约定。
 * 但是复写了equals以后，Object可以和其他Object相同了，但此时hashCode还是不同的，不满足equals约定。
 * 因此，如果复写了equals方法，就必须重写hashCode方法，使得hashCode满足equals的约定。
 * 反之，复写hashCode，不影响equals，不要求必须重写equals。
 */
public class EqualsAndHashCode {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        // Object.hashCode()是native方法，每个Object的hashCode都不同
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
    }
}
