package medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static common.TreeUtils.buildTree;

/**
 * 二叉树层次遍历，自底向上。想到用栈。递归就是用到了栈，因此不用显示创建栈，使用递归来解
 * @author gaiqun
 * @date 2023/8/13
 */
public class M107_Level_Trace_From_Bottom {

    public static void main(String[] args) {
        M107_Level_Trace_From_Bottom bottom = new M107_Level_Trace_From_Bottom();
        int[] nums = {3, 2, 3, -1, 3, -1, 1};
        bottom.levelOrderBottom(buildTree(nums));
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        recurse(Collections.singletonList(root));
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    void recurse(List<TreeNode> nodes) {
        if (nodes.size() == 0) {
            return;
        }
        List<TreeNode> children = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node.left != null) {
                children.add(node.left);
            }
            if (node.right != null) {
                children.add(node.right);
            }
            level.add(node.val);
        }
        recurse(children);
        res.add(level);
    }
}
