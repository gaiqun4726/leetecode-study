package medium;

import common.TreeNode;

/**
 * @author gaiqun
 * @date 2020/4/18
 *
 * 总结
 *
 * 中序后续遍历，构建唯一二叉树
 */
public class M106_Build_Binary_Tree {
    public static void main(String[] args) {
        M106_Build_Binary_Tree solution = new M106_Build_Binary_Tree();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        solution.buildTree(inorder, postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        int len = inorder.length;
        return helper(inorder, 0, len - 1, postorder, 0, len - 1);
    }

    public TreeNode helper(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if (inLeft > inRight) {
            return null;
        }
        int val = postorder[postRight];
        int index = inLeft;
        for (; index <= inRight; index++) {
            if (inorder[index] == val) {
                break;
            }
        }
        int size = index - inLeft;
        TreeNode root = new TreeNode(val);
        root.left = helper(inorder, inLeft, index - 1, postorder, postLeft, postLeft + size - 1);
        root.right = helper(inorder, index + 1, inRight, postorder, postLeft + size, postRight - 1);
        return root;
    }
}
