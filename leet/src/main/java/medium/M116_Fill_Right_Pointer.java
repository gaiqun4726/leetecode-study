package medium;

/**
 * 题目是一颗完美二叉树
 *
 * @author gaiqun
 * @date 2021/1/1
 */
public class M116_Fill_Right_Pointer {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        helper(root.left, root.right);
        return root;
    }

    void helper(Node left, Node right) {
        if (left == null) {
            return;
        }
        left.next = right;
        helper(left.left, left.right);
        helper(right.left, right.right);
        // 跨子树的也要用递归函数连起来
        helper(left.right, right.left);
    }

    static class Node {
        private Node left;
        private Node right;
        private Node next;
        private int val;

        public Node(int val) {
            this.val = val;
        }
    }
}
