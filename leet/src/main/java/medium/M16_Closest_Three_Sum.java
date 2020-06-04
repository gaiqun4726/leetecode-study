package medium;

import java.util.Arrays;

public class M16_Closest_Three_Sum {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        System.out.println(new M16_Closest_Three_Sum().threeSumClosest2(nums, 1));
    }

    /**
     * 思路和三数之和类似
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len <= 2) {
            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += nums[i];
            }
            return sum;
        }
        Arrays.sort(nums); //首先排序
        int res = 0;
        int diff = Integer.MAX_VALUE; //两个变量，分别表示目标和以及差值
        int a = 0, b = 1, c = len - 1;
        while (a < len - 2) {
            while (b < c) {
                int tempRes = nums[a] + nums[b] + nums[c];
                int tempDiff = Math.abs(tempRes - target);
                if (tempDiff < diff) {
                    diff = tempDiff;
                    res = tempRes;
                }
                if (tempRes == target) {
                    return res;
                } else if (tempRes > target) {
                    c--;
                } else {
                    b++;
                }
            }
            a++;
            b = a + 1; // 将指针归位
            c = len - 1;
        }
        return res;
    }

    public int threeSumClosest2(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int result = 0;
        boolean tag = false;
        for (int mid = 1; mid < len - 1; mid++) {
            int left = 0, right = len - 1;
            while (left < mid && right > mid) {
                int sum = nums[left] + nums[mid] + nums[right];
                if (!tag) {
                    result = sum;
                    tag = true;
                } else {
                    result = Math.abs(sum - target) < Math.abs(result - target) ? sum : result;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
}
