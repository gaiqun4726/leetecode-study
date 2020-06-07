package medium;

import java.util.HashMap;
import java.util.Map;

public class M567_String_Arrange {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "ba";
        System.out.println(new M567_String_Arrange().checkInclusion(s1, s2));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;
        int[] array1 = new int[26];
        int[] array2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            array1[s1.charAt(i) - 'a']++;
            array2[s2.charAt(i) - 'a']++;
        }
        if (isSame(array1, array2))
            return true;
        for (int i = s1.length(); i < s2.length(); i++) {
            array2[s2.charAt(i - s1.length()) - 'a']--;
            array2[s2.charAt(i) - 'a']++;
            if (isSame(array1, array2))
                return true;
        }
        return false;
    }

    public boolean isSame(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i])
                return false;
        }
        return true;
    }

    public boolean checkInclusion2(String s1, String s2) {
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : s1.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int valid = 0;
        int left = 0, right = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right++);
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
                if (right - left == s1.length()) {
                    return true;
                }
                c = s2.charAt(left++);
                if (needs.containsKey(c)) {
                    if (window.get(c).equals(needs.get(c))) {
                        valid--;
                    }
                    window.put(c, window.get(c) - 1);
                }
            }
        }
        return false;
    }
}
