package kuaishou;

/**
 * 有序数组部分旋转，从中查找目标值。比如{4,5,6,1,2,3}，查找5。
 * 使用二分查找，时间复杂度为O(lgN)。
 *
 * @author gaiqun
 * @date 2021/1/17
 */
public class SearchInRotateArray {
    public int search(int[] A, int target) {
        // write code here
        if (A == null || A.length == 0) {
            return -1;
        }
        return find(A, 0, A.length - 1, target);
    }

    private int find(int[] A, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = A[mid];
        int leftVal = A[left];
        int rightVal = A[right];
        if (midVal == target) {
            return mid;
        }
        // mid和left的值比较，确定mid所在位置是单调递增数组，还是先增后减数组
        if (midVal > leftVal) {
            // 处理leftVal和target相等的情形，避免下面分支时把left跳过了
            if (target == leftVal) {
                return left;
            } else if (target > leftVal && target < midVal) {
                // target在左半段递增数组中
                return find(A, left, mid - 1, target);
            } else {
                // target在右半段先增后减数组中
                return find(A, mid + 1, right, target);
            }
        } else {
            if (target == rightVal) {
                return right;
            } else if (target > midVal && target < rightVal) {
                return find(A, mid + 1, right, target);
            } else {
                return find(A, left, mid - 1, target);
            }
        }
    }
}
