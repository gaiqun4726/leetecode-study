package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/4/17
 */
public class E448_Find_Disappear_Numbers {

    public static void main(String[] args) {
        E448_Find_Disappear_Numbers solution = new E448_Find_Disappear_Numbers();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = solution.findDisappearedNumbers(nums);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num - 1 == i) {
                continue;
            }
            while (nums[i] - 1 != i) {
                if (nums[i] == nums[num - 1]) {
                    nums[i] = -1;
                    break;
                }
                int tmp = nums[i];
                nums[i] = nums[num - 1];
                nums[num - 1] = tmp;
                num = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                list.add(i + 1);
            }
        }
        return list;
    }
}
