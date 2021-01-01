package medium;

import common.TreeNode;
import common.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 找重复的子树。把每棵子树序列化，放入map。map的key为序列化的字符串，value为字符串出现次数。
 * 不止出现一次的字符串，子树根节点就需要返回
 *
 * @author gaiqun
 * @date 2021/1/1
 */
public class M652_Find_Duplicate_Subtree {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, -1, 2, 4, -1, -1, -1, -1, 4, -1, -1, -1};
        TreeNode root = TreeUtils.buildTree(nums);
        M652_Find_Duplicate_Subtree solution = new M652_Find_Duplicate_Subtree();
        solution.findDuplicateSubtrees(root);
    }

    Map<String, Integer> map = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    public void dfs(TreeNode root, List<TreeNode> result) {
        if (root == null) {
            return;
        }
        String str = serialize(root);
        int count = map.getOrDefault(str, 0);
        if (count == 1) {
            result.add(root);
        }
        map.put(str, count + 1);
        dfs(root.left, result);
        dfs(root.right, result);
    }

    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }
}
