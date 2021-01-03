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
        int[] nums = {5, 1, -1, -1, 4, 3, 6};
        //int[] nums = {2, 1, 3};
        TreeNode root = TreeUtils.buildTree(nums);
        System.out.println(solution.isValidBST2(root));
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

    /**
     * 这个解法是错的。不只是当前节点满足左《中《右，其左子节点也必须小于根节点。
     * 因此应当带上当前对节点大小的限制。
     * 总之，这道题还是用中序遍历来做容易一些。
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftValid = true, rightValid = true;
        if (root.left != null) {
            leftValid = root.left.val < root.val;
        }
        if (root.right != null) {
            rightValid = root.right.val > root.val;
        }
        return leftValid && rightValid && isValidBST(root.left) && isValidBST(root.right);
    }

    Integer prev = null;

    /**
     * 中序遍历一次搞定。注意边界值，用Integer的null表示初始的prev，不能用Integer.MAX_VALUE,测试用例里有极值。
     *
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftValid = isValidBST(root.left);
        // 严格单调递增
        if (prev != null && prev >= root.val) {
            return false;
        }
        prev = root.val;
        boolean rightValid = isValidBST(root.right);
        return leftValid && rightValid;
    }

    /**
     * 判断BST树，左节点必须小于根节点，等于是不行的。
     * 测试用例里取值有最大最小整数，因此把最大最小整数作为边界传递是不行的。
     * 改为传递最大最小限制的节点。
     *
     * @param root
     * @return
     */
    public boolean isValidBST4(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode root, TreeNode minVal, TreeNode maxVal) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        // 当前root不满足条件则return false。
        if (minVal != null && minVal.val >= val) {
            return false;
        }
        if (maxVal != null && maxVal.val <= val) {
            return false;
        }
        // 修改左右子节点的边界，递归判断
        return validate(root.left, minVal, root) && validate(root.right, root, maxVal);
    }
}
