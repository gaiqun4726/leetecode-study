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
 *
 * 思路2：只要求第k个全排列，而不是从第一个到第k个全排列。因此肯定不能求每一个全排列，这样太慢。
 * 对于这种可以通过回溯求全集，而题目只要求求部分解的，必然是用规律推导，而不是直接求全集。
 * 集合中每个元素都不同。以每个元素开头的全排列个数是一样的，都是(n-1)!
 * 可以通过判断第k个元素处于第几个元素开头的排列范围里，确定第一个元素；然后继续找第二个元素，直到找到第k个元素对应的全排列。
 * 时间复杂度O(n)
 */
public class M60_Kth_Permutation {

    public static void main(String[] args) {
        M60_Kth_Permutation solution = new M60_Kth_Permutation();
        System.out.println(solution.getPermutation2(3, 2));
    }

    private int[] nums;
    private int K;
    private int N;
    private int count = 0;
    String result = "";
    boolean tag = false;

    /**
     * 回溯，全排列。超时
     * @param n
     * @param k
     * @return
     */
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

    /**
     * 计算每个位置应该放哪个数字。
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
        StringBuilder builder = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        // 备选的数字放在list里，每次确定一个数字就移除，并放入结果builder里
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        // 这里是关键，k是大于0的数字，但是排列是从0开始算起的
        k--;
        for (int i = 0; i < n; i++) {
            // 每一轮数字可能的组合是在变化的
            int size = factorial(n - i - 1);
            int index = k / size;
            builder.append(list.remove(index));
            // 如果有余数，下一轮继续找下个元素。如果余数为0，意味着已经后续的数字不需要再排列。循环会把后续的数字直接加进来
            k %= size;
        }
        return builder.toString();
    }

    /**
     * 求全排列个数的方法。jdk里没有现成的库函数，需要自己写一下
     * @param n
     * @return
     */
    private int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
