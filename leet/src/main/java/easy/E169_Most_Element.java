package easy;

/**
 * @author gaiqun
 * @date 2020/3/13
 *
 * 总结
 *
 * 我的解法应该是时间空间复杂度最优的。时间复杂度O(n)，空间复杂度O(1)。
 *
 * 众数比其他任何数都多。通过一遍循环，每一对不同的数相互抵消，则最终未被抵消的就是众数。
 */
public class E169_Most_Element {

    public int majorityElement(int[] nums) {
        // 当前数量最多的数
        int element = 0;
        // 抵消后剩余的数字个数
        int count = 0;
        for (int num : nums) {
            // 如果之前的都被抵消了，则当前的数字是最多的
            if (count == 0) {
                element = num;
                count++;
            } else {
                // 如果当前元素和之前最多的数字相同，则数量增加
                if (element == num) {
                    count++;
                } else {
                    // 否则，抵消两个数字
                    count--;
                }
            }
        }
        return element;
    }
}
