package medium;

/**
 * 总结
 *
 * 时隔一年，这道题还做这么费劲，服了。
 */
public class M8_Atoi {

    public static void main(String[] args) {
        M8_Atoi solution = new M8_Atoi();
        System.out.println(solution.myAtoi3("42"));
    }

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean isNegative = false;
        int start = 0;
        int end = 0;
        while (end < str.length() && str.charAt(end) == ' ') {
            start++;
            end++;
        }
        if (end == str.length() || (!isDigit(str.charAt(end)) && str.charAt(end) != '-' && str.charAt(end) != '+')) {
            return 0;
        } else if (str.charAt(end) == '-' || str.charAt(end) == '+') {  //可能有-/+来标识有符号数
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

    private final static String POSITIVE_LIMIT = "2147483647";
    private final static String NEGATIVE_LIMIT = "2147483648";

    public int myAtoi2(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        char c = str.charAt(0);
        if (!isFlag(c) && !isDigit2(c)) {
            return 0;
        }
        boolean isPositive = true;
        if (c == '-') {
            isPositive = false;
            str = str.substring(1);
        }
        if (c == '+') {
            str = str.substring(1);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (isDigit2(c)) {
                builder.append(c);
            } else {
                break;
            }
        }
        str = builder.toString();
        while (!str.isEmpty() && str.charAt(0) == '0') {
            str = str.substring(1);
        }
        if (str.isEmpty()) {
            return 0;
        }
        if (str.length() > POSITIVE_LIMIT.length()) {
            return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else if (str.length() == POSITIVE_LIMIT.length()) {
            if (isPositive) {
                return str.compareTo(POSITIVE_LIMIT) >= 0 ? Integer.MAX_VALUE : Integer.parseInt(str);
            } else {
                return str.compareTo(NEGATIVE_LIMIT) >= 0 ? Integer.MIN_VALUE : -Integer.parseInt(str);
            }
        } else {
            if (isPositive) {
                return Integer.parseInt(str);
            } else {
                return -Integer.parseInt(str);
            }
        }
    }

    private boolean isDigit2(char c) {
        return c - '0' >= 0 && c - '0' <= 9;
    }

    private boolean isFlag(char c) {
        return c == '-' || c == '+';
    }

    /**
     * 背下来这道题的解法
     * @param str
     * @return
     */
    public int myAtoi3(String str) {
        if (str.isEmpty() || str.trim().isEmpty()) {
            return 0;
        }
        // trim去掉首位的空格
        str = str.trim();
        int len = str.length();
        int index = 0;
        // 标志位记录正负值
        boolean isPositive = true;
        char c = str.charAt(index);
        if (c == '-') {
            index++;
            isPositive = false;
        } else if (c == '+') {
            index++;
        } else if (!Character.isDigit(c)) {
            return 0;
        }
        int ans = 0;
        // 使用Character.isDigit判断是不是数字
        while (index < len && Character.isDigit(str.charAt(index))) {
            // 整数不能越界。不能直接用Integer.parseInt。也不能直接用大于Integer.MAX_VALUE的数字比较
            // 整数是由字符串一次次迭代计算出的。公式为ans=ans*10+digit>Integer.MAX。
            // 因此在循环时，只要每一次循环都判断ans>(Integer.MAX_VALUE-digit)/10，超过则大于最大值
            // 为什么这里只用判断绝对值是否大于Integer.MAX_VALUE就行，不用判断Integer.MIN_VALUE。
            // 因为大于最大正整型的数，绝对值最新就是最小负整数的绝对值。
            // 因此只要满足ans>(Integer.MAX_VALUE-digit)/10，就根据正负号直接取整数的最大最小值即可。
            if (ans > (Integer.MAX_VALUE - (str.charAt(index) - '0')) / 10) {
                return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // 通过这种累积的方式，可以避免开头有大量0的情形。开头的0在累加时会作为0不会影响最终生成的整数
            ans = ans * 10 + (str.charAt(index) - '0');
            index++;
        }
        // 根据正负值符号返回生成的整数
        return isPositive ? ans : -ans;
    }
}
