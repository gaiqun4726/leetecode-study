package easy;

/**
 * @author gaiqun
 * @date 2020/3/22
 *
 * 总结
 *
 * 连续递增子序列，直接一次遍历就可以。如果是递增子序列，需要用dp解决
 */
public class E674_Longest_Continuous_Sequence {

    public static void main(String[] args) {
        E674_Longest_Continuous_Sequence solution = new E674_Longest_Continuous_Sequence();
        int[] nums = {1, 3, 5, 7};
        System.out.println(solution.findLengthOfLCIS2(nums));
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLen = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                maxLen = Math.max(maxLen, count);
                count = 1;
            }
        }
        // 如果整个序列递增，那么循环里的else分支不会走到，所以需要在循环结束后再收集一次maxLen。
        maxLen = Math.max(maxLen, count);
        return maxLen;
    }

    public int findLengthOfLCIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int count = 1;
        int result = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                count++;
            } else {
                result = Math.max(result, count);
                count = 1;
            }
        }
        return Math.max(result, count);
    }
}

