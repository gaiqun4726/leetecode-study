package com.gaiqun;

/**
 * @author gaiqun
 * @date 2020/5/10
 */
public class FinalStudy {
    // static final必须在定义的时候初始化，因为static成员属于类，可能在构造函数之前被调用
    private static final String str = "";
    // final可以在构造函数里初始化，注意，有几种构造函数就需要初始化几次
    private final int value;
    FinalStudy() {
        this.value = 1;
    }

    FinalStudy(int value) {
        this.value = value;
    }
}
