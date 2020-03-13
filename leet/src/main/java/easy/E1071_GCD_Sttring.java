package easy;

/**
 * @author gaiqun
 * @date 2020/3/12
 *
 * 总结
 *
 * 直观思路：找到较短字符串的最大因子，然后判断是不是较长字符串的因子。如果不是，找较短字符串的第二大因子，以此类推
 * 通过判断字符串能不能被因子的长度整除，剪枝掉不需要的判断。最坏的时间复杂度是O(n^3)，实际要比这个好很多。
 */
public class E1071_GCD_Sttring {

    public static void main(String[] args) {
        E1071_GCD_Sttring solution = new E1071_GCD_Sttring();
        System.out.println(solution.gcdOfStrings("LEFT", "CODE"));
    }

    public String gcdOfStrings(String str1, String str2) {
        String gcdStr = "";
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return gcdStr;
        }
        // 找到较短字符串
        String shortStr = str1.length() < str2.length() ? str1 : str2;
        String longStr = str1.length() >= str2.length() ? str1 : str2;
        int shortLen = shortStr.length();
        int longLen = longStr.length();
        // 从因子就是较短字符串开始尝试，从大到小找因子
        for (int i = 1; i <= shortLen; i++) {
            // 因子必须能被长短字符串整除
            if (shortLen % i != 0 || longLen % (shortLen / i) != 0) {
                continue;
            }
            int step = shortLen / i;
            boolean tag = true;
            // 较短字符串需要能被因子除尽
            for (int j = 0; j < step; j++) {
                char c = shortStr.charAt(j);
                for (int k = j; k < shortLen; k += step) {
                    if (shortStr.charAt(k) != c) {
                        tag = false;
                        break;
                    }
                }
                if (!tag) {
                    break;
                }
            }
            if (!tag) {
                continue;
            }
            // 较长字符串需要能被因子除尽
            for (int j = 0; j < step; j++) {
                char c = shortStr.charAt(j);
                for (int k = j; k < longLen; k += step) {
                    if (longStr.charAt(k) != c) {
                        tag = false;
                        break;
                    }
                }
                if (!tag) {
                    break;
                }
            }
            if (tag) {
                gcdStr = shortStr.substring(0, step);
                break;
            }
        }
        return gcdStr;
    }

    public String gcdOfStrings2(String str1, String str2) {
        return null;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
