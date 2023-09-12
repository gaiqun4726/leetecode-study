package easy;

import common.TreeNode;

/**
 * 判断一颗二叉树是否是高度平衡二叉树，即左右子树的高度差不超过1。
 * 本题被卡住很多次。以下解法不是时间复杂度最低的解法，但是思路最清晰，背下来。
 *
 * 本题和“是否是另一棵树的子树”的代码结构是一样的。先定义helper函数算出树高（判断子树是否相同），然后在目标函数里，结合helper函数做递归。
 *
 * 以下是检查树本身是否具有某种特性的代码框架。树的特性，一般要求左右子树有某种特性，且该特性对于任意子树成立。
 * 那么代码框架就是helper函数检查特性，主递归函数利用helper检查当前子树，然后递归调用主递归函数检查左右子树
 * 这个检查条件之间可能是与，也可能是或的关系。
 * @author gaiqun
 * @date 2023/9/4
 */
public class E110_Balance_Tree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(helper(root.left) - helper(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 求树高的函数，很经典，可以背下来。
     * @param root
     * @return
     */
    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }
}
