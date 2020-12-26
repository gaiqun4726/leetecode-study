package medium;

import common.TreeNode;
import common.TreeUtils;

import java.util.*;

/**
 * @author gaiqun
 * @date 2020/4/19
 *
 * 总结
 *
 * 思路1：动态规划问题。
 *
 * 这道题具有最优子结构，且子问题相互独立，又是求最值的问题。因此可以用动态规划求解。
 * 从根节点开始，最优解是根节点的值加上孙子节点的值，和根节点左右孩子的值，之中较大的。
 * 树只能从父节点访问子节点，因此不能自底向上，只能自上而下的遍历树。使用备忘录记录访问过的节点值。
 *
 * 思路2：既然最多是隔层访问，那么用广度优先遍历，把奇数层和偶数层的和分别计算，然后比较较大的值即可。
 * 结果证明是错的，有可能是隔好几层才能得到最优解。因此奇偶层遍历时不对的，只能动态规划求解。
 */
public class M337_Rob_III {

    public static void main(String[] args) {
        M337_Rob_III solution = new M337_Rob_III();
        int[] nums = {3, 2, 3, -1, 3, -1, 1};
        //int[] nums = {3, 4, 5, 1, 3, -1, 1};
        //int[] nums = {4, 1, -1, 2, -1, 3};
        TreeNode root = TreeUtils.buildTree(nums);
        System.out.println(solution.rob3(root));
    }

    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        if (root == null) {
            memo.put(null, 0);
            return 0;
        }
        int val = root.val;
        int leftVal = 0;
        int rightVal = 0;
        int leftleftVal = 0;
        int leftrightVal = 0;
        int rightleftVal = 0;
        int rightrightVal = 0;
        if (root.left != null) {
            leftVal = rob(root.left);
            leftleftVal = rob(root.left.left);
            leftrightVal = rob(root.left.right);
        }
        if (root.right != null) {
            rightVal = rob(root.right);
            rightleftVal = rob(root.right.left);
            rightrightVal = rob(root.right.right);
        }
        int maxVal = Math.max((val + leftleftVal + leftrightVal + rightleftVal + rightrightVal), (leftVal + rightVal));
        memo.put(root, maxVal);
        return maxVal;
    }

    /**
     * 奇偶层层次遍历，此解不可行。
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        int oddSum = 0;
        int evenSum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (level % 2 == 0) {
                evenSum += sum;
            } else {
                oddSum += sum;
            }
            level++;
        }
        return Math.max(evenSum, oddSum);
    }

    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> levels = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            while (size != 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            levels.add(sum);
        }
        if (levels.size() == 1) {
            return root.val;
        }
        int a = levels.get(0);
        int b = Math.max(levels.get(0), levels.get(1));
        for (int i = 2; i < levels.size(); i++) {
            int tmp = Math.max(a + levels.get(i), b);
            a = b;
            b = tmp;
        }
        return b;
    }

    public int rob4(TreeNode root) {
        memo = new HashMap<>();
        return dp(root);
    }

    public int dp(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int robLeft = root.left != null ? dp(root.left.left) + dp(root.left.right) : 0;
        int robRight = root.right != null ? dp(root.right.left) + dp(root.right.right) : 0;
        int res = Math.max(root.val + robLeft + robRight, dp(root.left) + dp(root.right));
        memo.put(root, res);
        return res;
    }
}
