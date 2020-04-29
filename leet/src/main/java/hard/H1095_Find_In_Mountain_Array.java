package hard;

/**
 * @author gaiqun
 * @date 2020/4/29
 *
 * 总结
 *
 * 需要读懂题意。山脉数组是一个存在最值的数组，最值左侧单调递增，右侧单调递减。
 * 如果能找到最值，左右两侧的数组分别进行二分就可以找到目标值。
 * 找最值的方法也是二分，每次的mid和mid+1所在的值进行比较，根据是递增还是递减判断峰值是在mid的左侧还是右侧。
 * 总的时间复杂度是3次二分查找，即O(logN)
 *
 * 二分查找用在有序数组中，每次二分的条件可以任意变换，不一定就是mid和target的比较。
 * 通过变形的二分，可以解决许多问题。比如旋转数组找目标值。
 * 类似的局部有序数组找目标值问题，都可以考虑二分来解决，区别就是二分的条件不同。
 */
public class H1095_Find_In_Mountain_Array {

    public static void main(String[] args) {
        H1095_Find_In_Mountain_Array solution = new H1095_Find_In_Mountain_Array();
        int[] nums = {0, 1, 2, 4, 2, 1};
        //int[] nums = {1, 2, 3, 4, 5, 3, 1};
        //int[] nums = {3, 5, 3, 2, 0};
        MountainArray array = new MountainArray(nums);
        System.out.println(solution.findInMountainArray(3, array));
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int start = 0, end = len - 1;
        int mid = (start + end) / 2;
        while (start <= end) {
            mid = (start + end) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int ls = 0, le = mid;
        while (ls <= le) {
            int lm = (ls + le) / 2;
            if (mountainArr.get(lm) == target) {
                return lm;
            } else if (mountainArr.get(lm) < target) {
                ls = lm + 1;
            } else {
                le = lm - 1;
            }
        }
        int rs = mid, re = len - 1;
        while (rs <= re) {
            int rm = (rs + re) / 2;
            if (mountainArr.get(rm) == target) {
                return rm;
            } else if (mountainArr.get(rm) < target) {
                re = rm - 1;
            } else {
                rs = rm + 1;
            }
        }

        return -1;
    }
}

class MountainArray {

    private int[] nums;

    public MountainArray(int[] nums) {
        this.nums = nums;
    }

    int get(int index) {
        return nums[index];
    }

    int length() {
        return nums.length;
    }
}