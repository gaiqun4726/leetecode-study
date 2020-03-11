package medium;

/**
 * @author gaiqun
 * @date 2020/3/11
 *
 * 总结
 *
 * 第一遍求解：
 *
 * 在题目分析阶段，没有形成清晰的实现思路，在实现细节上没有考虑周全。导致在实现阶段，花时间回头去分析问题，没有达到高效实现的目标
 *
 * 解题思路是，找到分隔数组的两个位置点。通过二重循环，每次固定一侧的位置点，不断尝试另一个位置点。时间复杂度为O(n^2)
 *
 * 边界条件是，中间数组至少有一个元素，边界点的位置需满足i+1<j.
 *
 * 最优解：
 *
 * 这道题目归类为"line sweep"，即"线性扫描"，一次扫描时间复杂度O(n)就可以求解
 *
 * 三等分，意味着每一部分的和都是sum/3。通过累加，找到和为sum/3的地方即为第一个分隔点；然后找到第二个分隔点。
 *
 * 如果有多个累加为sum/3的位置，选第一个就可以了。因为如果有多个i0,i1，那么i0到i1直接的累加和必然为0，i0到i1归为
 * 哪一部分都是可以的。题目要求判断能不能分割，而不是求出分割，所以判断是否有解就可以了。
 *
 * 这道题目第一次解走了弯路，是因为完全沉入到用程序求解的思路了。其实考虑数学特性，可以有助解题。
 */
public class M1013_Split_Array {

    public boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int sum = 0;
        for (int value : A) {
            sum += value;
        }
        int startSum = 0;
        for (int i = 0; i < A.length - 2; i++) {
            startSum += A[i];
            int endSum = 0;
            for (int j = A.length - 1; j > i + 1; j--) {
                endSum += A[j];
                int midSum = sum - startSum - endSum;
                if (midSum == startSum && midSum == endSum) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canThreePartsEqualSum2(int[] A) {
        // 能分成三组
        if (A == null || A.length < 3) {
            return false;
        }
        // 求和
        int sum = 0;
        for (int value : A) {
            sum += value;
        }
        // 和能被3整除
        if (sum % 3 != 0) {
            return false;
        }
        // 找第一个分割点
        int i = 0;
        int target = sum / 3;
        int tempSum = 0;
        while (i < A.length - 2) {
            tempSum += A[i];
            if (tempSum == target) {
                break;
            }
            i++;
        }
        if (tempSum != target) {
            return false;
        }
        // 找第二个分割点
        tempSum = 0;
        int j = i+1;
        while (j < A.length - 1) {
            tempSum += A[j];
            if (tempSum == target) {
                break;
            }
            j++;
        }
        // 找到两个分割点则能三等分，否则不能
        return tempSum == target;
    }
}

