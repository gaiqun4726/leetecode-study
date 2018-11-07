package medium;

public class M238_Product_Except_Self {
    /**
     * 题目要求：1.时间复杂度O(n) 2. 不能用除法
     * 所以只能用乘法。每一个位置是其他位置的乘积，就是说是左边的乘积乘以右边的乘积。
     * 所以可以使用两个数组，分别保存左侧的乘积和右侧的乘积。然后用两个数组中左右的部分
     * 乘起来构造乘积。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int count = 1;
        for (int i = 0; i < len; i++) {
            left[i] = count * nums[i];
            count = left[i];
        }
        count = 1;
        for (int i = len - 1; i >= 0; i--) {
            right[i] = count * nums[i];
            count = right[i];
        }
        for (int i = 1; i < len - 1; i++) {
            nums[i] = left[i - 1] * right[i + 1];
        }
        nums[0] = right[1];
        nums[len - 1] = left[len - 2];
        return nums;
    }

    /**
     * 只用一个结果数组。思路和上边是一样的，但是乘积都保存在一个数组中。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) { // 从第二个位置开始，每个位置都保存了左侧所有元素的乘积。
            res[i] = res[i - 1] * nums[i - 1];
        }
        int count = 1;
        for (int i = len - 1; i >= 0; i--) { // 从倒数第二个元素开始，每个元素乘上右边元素的乘积
            res[i] *= count;
            count *= nums[i];
        }
        return res;
    }
}
