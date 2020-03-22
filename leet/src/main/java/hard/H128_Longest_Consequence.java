package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/3/22
 *
 * 总结
 *
 * 思路1：把所有数字放到set里，遍历set，在set中找以当前i开始，连续递增的序列。遍历所有数字得到最大连续序列长度。
 * Set和Array都能达到目的，什么时候用set，什么时候用Array呢？
 * 如果数字的范围有限且连续，用array，数据结构简单。如果数字范围不确定，且不连续，用Set/Map，不用保存所有数字。
 * 这个时间复杂度肯定大于O(n)，接近O(n^2)
 *
 * 思路2：并查集
 * 把所有相差1的数字合并到一个并查集里，找到最大的并查集数字个数。
 * 并查集的查找很快，比O(logn)还要快。这个时间复杂度为O(n)。
 * 难点在于正确写好并查集的合并操作。
 */
public class H128_Longest_Consequence {

    public static void main(String[] args) {
        H128_Longest_Consequence solution = new H128_Longest_Consequence();
        int[] nums = {0};
        System.out.println(solution.longestConsecutive2(nums));
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        int count = 1;
        for (Integer i : set) {
            int j = i;
            while (set.contains(j + 1)) {
                count++;
                j++;
            }
            maxLen = Math.max(maxLen, count);
            count = 1;
        }
        return maxLen;
    }

    // 保存根节点
    private static Map<Integer, Integer> par = new HashMap<>();
    // 保存并查集的大小
    private static Map<Integer, Integer> cnt = new HashMap<>();
    private static int maxLen = 1;

    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 初始化并查集，根节点是自己，大小是1.
        for (int i : nums) {
            par.put(i, i);
            cnt.put(i, 1);
        }
        // 遍历每个元素
        for (int i : nums) {
            // 如果比当前元素小1的元素存在，则合并二者（大于1的数字存在，则遍历的时候，会以小于1找到当前元素，所以不用判断
            if (par.containsKey(i - 1)) {
                union(i, i - 1);
            }
        }
        return maxLen;
    }

    private void union(int a, int b) {
        // 如果根已经相同，则不需合并
        if (find(a) == find(b)) {
            return;
        }
        par.put(a, par.get(b));
        int sum = cnt.get(a) + cnt.get(b);
        // 合并后更新保存并查集大小的map
        cnt.put(a, sum);
        cnt.put(b, sum);
        // 合并时就计算最大集合大小，不用在最后再遍历一遍cnt
        maxLen = Math.max(maxLen, cnt.get(a));
    }

    private int find(int a) {
        // 根是自己，则返回
        if (par.get(a) == a) {
            return a;
        }
        // 否则递归找到根，赋值给par
        par.put(a, find(par.get(a)));
        return par.get(a);
    }
}
