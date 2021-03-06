package easy;

import java.util.HashMap;
import java.util.Map;

public class E1_Two_Sum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        System.out.println(new E1_Two_Sum().twoSum(nums, 6));
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = {-1, -1}; //只有在初始化数组时可以这么用。不能直接返回右边的大括号数组。
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int other = target - num;
            if (other == num && map.get(other) != i) {
                return new int[]{i, map.get(other)};
            } else if (other != num && map.containsKey(other)) {
                return new int[]{i, map.get(other)};
            }
        }
        return new int[]{-1, -1};
    }
}
