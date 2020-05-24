package com.example;

/**
 * @author gaiqun
 * @date 2020/4/28
 *
 * 总结
 *
 * 利用异或的特点。所有数字异或后，得到的结果就是两个只出现一次数字的异或，计这个结果为val。
 * 两个只出现一次的数字必然是不同的，存在异或后为1的位。从val中找到为1的位，表明两个只出现一次的数字在这一位不同。
 * 所有在这一位为0的数字分为一组，为1的分为另一组，这样两个出现一次的数字被分到两组中。组中其他元素都出现两次。
 * 两个组分别再异或一次，得到的结果就是两个只出现一次的数字。
 */
public class Q56_I_Number_Appear_Once {

    public static void main(String[] args) {
        //int[] nums = {1, 2, 2, 3, 5, 5, 7, 7};
        int[] nums = {1, 3};
        Q56_I_Number_Appear_Once solution = new Q56_I_Number_Appear_Once();
        int[] result = solution.singleNumbers(nums);
        System.out.println(result[0] + "," + result[1]);
    }

    public int[] singleNumbers(int[] nums) {
        int val = 0;
        for (int num : nums) {
            val ^= num;
        }
        int digit = 1;
        while ((val & digit) == 0) {
            digit <<= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & digit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[] {a, b};
    }
}
