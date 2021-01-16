package kuaishou;

import common.TreeNode;
import common.TreeUtils;

import java.util.ArrayList;

/**
 * @author gaiqun
 * @date 2021/1/16
 */
public class FindPathInTree {
    public static void main(String[] args) {
//        int[] nums = {1,2,-1};
        int[] nums = {5, 4, 8, 1, 11, -1, 9, -1, -1, 2, 7, -1, -1, -1, -1};
        TreeNode root = TreeUtils.buildTree(nums);
        FindPathInTree solution = new FindPathInTree();
//        ArrayList<ArrayList<Integer>> res = solution.pathSum(root, 3);
        ArrayList<ArrayList<Integer>> res = solution.pathSum(root, 22);
        System.out.println(res);
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        // write code here
        find(root, new ArrayList<>(), 0, sum);
        return result;
    }

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    private void find(TreeNode root, ArrayList<Integer> list, int sum, int target) {
        if (root == null) {
            return;
        }
        // 先序遍历
        sum += root.val;
        list.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(list);
            }
            return;
        }
        // 需要new新的链表返回
        find(root.left, new ArrayList<>(list), sum, target);
        find(root.right, new ArrayList<>(list), sum, target);
    }
}
