package easy;

import java.util.ArrayList;
import java.util.List;

public class E557_Reverse_Words_III {
    public static void main(String[] args) {
        System.out.println(new E557_Reverse_Words_III().reverseWords("Let's take LeetCode contest"));
    }

    /**
     * 题目说没有额外的空格，所以可以用下面的方法
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0)
            return s;
        String[] strs = s.split(" ");
        List<String> list = new ArrayList<String>();
        for (String str : strs) {
            char[] tmp = str.toCharArray();
            reverse(tmp, 0, tmp.length - 1);
            list.add(String.valueOf(tmp));
        }
        return String.join(" ", list);
    }

    /**
     * 如果有额外的空格，用下面的方法
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        int start = 0;
        int end = 1;
        char[] words = s.toCharArray();
        while (end < words.length) {
            if (words[end] != ' ') {
                end++;
            } else {
                reverse(words, start, end - 1);
                while (end < words.length && words[end] == ' ')
                    end++;
                start = end;
            }
        }
        if (start != end)
            reverse(words, start, end - 1);
        return String.valueOf(words);
    }

    /**
     * 反转字符数组任意区间的字符。
     * 注意两点：1. i的范围是start~start + (len / 2).
     * 2. 需要调换的两个坐标需要满足的条件是，i距离start的距离，等于j距离end的距离。
     *
     * @param words
     * @param start
     * @param end
     */
    public void reverse(char[] words, int start, int end) {
        int len = end - start + 1;
        for (int i = start; i < start + (len / 2); i++) {
            char tmp = words[i];
            words[i] = words[end + start - i];
            words[end + start - i] = tmp;
        }
    }
}
