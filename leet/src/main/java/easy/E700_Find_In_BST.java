package easy;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2021/1/2
 */
public class E700_Find_In_BST {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        if (root.val < val) {
            return searchBST(root.right, val);
        }
        return root;
    }
}
