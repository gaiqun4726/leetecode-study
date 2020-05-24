package com.example;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/4/24
 *
 * 使用暴力解法时间复杂度为O(n^2)，超时。
 *
 * 看题解，用归并排序来解。归并排序，每个待归并的区间内是有序的。
 * 在合并时，如果比较的两个数字后半段小于前半段，则意味着后半段的数字和前半段剩余的数字都构成了逆序。
 * 归并排序的分裂过程，最后只剩下一个元素。因此在合并时，每个区间内有序的数组，其逆序数在之前已经计算过了。
 * 在归并结束后，就可以算出全部逆序数。时间复杂度O(nlogn)
 *
 */
public class Q51_Reverse_Pairs {

    public static void main(String[] args) {
        Q51_Reverse_Pairs solution = new Q51_Reverse_Pairs();
        int[] nums = {5, 2, 3, 7, 1, 6};
        System.out.println(solution.reversePairs(nums));
    }

    private int count = 0;

    public int reversePairs(int[] nums) {
        sort(nums);
        return count;
    }

    private int[] sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int[][] res = split(nums);
        int[] arr1 = sort(res[0]);
        int[] arr2 = sort(res[1]);
        return merge(arr1, arr2);
    }

    private int[] merge(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        int len1 = arr1.length, len2 = arr2.length;
        int[] res = new int[len1 + len2];
        while (i < len1 && j < len2) {
            if (arr1[i] > arr2[j]) {
                res[k] = arr2[j++];
                count += len1 - i;
            } else {
                res[k] = arr1[i++];
            }
            k++;
        }
        while (i < len1) {
            res[k++] = arr1[i++];
        }
        while (j < len2) {
            res[k++] = arr2[j++];
        }
        return res;
    }

    private int[][] split(int[] arr) {
        int len = arr.length;
        int[][] res = new int[2][];
        res[0] = Arrays.copyOfRange(arr, 0, len / 2);
        res[1] = Arrays.copyOfRange(arr, len / 2, len);
        return res;
    }
}
