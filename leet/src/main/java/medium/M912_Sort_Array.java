package medium;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/3/31
 *
 * 总结
 *
 * 实现了快排和归并排序。后续可以实现堆排序
 *
 * 归并和快排的区别是，快排是就地排序，空间复杂度为O(1)。归并排序如果是排序数组，只能新开辟数组保存归并结果，空间复杂度为O(n)。
 * 如果单纯排序，快排就足够好了。但是有些问题需要解决归并排序的思想来解决，比如求逆序数的问题。
 *
 * 稳定的排序算法可以使用冒泡排序，时间复杂度O(n^2)。
 */
public class M912_Sort_Array {

    public static void main(String[] args) {
        M912_Sort_Array solution = new M912_Sort_Array();
        int[] nums = {5, 2, 3, 1, 7};
        System.out.println(Arrays.toString(solution.sortArray2(nums)));
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 快排，一个sort方法，一个partition方法
     * @param nums
     * @return
     */
    public void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        // 找到分割点，然后递归排序
        int pivot = partition(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        // pivot选第一个元素
        int pivot = nums[start];
        while (start < end) {
            // 注意循环继续的条件，start<end
            while (start < end && nums[end] >= pivot) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= pivot) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    /**
     * 归并排序，使用递归求解。思路是：排序数组-》拆分两个数组-》递归排序两个数组-》两个排序数组合并
     * @param nums
     * @return
     */
    public int[] sortArray2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return nums;
        }
        // 排序结果用一个二维数组表示
        int[][] res = split(nums);
        int[] left = sortArray2(res[0]);
        int[] right = sortArray2(res[1]);
        return merge(left, right);
    }

    int[][] split(int[] array) {
        int len = array.length;
        int half = len / 2;
        int[][] res = new int[2][];
        // 注意copyOfRange是左闭右开区间
        int[] left = Arrays.copyOfRange(array, 0, half);
        int[] right = Arrays.copyOfRange(array, half, len);
        res[0] = left;
        res[1] = right;
        return res;
    }

    int[] merge(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        // 开辟新数组保存合并结果
        int[] res = new int[aLen + bLen];
        int i = 0, j = 0, k = 0;
        while (i < aLen && j < bLen) {
            if (a[i] < b[j]) {
                res[k] = a[i];
                i++;
            } else {
                res[k] = b[j];
                j++;
            }
            k++;
        }
        while (i < aLen) {
            res[k] = a[i];
            i++;
            k++;
        }
        while (j < bLen) {
            res[k] = b[j];
            j++;
            k++;
        }
        return res;
    }
}
