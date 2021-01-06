package medium;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2020/3/28
 *
 * 总结
 *
 * 需要背下来的题目。找最近公共祖先，用递归来求解
 *
 * 如果root是p或q，则root就是最近公共祖先；如果root是null，则没有公共祖先；
 * 以上边的条件作为递归终止的唯一条件，因此递归返回时，得到的必然是null或者p或者q的结点；
 * 递归返回时，如果左右分支一个为null，另一个不空，则最近公共祖先必然是非空的那个结点；
 * 如果两个分支都不为空，说明p、q位于左右两个分支，所以最近公共祖先必然是当前root结点。
 *
 * 综上，使用递归可以快速得到两个结点的最近公共祖先。
 */
public class M236_Nearest_Common_Ancestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 函数定义：返回p、q的最近公共祖先。由于要递归调用，因此要考虑不存在p或q的场景。
     * 定义改为：存在p、q则返回最近公共祖先；存在p或q则返回p或q；p、q都不存在，则返回null。
     * 题目的描述，树中一定存在p和q。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }
        // 递归方法会递归调用，因此函数的定义需要考虑各种情形
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        // 递归函数的返回值该怎么用，考虑base case，返回值就遵循base case的用法
        // 本题是一道后序遍历的框架
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
