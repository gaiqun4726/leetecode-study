package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2020/6/7
 *
 * 本题和567题基本一模一样，只是返回值不同。
 * 使用滑动窗口框架就能解决
 */
public class M438_Find_Anagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int valid = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right++);
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
//                if (right - left == p.length() && !s.substring(left, right).equals(p)) {
//                    result.add(left);
//                }
                // 答案可能有问题，没体现出“异位词”的特性
                if (right - left == p.length()) {
                    result.add(left);
                }
                c = s.charAt(left++);
                if (needs.containsKey(c)) {
                    if (window.get(c).equals(needs.get(c))) {
                        valid--;
                    }
                    window.put(c, window.get(c) - 1);
                }
            }
        }
        return result;
    }
}
