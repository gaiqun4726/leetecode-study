package medium;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2020/4/15
 *
 * 总结
 *
 * 从题目的意思来看，是把二叉树先序遍历的结果，用树节点的右指针构成一个链表。
 * 本题要求原地修改，其实通过修改指针就是原地修改了。并没有创建新的节点，只是修改引用。
 *
 * 解决思路是：把节点的左孩子变为右孩子，原来的右孩子变为原来左孩子最右节点的右孩子。重复上述过程，直到当前节点没有左孩子。
 *
 * 在树的问题中，先序遍历，找左子树的最右节点。即找右节点的直接前驱及类似的问题，是树问题的常见套路。
 */
public class M114_Tree_To_LinkedList {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        node2.left = null;
        node2.right = null;
        node3.left = null;
        node3.right = null;
        node1.left = node2;
        node1.right = node3;

        M114_Tree_To_LinkedList solution = new M114_Tree_To_LinkedList();
        solution.flatten(node1);
    }

    public void flatten(TreeNode root) {
        TreeNode cur = root;
        // 最后空节点则结束
        while (cur != null) {
            // 左子树不空才需要把右子树接过去
            if (cur.left != null) {
                TreeNode tmp = cur.left;
                // 找到左子树的最右节点。先序遍历，右子树的根节点遍历顺序在左子树最右节点之后。
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                // 右节点作为左子树的最右节点的右子树
                tmp.right = cur.right;
                // 然后把原来的左子树作为右子树
                cur.right = cur.left;
                // 左子树置空
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
