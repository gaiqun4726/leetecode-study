package easy;

/**
 * @author gaiqun
 * @date 2020/4/4
 *
 * 总结
 * 我想到要用等差数列求和的解法了。但是考虑到解答的复杂度，我还是选择暴力求解。
 */
public class E1103_Distribute_Candies {

    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int count = 1;
        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                if (candies >= count) {
                    result[i] += count;
                    candies -= count;
                    count++;
                } else {
                    result[i] += candies;
                    candies = 0;
                }
            }
        }
        return result;
    }
}
