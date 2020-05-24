package medium;

/**
 * @author gaiqun
 * @date 2020/4/17
 *
 * 总结
 *
 * 模拟jump的过程。用一个数组表示上次能够跳达的位置标记。如果可以跳达，下次从那个位置继续跳。
 * 所有可能的位置都跳过后，查看最终位置是否被标记。
 *
 * 官方解答，每个位置算出最远可达的位置。遍历所有位置，如果不在可达范围内，则最终不可达；否则更新最远可达未知。
 * 时间复杂度O(n)，空间复杂度O(1)。
 *
 * 也可以使用队列保存下一步可达的位置。不过有可能会重复走之前走过的路线。
 *
 * 不用回溯的原因是，回溯一般用来求全部解，求整个过程。本题只是要求是否可达，用回溯得不偿失。
 *
 * 求解问题不要想复杂了。
 */
public class M55_Jump_Game {

    public static void main(String[] args) {
        M55_Jump_Game solution = new M55_Jump_Game();
        //int[] nums = {2,3,1,1,4};
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(solution.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        int[] flags = new int[len];
        flags[0] = 1;
        for (int i = 0; i < len; i++) {
            if (flags[i] == 0) {
                continue;
            }
            int steps = nums[i];
            for (int j = 1; j <= steps; j++) {
                if (i + j < len) {
                    flags[i + j] = 1;
                }
            }
        }
        return flags[len - 1] == 1;
    }

    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        int boarder = 0;
        for (int i = 0; i < len; i++) {
            if (i > boarder) {
                return false;
            }
            boarder = Math.max(boarder, i + nums[i]);
        }
        return true;
    }
}
