package easy;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2021/1/3
 */
public class E101_Is_Symmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
        }
        return false;
    }
}
