package easy;

public class E88_Merge_Two_Sorted_Array {
    /**
     * 比较两个数组的尾元素，将大的放到其最终位置上
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = m + n - 1;
        int tail1 = m - 1;
        int tail2 = n - 1;
        while (tail1 > -1 && tail2 > -1) {
            if (nums1[tail1] > nums2[tail2]) {
                nums1[cur--] = nums1[tail1--];
            } else {
                nums1[cur--] = nums2[tail2--];
            }
        }
        while (tail2 > -1) {
            nums1[cur--] = nums2[tail2--];
        }
    }
}
