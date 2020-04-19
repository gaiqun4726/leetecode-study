package easy;

import common.TreeNode;
import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/4/19
 *
 * 总结
 *
 * 排序数组转BST树，树高平衡。
 * 每次从数组中间分开，然后递归构建子树。
 */
public class E108_Sorted_Array_To_BST {

    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        int mid = len / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, len));
        return root;
    }
}
