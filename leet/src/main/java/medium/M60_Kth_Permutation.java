package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/29
 *
 * 总结
 *
 * 思路1：使用回溯法求全排列，当得到第k个全排列时，输出结果。
 * 经测试，每个测试用例都能得到正确结果，但是200个测试用例一起执行，在第111个测试用例上会报TLE错误，因此需要找其他解法
 */
public class M60_Kth_Permutation {
    public static void main(String[] args) {
        M60_Kth_Permutation solution = new M60_Kth_Permutation();
        System.out.println(solution.getPermutation(5, 2));
    }

    private int[] nums;
    private int K;
    private int N;
    private int count = 0;
    String result = "";
    boolean tag = false;

    public String getPermutation(int n, int k) {
        N = n;
        K = k;
        nums = new int[N];
        for (int i = 1; i <= N; i++) {
            nums[i - 1] = i;
        }
        backTrace(new ArrayList<>());
        return result;
    }

    private void backTrace(List<String> list) {
        if (tag) {
            return;
        }
        if (list.size() == N && count == K - 1) {
            result = String.join("", list);
            count++;
            tag = true;
            return;
        } else if (list.size() == N) {
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!list.contains(String.valueOf(nums[i]))) {
                list.add(String.valueOf(nums[i]));
                backTrace(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
        }
    }
}
