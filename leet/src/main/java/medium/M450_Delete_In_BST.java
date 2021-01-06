package medium;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2021/1/2
 */
public class M450_Delete_In_BST {
    /**
     * 注意deleteNode方法的定义：删除val为key的节点后，返回树的根节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 对于目标节点的删除
        if (root.val == key) {
            // 如果没有左右子树，直接删
            if (root.left == null && root.right == null) {
                return null;
            }
            // 如果左子树或右子树为空，用不空的子树替换当前节点
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 如果左右子树均不为空，找到左子树的最右节点替换当前节点
            TreeNode p = root.left;
            while (p.right != null) {
                p = p.right;
            }
            root.val = p.val;
            root.left = deleteNode(root.left, p.val);
        }
        // 不是目标节点，则去左右子树中删除
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
}
