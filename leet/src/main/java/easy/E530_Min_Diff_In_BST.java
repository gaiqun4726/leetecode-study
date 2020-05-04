package easy;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2020/5/5
 *
 * 总结
 *
 * 求BST树任意两点差的绝对值的最小值。中序遍历BST得到的是递增序列，计算相邻两个值得绝对值的差，取最小差。
 */
public class E530_Min_Diff_In_BST {

    int minDiff = Integer.MAX_VALUE;
    int prev = -1;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (prev != -1) {
            minDiff = Math.min(minDiff, Math.abs(root.val - prev));
        }
        prev = root.val;
        inorder(root.right);
    }
}
