package easy;

/**
 * @author gaiqun
 * @date 2020/4/19
 */
public class E125_Verify_Palindrome {

    public static void main(String[] args) {
        E125_Verify_Palindrome solution = new E125_Verify_Palindrome();
        //String s = "A man, a plan, a canal: Panama";
        String s = ".,";
        System.out.println(solution.isPalindrome(s));
    }

    public boolean isPalindrome(String s) {
        s = s.toUpperCase();
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        int len = s.length();
        int i = 0, j = len - 1;
        while (i < j) {
            while (i < len && !isChar(s.charAt(i))) {
                i++;
            }
            while (j >= 0 && !isChar(s.charAt(j))) {
                j--;
            }
            if (i >= j) {
                break;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z');
    }
}
