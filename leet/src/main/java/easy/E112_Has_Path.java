package easy;

import common.TreeNode;
import common.TreeUtils;

/**
 * @author gaiqun
 * @date 2021/1/3
 */
public class E112_Has_Path {
    public static void main(String[] args) {
        E112_Has_Path solution = new E112_Has_Path();
        int[] nums = {1, 2, -1};
        TreeNode root = TreeUtils.buildTree(nums);
        System.out.println(solution.hasPathSum(root, 1));
    }

    boolean hasPath = false;

    public boolean hasPathSum(TreeNode root, int sum) {
        helper(root, sum, 0);
        return hasPath;
    }

    private void helper(TreeNode root, int target, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (target == sum && root.left == null && root.right == null) {
            hasPath = true;
        }
        helper(root.left, target, sum);
        helper(root.right, target, sum);
    }
}
