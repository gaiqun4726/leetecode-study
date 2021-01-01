package medium;

import common.TreeNode;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/4/18
 *
 * 总结
 *
 * 前序中序遍历，构建唯一二叉树
 */
public class M105_Build_Binary_Tree {
    public static void main(String[] args) {
        M105_Build_Binary_Tree solution = new M105_Build_Binary_Tree();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        solution.buildTree2(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        int len = preorder.length;
        int val = preorder[0];
        TreeNode root = new TreeNode(val);
        int leftLen = find(val, inorder);
        // 使用Arrays.copyOfRange方法复制数组。注意拷贝的from、to是左闭右开区间。函数在拷贝from、to重叠的数组时，返回空数组
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + leftLen), Arrays.copyOfRange(inorder, 0, leftLen));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1 + leftLen, len),
                Arrays.copyOfRange(inorder, 1 + leftLen, len));
        return root;
    }

    private int find(int val, int[] inorder) {
        int pos = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                pos = i;
            }
        }
        return pos;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        int len = preorder.length;
        return helper(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    public TreeNode helper(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        int val = preorder[preLeft];
        int index = inLeft;
        for (; index <= inRight; index++) {
            if (inorder[index] == val) {
                break;
            }
        }
        int size = index - inLeft;
        TreeNode root = new TreeNode(val);
        root.left = helper(preorder, preLeft + 1, preLeft + size, inorder, inLeft, index - 1);
        root.right = helper(preorder, preLeft + size + 1, preRight, inorder, index + 1, inRight);
        return root;
    }
}
