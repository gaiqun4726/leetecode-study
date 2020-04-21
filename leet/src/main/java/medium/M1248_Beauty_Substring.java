package medium;

import java.util.LinkedList;

/**
 * @author gaiqun
 * @date 2020/4/21
 *
 * 总结
 *
 * 完美字符串是含有k个奇数的字符串。找到包含k个奇数的最长字符串，然后利用排列数公式，计算可能的子串个数。
 * 定义start, end表示字符串的起始，中间记下每个奇数所在的索引。然后循环移动start、end和索引。
 *
 * 一次遍历可以得到结果，时间复杂度看似是O(n)。但是奇数索引移位，可能每一次都需要移n次。因此时间复杂度可能是O(n^2)。
 *
 * 优化，因为我们每次只需要用到奇数索引的头和尾，因此记录奇数索引的使用双向队列。
 * 这样奇数索引的移动只需要poll头结点，offer尾结点就可以了，每次时间复杂度是O(1)。
 * 使用双向队列后，时间复杂度降为O(n)。
 */
public class M1248_Beauty_Substring {

    public static void main(String[] args) {
        M1248_Beauty_Substring solution = new M1248_Beauty_Substring();
        //int[] nums = {1, 1, 2, 1, 1};
        //int[] nums = {2,4,6};
        int[] nums = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        //int k = 3;
        //int k = 1;
        int k = 2;
        System.out.println(solution.numberOfSubarrays2(nums, k));
    }

    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
            return 0;
        }
        int len = nums.length;
        int[] bits = new int[k];
        int start = 0;
        int cur = 0;
        int i = 0;
        // 记下每个奇数所在的索引
        while (cur < len) {
            if (nums[cur] % 2 == 1) {
                bits[i] = cur;
                i++;
            }
            if (i == k) {
                break;
            }
            cur++;
        }
        // 无法找到k个奇数，则返回0
        if (i != k) {
            return 0;
        }
        // end是右边界的下一个位置。
        int end = bits[k - 1] + 1;
        int count = 0;
        // 每一轮循环，就是把start、end和奇数索引都向前移动
        while (end <= len) {
            while (end < len && nums[end] % 2 == 0) {
                end++;
            }
            // 排列数计算。注意end是右边界的下一个位置
            count += (end - bits[k - 1]) * (bits[0] - start + 1);
            // 更新每个索引
            start = bits[0] + 1;
            for (int j = 0; j < k - 1; j++) {
                bits[j] = bits[j + 1];
            }
            bits[k - 1] = end;
            end++;
        }
        return count;
    }

    public int numberOfSubarrays2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
            return 0;
        }
        int len = nums.length;
        LinkedList<Integer> bits = new LinkedList<>();
        int start = 0;
        int cur = 0;
        int i = 0;
        // 记下每个奇数所在的索引
        while (cur < len) {
            if (nums[cur] % 2 == 1) {
                bits.offerLast(cur);
                i++;
            }
            if (i == k) {
                break;
            }
            cur++;
        }
        // 无法找到k个奇数，则返回0
        if (i != k) {
            return 0;
        }
        // end是右边界的下一个位置。
        int end = bits.peekLast() + 1;
        int count = 0;
        // 每一轮循环，就是把start、end和奇数索引都向前移动
        while (end <= len) {
            while (end < len && nums[end] % 2 == 0) {
                end++;
            }
            // 排列数计算。注意end是右边界的下一个位置
            count += (end - bits.peekLast()) * (bits.peekFirst() - start + 1);
            // 更新每个索引
            start = bits.pollFirst() + 1;
            bits.offerLast(end);
            end++;
        }
        return count;
    }
}
