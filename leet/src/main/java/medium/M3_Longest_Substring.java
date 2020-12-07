package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 总结
 *
 * 最长连续子串。
 *
 * 使用双指针，start记录最长连续子串的开始，end记录结束。
 * end向前走，当出现重复时，start移动到重复字符的后面。然后重复上述过程
 * 为了知道重复时start应到的位置，用一个hashmap记录当前子串中每个字符所在的索引，
 * 并在start移动时，把start原位置到新位置直接的字符都从map中移除。
 */
public class M3_Longest_Substring {

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new M3_Longest_Substring().lengthOfLongestSubstring4(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
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

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0, len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int longest = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
                longest = Math.max(longest, map.size());
            } else {
                int index = map.get(c);
                for (int j = start; j <= index; j++) {
                    map.remove(s.charAt(j));
                }
                map.put(c, i);
                start = index + 1;
            }
        }
        return longest;
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null) {
            return 0;
        }
        Map<Character, Integer> window = new HashMap<>();
        int len = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char t = s.charAt(left++);
                window.put(t, window.getOrDefault(t, 0) - 1);
            }
            len = Math.max(len, right - left);
        }
        return len;
    }

    public int lengthOfLongestSubstring4(String s) {
        Set<Character> set = new HashSet<>();
        int left =0, right =0;
        int len = s.length();
        int maxLen = 0;
        while(right<len) {
            char c = s.charAt(right);
            while(right<len && !set.contains(c)) {
                set.add(c);
                right++;
                c = s.charAt(right);
            }
            maxLen = Math.max(maxLen, right-left);
            while(right<len && s.charAt(left) != c && left < right) {
                set.remove(s.charAt(left));
                left++;
            }
            right++;
        }
        return maxLen;
    }
}
