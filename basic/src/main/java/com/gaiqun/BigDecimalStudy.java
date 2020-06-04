package com.gaiqun;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author gaiqun
 * @date 2020/6/2
 */
public class BigDecimalStudy {

    public static void main(String[] args) {
        BigDecimal b = new BigDecimal("10.1234567").setScale(5, RoundingMode.HALF_UP);
        System.out.println(b.doubleValue());
        System.out.println(b.toString());
    }
}
