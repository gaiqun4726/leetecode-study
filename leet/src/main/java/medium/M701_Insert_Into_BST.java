package medium;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2021/1/2
 */
public class M701_Insert_Into_BST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
