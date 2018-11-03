package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M15_Three_Sum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new M15_Three_Sum().threeSum(nums));
    }

    /**
     * 固定一个数字，找其他两个数字。首先排序数组，找其他两个数就可以变为求两数和的问题。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3)
            return list;
        Arrays.sort(nums); // 默认是升序排序
        int len = nums.length;
        int n1 = 0;
        int n2 = 1;
        int n3 = len - 1;
        while (n1 <= len - 3) {
            if (nums[n1] > 0) // 升序数组，第一个数为正，后两个数的和不可能为负
                break;
            while (n2 < n3) {
                if (nums[n1] + nums[n2] + nums[n3] == 0) {
                    list.add(Arrays.asList(nums[n1], nums[n2], nums[n3]));
                    while (n2 < n3 && nums[n2] == nums[n2 + 1]) n2++; // 去除可能重复的三元组
                    while (n2 < n3 && nums[n3] == nums[n3 - 1]) n3--;
                    n2++;
                    n3--;
                } else if (nums[n1] + nums[n2] + nums[n3] < 0) {
                    n2++;
                } else {
                    n3--;
                }
            }
            while (n1 <= len - 3 && nums[n1] == nums[n1 + 1]) n1++; // 去除可能重复的三元组
            n1++;
            n2 = n1 + 1; // 将后两个指针归位
            n3 = len - 1;
        }
        return list;
    }
}
