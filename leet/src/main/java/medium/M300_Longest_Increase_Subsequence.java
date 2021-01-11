package medium;

/**
 * @author gaiqun
 * @date 2020/3/14
 *
 * 总结LIS最长递增子序列问题是经典dp问题，背也要背下来。
 *
 * dp[i]表示以i结尾的最长递增子序列的长度。求最长递增子序列长度则是求dp[i]中的最大值。
 *
 * 初始化dp[0]，然后迭代计算所有dp[i]。时间复杂度O(n^2)，空间复杂度O(n)。
 *
 * 有时间复杂度为O(nlogn)的解法，目前看不懂。等以后能力有提升再看。
 */
public class M300_Longest_Increase_Subsequence {

    public static void main(String[] args) {
        M300_Longest_Increase_Subsequence solution = new M300_Longest_Increase_Subsequence();
        int[] nums = {5, 2, 1, 3, 6, 4, 9};
        System.out.println(solution.lengthOfLIS(nums));
        for (int i : solution.LIS(nums)) {
            System.out.print(i + " ");
        }
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        // 第一重循环，计算每个位置的dp，同时计算dp的最大值
        for (int i = 0; i < dp.length; i++) {
            int maxVal = 0;
            // 第二重循环，查找当前位置之前所有位置，满足能够成递增子序列里的最大dp，dp+1就是当前的dp
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxVal = Math.max(dp[j], maxVal);
                }
            }
            dp[i] = maxVal + 1;
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    public int[] LIS(int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int n = arr.length;
        String[] dp = new String[n];
        String result = String.valueOf(arr[0]);
        for (int i = 0; i < n; i++) {
            dp[i] = String.valueOf(arr[i]);
            if (dp[i].compareTo(result) < 0) {
                result = dp[i];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (dp[i].length() < dp[j].length() + 1) {
                        dp[i] = dp[j] + arr[i];
                    } else if (dp[i].length() == dp[j].length() + 1) {
                        if (dp[i].compareTo(dp[j] + arr[i]) > 0) {
                            dp[i] = dp[j] + arr[i];
                        }
                    }
                }
                if (dp[i].length() > result.length()) {
                    result = dp[i];
                } else if (dp[i].length() == result.length()) {
                    if (dp[i].compareTo(result) < 0) {
                        result = dp[i];
                    }
                }
            }
        }
        int[] res = new int[result.length()];
        for (int i = 0; i < result.length(); i++) {
            res[i] = result.charAt(i) - '0';
        }
        return res;
    }
}
