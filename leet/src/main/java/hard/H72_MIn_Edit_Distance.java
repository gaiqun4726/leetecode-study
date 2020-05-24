package hard;

/**
 * @author gaiqun
 * @date 2020/4/6
 *
 * 总结
 *
 * 跟LCS问题相似。
 * 解决最长公共子序列、编辑距离问题，用迭代求解。难处理的问题之一是边界问题。
 * 处理方法是，定义dp时增加初始化的边界，这样避免在处理边界时的复杂性。
 */
public class H72_MIn_Edit_Distance {

    public int minDistance(String word1, String word2) {
        if (word1.isEmpty() && word2.isEmpty()) {
            return 0;
        }
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        // dp中的索引对应字符串的长度，从1开始。dp[0][0]表示空串
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 空串的编辑距离，都是非空串的长度
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < len2 + 1; j++) {
            dp[0][j] = j;
        }
        // 注意chars数组和dp数组索引的关系
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (chars1[i] == chars2[j]) {
                    // 字符相同，编辑距离和之前的相同
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    // 否则选择之前编辑距离的最小值加1
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
