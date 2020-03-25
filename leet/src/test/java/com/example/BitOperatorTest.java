package com.example;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author gaiqun
 * @date 2020/3/25
 */
public class BitOperatorTest {

    @Test
    public void test() {
        int a = 2;
        int temp = 0;
        // 左移n位，就是乘以2^n
        temp = a << 2;
        // 位运算不会改变原值
        Assert.assertEquals(2, a);
        Assert.assertEquals(8, temp);
        temp = a << 2 + 1;
        // 位运算的优先级低于加减运算
        Assert.assertEquals(2, a);
        Assert.assertEquals(16, temp);
        temp = (a << 2) + 1;
        // 如果想要改变顺序，需要在位运算外加括号
        Assert.assertEquals(2, a);
        Assert.assertEquals(9, temp);
    }
}
