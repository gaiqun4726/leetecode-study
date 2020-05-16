package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2020/5/15
 *
 * 总结
 *
 * 求数组中和为k的连续子数组个数。暴力求解时间复杂度是O(N^2)。
 *
 * s[i]表示从0开始到i的连续子数组的和。如果s[j]-s[i]=k，则[i,j]表示一个和为k的连续子数组。
 * 使用一个HashMap表示累加和出现的次数。
 * 从前向后遍历数组，累加和s[i]。把s[i]+k放入map，这样，当遍历到j,且累加和s[j]为s[i]+k时，就得到满足条件的子数组。
 * map中的key被使用多次是正确的，因为对应的子数组结尾j不同，因此得到的是不同的子数组。
 * 注意，一个元素也没有也是一种情形，因此需要把和为0时，0+k加入map。当s[i]第一次出现k时，就可以计出现一次了。
 * 可以使用s[i]+k或s[i]-k作为key，注意初始化不同。
 * 一次遍历可以得到结果，时间复杂度为O(N)。
 */
public class M560_Count_Of_SubArray {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        M560_Count_Of_SubArray solution = new M560_Count_Of_SubArray();
        System.out.println(solution.subarraySum2(nums, k));
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(k, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum)) {
                count += map.get(sum);
            }
            map.put(sum + k, map.getOrDefault(sum + k, 0) + 1);
        }
        return count;
    }

    public int subarraySum3(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
