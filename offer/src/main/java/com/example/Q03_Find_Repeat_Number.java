package com.example;

/**
 * @author gaiqun
 * @date 2020/4/14
 */
public class Q03_Find_Repeat_Number {

    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        int[] array = new int[len];
        int repeat = -1;
        for (int num : nums) {
            array[num]++;
            if (array[num] == 2) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }
}
