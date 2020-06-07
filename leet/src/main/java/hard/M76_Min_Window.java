package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2020/5/23
 *
 * 总结
 * 把T字符串每个字符出现的次数存入Map。
 * 在S中使用两个指针start, end。end从第一个在T中出现的字符开始，逐个向后遍历，直到找到个数覆盖T的子串。
 * 然后start向后移动到下一个T中出现字符的位置，end继续往后遍历。
 * 一边遍历，一边更新最短子串。
 * 时间复杂度为O(Max(S,T))。
 */
public class M76_Min_Window {

    public static void main(String[] args) {
        String s = "A";
        String t = "";
        M76_Min_Window solution = new M76_Min_Window();
        System.out.println(solution.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        int start = 0, end = 0;
        int size = s.length();
        String result = "";
        if (t.isEmpty() || s.isEmpty()) {
            return result;
        }
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int valid = 0;
        while (end < size) {
            char c = s.charAt(end);
            end++;
            if (tMap.containsKey(c)) {
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
                if (sMap.get(c).equals(tMap.get(c))) {
                    valid++;
                }
            }
            while (valid == tMap.size()) {
                String temp = s.substring(start, end);
                c = s.charAt(start);
                start++;
                if (!result.isEmpty()) {
                    result = result.length() < temp.length() ? result : temp;
                } else {
                    result = temp;
                }
                if (tMap.containsKey(c)) {
                    if (sMap.get(c).equals(tMap.get(c))) {
                        valid--;
                    }
                    sMap.put(c, sMap.getOrDefault(c, 0) - 1);
                }
            }
        }
        return result;
    }

    public String minWindow2(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int valid = 0;
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                c = s.charAt(left);
                left++;
                if (needs.containsKey(c)) {
                    // 注意Integer的比较要用equals。否则相同的整数也可能比较结果不同
                    if (window.get(c).equals(needs.get(c))) {
                        valid--;
                    }
                    window.put(c, window.get(c) - 1);
                }

            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
