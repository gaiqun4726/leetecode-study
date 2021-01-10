package kuaishou;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2021/1/10
 */
public class RightVision {
    public static void main(String[] args) {
        int[] xianxu = {1, 2, 4, 5, 3};
        int[] zhongxu = {4, 2, 5, 1, 3};
        System.out.println(new RightVision().solve(xianxu, zhongxu));
    }

    public int[] solve(int[] xianxu, int[] zhongxu) {
        // write code here
        int n = xianxu.length;
        TreeNode root = build(xianxu, 0, n - 1, zhongxu, 0, 1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            result.add(queue.peek().val);
            while (size != 0) {
                TreeNode node = queue.poll();
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                size--;
            }
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private TreeNode build(int[] xianxu, int xl, int xr, int[] zhongxu, int zl, int zr) {
        if (xl > xr) {
            return null;
        }
        TreeNode root = new TreeNode(xianxu[xl]);
        int index = zl;
        while (zhongxu[index] != xianxu[xl]) {
            index++;
        }
        root.left = build(xianxu, xl + 1, xl + index - zl, zhongxu, zl, index - 1);
        root.right = build(xianxu, xl + index - zl + 1, xr, zhongxu, index + 1, zr);
        return root;
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
