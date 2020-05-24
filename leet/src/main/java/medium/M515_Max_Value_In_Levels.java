package medium;

import common.TreeNode;
import common.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/5/24
 */
public class M515_Max_Value_In_Levels {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 3, -1, 9};
        TreeNode root = TreeUtils.buildTree(nums);
        List<Integer> result = new M515_Max_Value_In_Levels().largestValues(root);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            while (size != 0) {
                TreeNode cur = queue.poll();
                maxValue = Math.max(maxValue, cur.val);
                // 千万小心，别再把root放过来了！！！
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                size--;
            }
            result.add(maxValue);
        }
        return result;
    }
}
