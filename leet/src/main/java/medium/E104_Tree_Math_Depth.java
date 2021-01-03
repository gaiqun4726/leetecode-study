package medium;

import common.TreeNode;
import common.TreeUtils;

/**
 * @author gaiqun
 * @date 2021/1/3
 */
public class E104_Tree_Math_Depth {
    public static void main(String[] args) {
        E104_Tree_Math_Depth solution = new E104_Tree_Math_Depth();
        int[] nums = {1, 2, 3, 4, -1, -1, 5};
        TreeNode root = TreeUtils.buildTree(nums);
        System.out.println(solution.maxDepth(root));
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
