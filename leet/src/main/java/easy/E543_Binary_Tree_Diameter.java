package easy;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2020/4/4
 *
 * 总结
 *
 * 这道题归为Easy，但是对我一点也不容易
 * 二叉树没有给特殊的信息，那么我们的手段只有深度或广度遍历。
 * 在深度遍历时，我们可以得到树的高度，基于左右子树的高度，可以算出结点的直径。
 * 所有结点的直径中的最大值就是树的直径。
 * 在深度遍历计算左右子树高的同时，计算出树的直径，更新树的最大值。
 */
public class E543_Binary_Tree_Diameter {

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 树高只在遍历求最大路径时有用。DFS完成后总的树高并不需要。
        depth(root);
        return ans;
    }

    public int depth(TreeNode root) {
        // 空树高为0，一个根节点的树高为1。树高即为经过的节点个数
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        // 左右子树经过根节点，构成路径。路径是树的所有边的和，恰好等于左右子树树高的和
        ans = Math.max(ans, leftDepth + rightDepth);
        // 当前树高为左右子树最大高度加1。
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
