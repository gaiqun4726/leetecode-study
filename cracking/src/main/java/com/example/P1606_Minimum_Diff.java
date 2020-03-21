package com.example;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/3/18
 *
 * 总结
 *
 * 这个题我只有暴力求解的思路，时间复杂度为O(M*N)。
 *
 * 这道题归类为双指针问题，参考别人的解决方案，时间复杂度为max(nlogn,mlogm)。
 * 将两个数组排序，从两个数组头部开始逐个比较。
 * 假设a的下标为i，b的下标为j。a[i]>b[j]时，a[i]与b[j]之后的元素都不用比较了，因为只会比当前的差要大，所以a的指针i向前；反之，b的指针向前。
 *
 * 有一个问题就是提防数据类型越界，越界的话绝对值的计算就错了。
 * 防止越界的简单方法就是扩大数据类型，从int转为long，之前也干过类似的事情。
 *
 * 或者如果判断越界，直接丢掉也可以，因为题目的返回值是int类型，证明差一定在整型范围内，所以可以把越界的结果丢弃。
 */
public class P1606_Minimum_Diff {

    public static void main(String[] args) {
        P1606_Minimum_Diff solution = new P1606_Minimum_Diff();
        int[] a = {-2147483648, 1};
        int[] b = {2147483647, 0};
        System.out.println(solution.smallestDifference(a, b));
    }

    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long minDiff = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            Long aa = (long) a[i];
            Long bb = (long) b[j];
            minDiff = Math.min(minDiff, Math.abs(aa - bb));
            if (aa < bb) {
                i++;
            } else {
                j++;
            }
        }
        return (int) minDiff;
    }
}
