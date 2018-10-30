package easy;

public class E7_Reverse_Integer {
    public static void main(String[] args) {
        System.out.println(new E7_Reverse_Integer().reverse(-153423646));
    }

    /**
     * 解法1，使用String反转数字。使用Long类型来判断Integer是否越界。可以pass，但是代码丑陋。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        String str = String.valueOf(x);
        int index = str.length() - 1;
        for (; index >= 0; index--) {
            if (str.charAt(index) != '0')
                break;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i > 0; i--) {
            sb.append(str.charAt(i));
        }
        if (str.charAt(0) != '-')
            sb.append(str.charAt(0));
        String absDigits = sb.toString();
        long digits = Long.parseLong(absDigits);
        if (digits > Integer.MAX_VALUE)
            return 0;
        if (str.charAt(0) == '-')
            absDigits = '-' + absDigits;
        return Integer.parseInt(absDigits);
    }
}
