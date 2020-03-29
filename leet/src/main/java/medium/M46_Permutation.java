package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 总结
 * 回溯法。在leetcode上，每次用回溯法求解都出错的原因是，我用了静态变量。
 * leetcode判题的方法是，不断调同一个接口，因此静态结果集不会被清空。
 * 使用leetcode解题时，别用静态变量
 */
public class M46_Permutation {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return res;
        List<Integer> list = new ArrayList<>();
        generate(nums, list);
        return res;
    }

    public void generate(int[] nums, List<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                generate(nums, list);
                list.remove(list.size() - 1);
            }
        }

    }

    public static void main(String[] args) {
        M46_Permutation solution = new M46_Permutation();
        int[] nums = {1, 2, 3};
        System.out.println(solution.permute(nums));
    }


    private static List<List<Integer>> result = new ArrayList<>();
    private static LinkedList<Integer> partResult = new LinkedList<>();

    public List<List<Integer>> permute2(int[] nums) {
        List<Integer> numList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numList.add(num);
        }
        backtrace(nums.length, numList);
        return result;
    }

    public void backtrace(int len, List<Integer> list) {
        for (Integer element : list) {
            partResult.addLast(element);
            if (partResult.size() == len) {
                result.add(new ArrayList<>(partResult));
            } else {
                List<Integer> newList = new ArrayList<>(list);
                // 注意，remove有两个重载接口，一个是移除索引指定的元素，一个是移除元素。要想移除元素，要传入Integer，而不能传入int
                newList.remove(element);
                backtrace(len, newList);
            }
            partResult.removeLast();
        }
    }
}
