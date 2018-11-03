package easy;

public class E9_Palindrome_Number {
    /**
     * 通过比较一半的数字是否是另一半的回文，来解决该问题。
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) //负数，10的倍数都不是回文
            return false;
        int reverseNum = 0;
        while (x > reverseNum) {
            int digit = x % 10;
            reverseNum = reverseNum * 10 + digit;
            x /= 10;
        }
        return x == reverseNum || x == reverseNum / 10; //如果是奇数位，则需要恢复一下
    }
}
