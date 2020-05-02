package medium;

import common.TreeNode;
import common.TreeUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/5/3
 *
 * 判断是不是BST树，采用中序遍历，然后判断遍历结果是不是单调递增的。遍历了两遍，时间复杂度O(N)，空间复杂度O(N)。
 */
public class M98_Validate_BST {

    public static void main(String[] args) {
        M98_Validate_BST solution = new M98_Validate_BST();
        //int[] nums = {5, 1, 4, -1, -1, 3, 6};
        int[] nums = {2, 1, 3};
        TreeNode root = TreeUtils.buildTree(nums);
        System.out.println(solution.isValidBST(root));
    }

    List<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        }
        inorder(root);
        int prev = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= prev) {
                return false;
            }
            prev = list.get(i);
        }
        return true;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
}
