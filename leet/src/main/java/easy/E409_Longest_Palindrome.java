package easy;

/**
 * @author gaiqun
 * @date 2020/3/19
 *
 * 总结
 *
 * 用了数组记录每个字符出现的次数，其实跟hash是一个意思，不过数据结构简单，能快一点
 * 注意回文串中奇数字符最多出现一个。偶数对的个数*2+奇数字符=回文串长度。
 */
public class E409_Longest_Palindrome {

    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] arr = new int[256];
        for (char c : s.toCharArray()) {
            arr[c]++;
        }
        int evenCount = 0;
        boolean hasOdd = false;
        for (int i = 0; i < arr.length; i++) {
            evenCount += arr[i] / 2;
            if (arr[i] % 2 != 0) {
                hasOdd = true;
            }
        }
        return hasOdd ? evenCount * 2 + 1 : evenCount * 2;
    }
}
