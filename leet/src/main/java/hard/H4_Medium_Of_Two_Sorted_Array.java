package hard;

/**
 * @author gaiqun
 * @date 2020/5/24
 *
 * 总结
 *
 * 方法1：
 * 查找两个正序数组的中位数。在nums1中查找i，nums2中查找j，使得nums1和nums2的左半段之和与右半段之和相等。
 * 由于nums1可能全部位于左半段或右半段，因此i取值范围为[0,len1]，令[0,i-1]表示左半段，[i,len1-1]表示右半段。
 * 需要注意i取0和len1时需要特殊处理。
 * 设nums1长度为m，nums2长度为n，则有i+j=m-i+n-j，即i+j=(m+n)/2。
 * 如果m+n是奇数，把中间的数字归到左半边，则有i+j=(m+n+1)/2。
 * 即i与j满足上述等式时，由i、j划分的数组两边元素个数相等。如果再满足左半边的元素都小于右半边的元素，则中位数的位置就知道了。
 * 综上，我们的目标是在nums1中查找i，此时的j为(m+n+1)/2-i，并且nums1[i-1]<=nums2[j] && nums[j-1]<=nums[i]。
 * 我们只需在nums1中进行二分查找，nums2中的位置可以推导出来。因此nums1的长度越小越好。
 * 算法的时间复杂度为O(min(nums1.length(), nums2.length()))，空间复杂度O(1)。
 *
 * 方法2：
 * 利用归并排序里merge的操作，先把两个有序数组合并，再取中间的数字。时间复杂度O(m+n)，空间复杂度O(m+n)。
 * 居然AC了，还是双100%。
 */
public class H4_Medium_Of_Two_Sorted_Array {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {3,4};
        H4_Medium_Of_Two_Sorted_Array solution = new H4_Medium_Of_Two_Sorted_Array();
        System.out.println(solution.findMedianSortedArrays2(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 互换长短字符串
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int left = 0, right = len1;
        // 在nums1中进行二分查找
        while (left <= right) {
            int i = (left + right) / 2;
            // 利用i，j的关系得出nums2中j的值
            int j = (len1 + len2 + 1) / 2 - i;
            // 不满足中位数的定义，则继续二分。注意i=0和i=len1需要特别处理，避免越界
            if (i > 0 && nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else if (i < len1 && nums1[i] < nums2[j - 1]) {
                left = i + 1;
            } else {
                // 求中卫数的左值。
                int maxLeft = 0;
                // 特别处理i=0和j=0的情形
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // 如果总长度是奇数，直接返回左值
                if (((len1 + len2) & 1) == 1) {
                    return maxLeft;
                }
                // 求右值
                int maxRight = 0;
                // 处理i=len1和j=len2的情形
                if (i == len1) {
                    maxRight = nums2[j];
                } else if (j == len2) {
                    maxRight = nums1[i];
                } else {
                    maxRight = Math.min(nums1[i], nums2[j]);
                }
                // 偶数则需取左值右值的平均数
                return (maxLeft + maxRight) / 2d;
            }
        }
        // 不会返回0
        return 0;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1 + len2 + 1) / 2 - 1;
        int[] nums = new int[len1 + len2];
        int pos1 = 0, pos2 = 0, pos = 0;
        while (pos1 < len1 && pos2 < len2) {
            if (nums1[pos1] < nums2[pos2]) {
                nums[pos] = nums1[pos1++];
            } else {
                nums[pos] = nums2[pos2++];
            }
            pos++;
        }
        while (pos1 < len1) {
            nums[pos++] = nums1[pos1++];
        }
        while (pos2 < len2) {
            nums[pos++] = nums2[pos2++];
        }
        return ((len1 + len2) & 1) == 1 ? nums[mid] : (nums[mid] + nums[mid + 1]) / 2d;
    }
}
