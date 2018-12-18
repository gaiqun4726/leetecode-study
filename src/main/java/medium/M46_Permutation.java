package medium;

import java.util.ArrayList;
import java.util.List;

public class M46_Permutation {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return res;
        List<Integer> list = new ArrayList<>();
        generate(nums, 0, list);
        return res;
    }

    public void generate(int[] nums, int index, List<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                generate(nums, index + 1, list);
                list.remove(list.size() - 1);
            }
        }

    }
}
