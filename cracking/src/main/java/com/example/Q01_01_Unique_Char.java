package com.example;

/**
 * 不使用额外的数据结构，就是不让用Map，Set。
 * 使用数组，其实和Map是一个思路，只不过自己实现一个数组
 * 字符集的范围需要确认，如果是ASCII则是256个字符。其实如果是其他字符集也不知道怎么做
 * 每个字符对应的数值，直接把char转成int就可以得到。
 * @author gaiqun
 * @date 2020/3/6
 */
public class Q01_01_Unique_Char {
    public boolean isUnique(String astr) {
        boolean[] chars = new boolean[256];
        for(int i=0;i<astr.length();i++) {
            int pos = astr.charAt(i);
            if(chars[pos]) {
                return false;
            }
            chars[pos] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        Q01_01_Unique_Char solution = new Q01_01_Unique_Char();
        System.out.println(solution.isUnique("abcd"));
    }
}
