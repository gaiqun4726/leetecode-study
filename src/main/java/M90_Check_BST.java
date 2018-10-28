public class M90_Check_BST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(1);
        root.right = right;
        System.out.println(new M90_Check_BST().isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        return isValidHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 判断二叉树是不是BST树的时候，不能只比较root节点和左右子节点的值，这样只能保证局部满足BST树性质，
     * 不能保证全局BST树的性质。因此，在判断子树时，必须带上父节点的值用来限制子节点值的范围。
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    boolean isValidHelper(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        if (root.val >= max || root.val <= min)
            return false;
        return isValidHelper(root.left, min, root.val) && isValidHelper(root.right, root.val, max);
    }

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
