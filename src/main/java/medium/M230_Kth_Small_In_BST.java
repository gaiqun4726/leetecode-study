package medium;

import common.TreeNode;
import hard.H297_Serialize_Deserialize_Tree;

public class M230_Kth_Small_In_BST {
    public static void main(String[] args) {
        TreeNode treeNode = new H297_Serialize_Deserialize_Tree().deserialize("3,1,4,null,2");
        System.out.println(new M230_Kth_Small_In_BST().kthSmallest(treeNode, 1));
    }

    /**
     * 未完成
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return 0;
        if (root.left == null && root.left == null)
            k--;
        if (k == 0)
            return root.val;
        kthSmallest(root.left, k);
        k--;
        if (k == 0)
            return root.val;
        kthSmallest(root.right, k);
        return k;
    }
}
