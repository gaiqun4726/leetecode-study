package medium;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2021/1/1
 */
public class M654_Max_Tree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int maxValue = Integer.MIN_VALUE;
        int index = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = helper(nums, left, index - 1);
        root.right = helper(nums, index + 1, right);
        return root;
    }
}
