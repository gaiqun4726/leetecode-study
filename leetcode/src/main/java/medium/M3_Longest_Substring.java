package medium;

import java.util.HashMap;
import java.util.Map;

public class M3_Longest_Substring {
    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(new M3_Longest_Substring().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLen = 0;
        while (end < s.length()) {
            char cur = s.charAt(end);
            if (map.containsKey(cur)) {
                maxLen = maxLen > end - start ? maxLen : end - start;
                int index = map.get(cur);
                for (int i = start; i <= index; i++)
                    map.remove(s.charAt(i));
                start = index + 1;
            }
            map.put(cur, end++);
        }
        maxLen = maxLen > end - start ? maxLen : end - start;
        return maxLen;
    }
}
