package medium;

import common.TreeNode;

public class M230_Kth_Small_In_BST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(6);
        TreeNode root4 = new TreeNode(2);
        TreeNode root5 = new TreeNode(4);
        TreeNode root6 = new TreeNode(1);
        root.left = root2;
        root.right = root3;
        root2.left = root4;
        root2.right = root5;
        root4.left = root6;
        System.out.println(new M230_Kth_Small_In_BST().kthSmallest2(root, 3));
    }

    /**
     * dfs不好返回值，因此使用引用保存中间结果。
     * 中序遍历的方法可以找到目标值，但是在找到目标值后依然需要遍历整棵树，因此有无用操作。
     *
     * @param root
     * @param k
     * @return
     */
    public static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        int[] array = new int[2];
        array[0] = k;
        dfs(root, array);
        return array[1];
    }

    public void dfs(TreeNode root, int[] array) {
        if (root == null)
            return;
        dfs(root.left, array);
        count++;
        if (count == array[0]) {
            array[1] = root.val;
            return;
        }
        dfs(root.right, array);
    }

    // cnt从零开始取
    int cnt = 0;
    int val = 0;

    public int kthSmallest2(TreeNode root, int k) {
        find(root, k);
        return val;
    }

    public void find(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        find(root.left, k);
        // 必须先把cnt增加，再判断和k是否相等
        cnt++;
        // 中序遍历，得到有序集合，第k个元素就是目标元素。
        if (cnt == k) {
            val = root.val;
            return;
        }
        find(root.right, k);
    }
}
