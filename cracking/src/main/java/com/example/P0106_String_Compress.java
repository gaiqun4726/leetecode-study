package com.example;

/**
 * @author gaiqun
 * @date 2020/3/16
 *
 * 总结
 *
 * 思路比较清晰，一次遍历就可以。
 *
 * 关键注意边界条件。题目描述不必源字符串短，则返回原字符串，因此大于等于原串长度的压缩都要抛弃。
 * 再有，最后一个字符在循环结束的时候没被加上，退出循环以后需要补上。
 */
public class P0106_String_Compress {

    public String compressString(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        StringBuilder builder = new StringBuilder();
        char cur = S.charAt(0);
        int count = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == cur) {
                count++;
            } else {
                builder.append(cur);
                builder.append(count);
                cur = S.charAt(i);
                count = 1;
            }
        }
        builder.append(cur);
        builder.append(count);
        return builder.length() >= S.length() ? S : builder.toString();
    }
}
