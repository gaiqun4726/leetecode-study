package com.example;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/3/21
 */
public class Q40_K_Least_Numbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k > arr.length) {
            return null;
        }
        Arrays.sort(arr);
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }
}
