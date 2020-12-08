package easy;

import common.TreeNode;
import common.TreeUtils;

/**
 * @author gaiqun
 * @date 2020/5/7
 *
 * 总结
 * 思路1：递归。判断两个子树是不是相同，如果不同，继续以左右子树判断是否相同。
 * 从用例来看，两棵树一颗空一颗非空则不是子树关系。
 *
 * 思路2：按先序遍历把树转为字符串，判断是不是子串。注意空指针需要填充值。
 */
public class E572_Is_SubTree {

    public static void main(String[] args) {
        TreeNode s = TreeUtils.buildTree(new int[] {1, -1, 1, -1, -1, 2});
        TreeNode t = TreeUtils.buildTree(new int[] {1, 2});
        E572_Is_SubTree solution = new E572_Is_SubTree();
        System.out.println(solution.isSubtree2(s, t));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        // 一颗为空，一颗非空，则不是子树
        if (s == null || t == null) {
            return false;
        }
        // 以根节点比较两棵子树，否则递归对左右子树进行比较
        return check(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    /**
     * 同时先序遍历，完全一样，两棵子树一样
     * @param s
     * @param t
     * @return
     */
    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val == t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }

    public boolean isSubtree2(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return check2(s, t) || isSubtree2(s.left, t) || isSubtree2(s.right, t);
    }

    private boolean check2(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return check2(s.left, t.left) && check2(s.right, t.right);
    }
}
