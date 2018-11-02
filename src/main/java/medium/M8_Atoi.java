package medium;

public class M8_Atoi {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        boolean isNegative = false;
        int start = 0;
        int end = 0;
        while (end < str.length() && str.charAt(end) == ' ') {
            start++;
            end++;
        }
        if (end == str.length() || (!isDigit(str.charAt(end)) && str.charAt(end) != '-' && str.charAt(end) != '+'))
            return 0;
        else if (str.charAt(end) == '-' || str.charAt(end) == '+') {  //可能有-/+来标识有符号数
            isNegative = str.charAt(end) == '-';
            start++;
            end++;
        }
        while (end < str.length() && isDigit(str.charAt(end))) {
            end++;
        }
        int res = 0;
        for (int i = start; i < end; i++) {
            if (res > Integer.MAX_VALUE / 10 || Integer.MAX_VALUE - res * 10 < (str.charAt(i) - '0')) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + (str.charAt(i) - '0');
        }
        return isNegative ? 0 - res : res;
    }

    public boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    }
}
