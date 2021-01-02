package medium;

import common.TreeNode;

/**
 * 题目要求节点值为所有大于当前节点值之和。BST树中，父节点和右孩子都可能大于当前节点，因此直接把右孩子累加是错误的。
 * BST树的中序便利课可以得到有序集合，先右再中再左，可以得到一个递减的有序集合。通过这种调整的中序便利，可以得到累加和。
 *
 * @author gaiqun
 * @date 2021/1/2
 */
public class M1038_BST_To_GST {
    // 记录累加和
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 先右再中再左
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }
}
