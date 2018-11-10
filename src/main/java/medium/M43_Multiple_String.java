package medium;

public class M43_Multiple_String {
    public static void main(String[] args) {
        System.out.println(new M43_Multiple_String().multiply("25", "0"));
        System.out.println(Integer.MAX_VALUE);
    }

    /**
     * 可以改进的地方。
     * 1.两个数相乘，其最大位数是两个数位数之和。所以可以用一个数组存放结果
     * 2.不用区分两个数哪个位数多，因为乘法次数都是一样的，满足交换率
     * 3.注意乘数可能为0的情形
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        String longNum = len1 > len2 ? num1 : num2;
        String shortNum = len1 > len2 ? num2 : num1;
        if ((len1 == 1 && num1.charAt(0) == '0') || (len2 == 1 && num2.charAt(0) == '0'))
            return "0";
        int len = shortNum.length();
        String res = "0";
        StringBuilder zero = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            res = add(multipleDigit(longNum, shortNum.charAt(i)) + zero, res);
            zero.append("0");
        }
        return res;
    }

    public String multipleDigit(String str, char digit) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            int tmp = (c - '0') * (digit - '0') + carry;
            sb.append(tmp % 10);
            carry = tmp / 10;
        }
        if (carry != 0)
            sb.append(carry);
        return sb.reverse().toString();
    }

    public String add(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int len1 = str1.length();
        int len2 = str2.length();
        String longNum = len1 > len2 ? str1 : str2;
        int len = len1 > len2 ? len1 : len2;
        int i = 0;
        for (; i < len1 && i < len2; i++) {
            int tmp = (str1.charAt(len1 - i - 1) - '0') + (str2.charAt(len2 - i - 1) - '0') + carry;
            sb.append(tmp % 10);
            carry = tmp / 10;
        }
        while (i < len) {
            int tmp = (longNum.charAt(len - i - 1) - '0') + carry;
            sb.append(tmp % 10);
            carry = tmp / 10;
            i++;
        }
        if (carry != 0)
            sb.append(carry);
        return sb.reverse().toString();
    }
}
