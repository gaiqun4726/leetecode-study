package easy;

import common.TreeNode;
import common.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2023/9/9
 */
public class E872_Similar_Leaf {

    public static void main(String[] args) {
        E872_Similar_Leaf solution = new E872_Similar_Leaf();
//        TreeNode root1 = TreeUtils.buildTree(new int[]{3, 5, 1, 6, 2, 9, 8, -1, -1, 7, 4, -1, -1, -1, -1});
//        TreeNode root2 = TreeUtils.buildTree(new int[]{3, 5, 1, 6, 7, 4, 2, -1, -1, -1, -1, -1, -1, 9, 8});
        TreeNode root1 = TreeUtils.buildTree(new int[]{1, -1, -1});
        TreeNode root2 = TreeUtils.buildTree(new int[]{2, -1, -1});
        System.out.println(solution.leafSimilar(root1, root2));
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        trace(root1, list1);
        trace(root2, list2);
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void trace(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null && right == null) {
            list.add(root.val);
            return;
        }
        if (left != null && left.left == null && left.right == null) {
            list.add(left.val);
        } else {
            trace(left, list);
        }
        if (right != null && right.left == null && right.right == null) {
            list.add(right.val);
        } else {
            trace(right, list);
        }
    }
}
