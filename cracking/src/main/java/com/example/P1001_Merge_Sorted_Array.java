package com.example;

/**
 * @author gaiqun
 * @date 2020/4/9
 *
 * 总结
 *
 * 逆向双指针求解。双100%
 */
public class P1001_Merge_Sorted_Array {

    public void merge(int[] A, int m, int[] B, int n) {
        if (m < 0 || n < 0 || A == null || A.length == 0 || B == null || B.length == 0) {
            return;
        }
        int k = A.length - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[k] = A[i];
                i--;
            } else {
                A[k] = B[j];
                j--;
            }
            k--;
        }
        while (j >= 0) {
            A[k] = B[j];
            j--;
            k--;
        }
    }
}
