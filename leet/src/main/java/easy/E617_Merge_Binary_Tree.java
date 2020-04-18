package easy;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2020/4/18
 *
 * 总结
 *
 * 合并两颗二叉树。同时深度遍历两棵树，不需要重新创建树节点，直接用第一颗树作为结果，把第二棵树接到第一棵树上。
 */
public class E617_Merge_Binary_Tree {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return t1;
        }
        if (t1 == null) {
            return t2;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
