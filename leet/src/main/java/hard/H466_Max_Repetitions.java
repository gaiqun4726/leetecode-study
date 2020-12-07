package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/4/19
 */
public class H466_Max_Repetitions {

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int[] repeatCnt = new int[n1 + 1];
        int[] nextIdx = new int[n1 + 1];
        int j = 0, cnt = 0;
        for (int k = 1; k <= n1; ++k) {
            for (int i = 0; i < s1.length(); ++i) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    ++j;
                    if (j == s2.length()) {
                        j = 0;
                        ++cnt;
                    }
                }
            }
            repeatCnt[k] = cnt;
            nextIdx[k] = j;
            for (int start = 0; start < k; ++start) {
                if (nextIdx[start] == j) {
                    int interval = k - start;
                    int repeat = (n1 - start) / interval;
                    int patternCnt = (repeatCnt[k] - repeatCnt[start]) * repeat;
                    int remainCnt = repeatCnt[start + (n1 - start) % interval];
                    return (patternCnt + remainCnt) / n2;
                }
            }
        }
        return repeatCnt[n1] / n2;
    }
}
