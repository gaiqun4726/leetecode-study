package medium;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/3/22
 *
 * 总结
 *
 * 暴力求解，每次找到下一个可以存放的位置，时间复杂度空间复杂度太高了，不能用。
 *
 * 暴力求解之所以慢，是因为记录了每一步的操作，但是对于求解，这些步骤并不需要。
 * 暴力求解是最直观的思路，优化也建立在暴力求解的思路上。
 * 通过优化，只记录一个最终状态，而不去记录每一步并不需要的细节，进而提高速度。
 *
 * 排序，然后遍历，如果当前元素A[i]<=A[i-1]，则当前元素置为A[i-1]+1，并记录需要变更的步数。
 * 其实就是对于重复的元素，它需要找到下一个位置。我们就利用排序后的数组，存储它应该变成的数字。
 * 时间复杂度O(nlogn)。
 *
 * 更加优化的方法，不是一下就能想出来的。不断增长自己的经验，然后钻研才能获得。
 * 想不出最优解很正常，不要着急，要持续学习。
 */
public class M945_Min_Increment {

    public int minIncrementForUnique(int[] A) {
        int count = 0;
        Arrays.sort(A);
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int inc = A[i - 1] - A[i] + 1;
                count += inc;
                A[i] += inc;
            }
        }
        return count;
    }
}
