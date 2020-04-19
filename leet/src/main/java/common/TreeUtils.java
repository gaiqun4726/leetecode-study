package common;

/**
 * @author gaiqun
 * @date 2020/4/19
 *
 * 构建二叉树的工具类。空节点用-1表示
 */
public class TreeUtils {

    public static TreeNode buildTree(int[] nums) {
        return build(1, nums);
    }

    /**
     * dfs构建。因为每个节点必须知道子节点才能构建，所以用dfs构建。
     * 树的节点从1开始编号，n号节点的左孩子编号为2n，右孩子为2n+1。
     * @param index
     * @param nums
     * @return
     */
    private static TreeNode build(int index, int[] nums) {
        if (index > nums.length || nums[index - 1] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(nums[index - 1]);
        root.left = build(2 * index, nums);
        root.right = build(2 * index + 1, nums);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, -1, 3, -1, 1};
        TreeNode root = buildTree(nums);
        System.out.println(root);
    }
}
