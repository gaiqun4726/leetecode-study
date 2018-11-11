package medium;

import common.TreeNode;

public class M938_BST_Range_Sum {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null)
            return 0;
        if (root.val <= R && root.val >= L) {
            return helperL(root.left, L, 0) + helperR(root.right, R, 0) + root.val;
        } else if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else {
            return rangeSumBST(root.right, L, R);
        }
    }

    public int helperL(TreeNode root, int L, int sum) {
        if (root == null)
            return sum;
        if (root.val >= L) {
            return helperL(root.left, L, sum) + helperL(root.right, L, sum) + root.val;
        } else {
            return helperL(root.right, L, sum);
        }
    }

    public int helperR(TreeNode root, int R, int sum) {
        if (root == null)
            return sum;
        if (root.val <= R) {
            return helperR(root.right, R, sum) + helperR(root.left, R, sum) + root.val;
        } else {
            return helperR(root.left, R, sum);
        }
    }
}
