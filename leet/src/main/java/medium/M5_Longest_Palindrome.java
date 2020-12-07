package medium;

public class M5_Longest_Palindrome {
    public static void main(String[] args) {
//        String s = "babbadd";
        String s = "abac";
        System.out.println(new M5_Longest_Palindrome().longestPalindrome2(s));
    }

    /**
     * 中心扩展法。注意回文长度可能是奇数或偶数，所以扩展时中心可能是一个或两个。
     * 时间复杂度O(n^2)，空间复杂度是常数。应该是O(n^2)的解法中最好的
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        int start = 0;
        int end = 0;
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] positions1 = {i, i};
            int[] positions2 = {i, i + 1};
            int count1 = longestCount(s, positions1);
            int count2 = longestCount(s, positions2);
            if (count1 > maxCount) {
                maxCount = count1;
                start = positions1[0];
                end = positions1[1];
            }
            if (count2 > maxCount) {
                maxCount = count2;
                start = positions2[0];
                end = positions2[1];
            }
        }
        return s.substring(start, end + 1);
    }

    public int longestCount(String s, int[] positions) {
        int len = s.length();
        while (positions[0] > -1 && positions[1] < len) {
            if (s.charAt(positions[0]) == s.charAt(positions[1])) {
                positions[0]--;
                positions[1]++;
            } else {
                break;
            }
        }
        positions[0]++;
        positions[1]--;
        return positions[1] - positions[0] + 1;
    }

    public String longestPalindrome2(String s) {
        String res = "";
        for(int i=0;i<s.length();i++) {
            String str1 = getPal1(s, i);
            String str2 = getPal2(s, i);
            res = res.length()>str1.length()?res:str1;
            res = res.length()>str2.length()?res:str2;
        }
        return res;
    }

    private String getPal1(String s, int index) {
        int left = index, right = index;
        while(left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    private String getPal2(String s, int index) {
        int left = index, right = index+1;
        while(left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }
}
