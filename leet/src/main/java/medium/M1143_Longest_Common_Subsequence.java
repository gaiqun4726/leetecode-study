package medium;

/**
 * @author gaiqun
 * @date 2020/4/6
 *
 * 总结
 *
 * 最长公共子序列LCS问题
 * 动态规划求解。可以用递归或迭代来做，递归超时，所以只能用迭代法。
 *
 * 迭代解法需要背下来
 */
public class M1143_Longest_Common_Subsequence {

    public static void main(String[] args) {
        M1143_Longest_Common_Subsequence solution = new M1143_Longest_Common_Subsequence();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(solution.longestCommonSubsequence3(text1, text2));
    }

    /**
     * 递归解法。迭代解法才需要dp数组记忆访问过的字符，递归直接返回结果即可。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        return helper(chars1, chars2, len1 - 1, len2 - 1);
    }

    private int helper(char[] chars1, char[] chars2, int i, int j) {
        if (i == 0 && j == 0) {
            return chars1[0] == chars2[0] ? 1 : 0;
        }
        if (i == 0) {
            return chars1[0] == chars2[j] ? 1 : helper(chars1, chars2, 0, j - 1);
        }
        if (j == 0) {
            return chars1[i] == chars2[0] ? 1 : helper(chars1, chars2, i - 1, 0);
        }
        if (chars1[i] == chars2[j]) {
            return helper(chars1, chars2, i - 1, j - 1) + 1;
        } else {
            return Math.max(helper(chars1, chars2, i, j - 1), helper(chars1, chars2, i - 1, j));
        }
    }

    /**
     * 迭代解法。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1][len2];
        // 第一步初始化，初始化最开始的元素
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        // 第二步初始化，初始化只有1个字符的两个子串的LCS
        for (int i = 1; i < len1; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < len2; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j - 1];
        }
        // 利用递推式，从小到大计算全部的LCS
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // 原字符串的LCS就是结果
        return dp[len1 - 1][len2 - 1];
    }

    /**
     * dp增加一个边界值，表示空串。这样比较好初始化
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence3(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化，空串的公共子序列长度都为0
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < len2 + 1; j++) {
            dp[0][j] = 0;
        }
        // 利用递推式，从小到大计算全部的LCS
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        // 原字符串的LCS就是结果
        return dp[len1][len2];
    }
}
