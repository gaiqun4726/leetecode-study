package easy;

/**
 * @author gaiqun
 * @date 2020/5/19
 *
 * 总结
 *
 * 验证删除一个字符后，是否构成回文串。
 * 如果尝试删除每个字符，判断剩余是否构成回文串，时间复杂度为O(N^2)。
 * 使用双指针，从两边向中间比较。如果发现两个字符不同，则尝试去掉左边的继续比较，和尝试去掉右边的继续比较。
 * 如果得到回文串则删除一个字符可以构成回文串，否则不行。时间复杂度O(N)。
 * 虽然代码行数不少，但逻辑很清晰
 */
public class E680_Verify_Palindrome {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        boolean leftValid = true;
        boolean rightValid = true;
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        int newLeft = left + 1;
        int newRight = right;
        while (newLeft < newRight) {
            if (chars[newLeft] != chars[newRight]) {
                leftValid = false;
                break;
            }
            newLeft++;
            newRight--;
        }
        if (leftValid) {
            return true;
        }
        newLeft = left;
        newRight = right - 1;
        while (newLeft < newRight) {
            if (chars[newLeft] != chars[newRight]) {
                rightValid = false;
                break;
            }
            newLeft++;
            newRight--;
        }
        return rightValid;
    }
}
