package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/4/9
 *
 * 总结
 *
 * 学习list.toArray方法。List.toArray(new T[0])这种方式把list转为array
 * 二维数组定义的时候，只需要指定第一维的长度。
 *
 * 思路1：暴力法，从1开始不断尝试连续数字，看是否可以构成目标和。如果不能构成则从2开始。
 * 使用等差数列求和公式。
 */
public class Q57_Continuous_Sequence {

    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        if (target <= 0) {
            return res.toArray(new int[0][]);
        }
        for (int i = 1; i < target; i++) {
            int sum = i;
            for (int j = 1; sum < target; j++) {
                sum = calc(i, j);
                if (sum == target) {
                    int[] array = new int[j];
                    for (int l = 0; l < j; l++) {
                        array[l] = i + l;
                    }
                    res.add(array);
                } else if (sum > target) {
                    break;
                }
            }
        }
        return res.toArray(new int[0][]);
    }

    private int calc(int a, int n) {
        return a * n + n * (n - 1) / 2;
    }
}
