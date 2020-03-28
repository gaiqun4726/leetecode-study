package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/3/28
 *
 * 总结
 *
 * 初始思路：判断每个单词是否是另外单词的后缀，如果是，把这个单词移除。最后计算剩下单词的总长度加上#的个数
 * 每两个单词判断是否互相为后缀，这样的话，如果是相同的单词，这两个单词都会被移除。因此约束后缀必须长度不同。
 * 最后用set去除重复元素。提交答案，30个样例过了28个，不知道为什么最后的没过。因此放弃这个思路。
 *
 * 可行的解法有三种：字典树、后缀检查、倒序排列。
 * 推荐的方法是后缀检查，又快又简洁。即对每个字符串查找所有可能的后缀，如果待压缩的数组里有这个后缀，则移除。
 */
public class M820_Word_Compress {

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        M820_Word_Compress solution = new M820_Word_Compress();
        System.out.println(solution.minimumLengthEncoding2(words));
        System.out.println(solution.minimumLengthEncoding3(words));
        System.out.println(solution.minimumLengthEncoding4(words));
    }

    /**
     * 初始想法，单词两两比较，把后缀单词移除。不可行
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        boolean[] tags = new boolean[words.length];
        for (int i = 0; i < words.length - 1; i++) {
            String wordA = words[i];
            String wordB = words[i + 1];
            if (isSuffix(wordA, wordB)) {
                tags[i + 1] = true;
            }
            if (isSuffix(wordB, wordA)) {
                tags[i] = true;
            }
        }
        int len = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (!tags[i]) {
                set.add(words[i]);
            }
        }
        for (String s : set) {
            len += s.length() + 1;
        }
        return len;
    }

    private boolean isSuffix(String a, String b) {
        if (b == null) {
            return true;
        }
        if (b.length() >= a.length()) {
            return false;
        }
        for (int i = a.length() - 1, j = b.length() - 1; j >= 0; i--, j--) {
            if (a.charAt(i) != b.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用字典树。
     * @param words
     * @return
     */
    public int minimumLengthEncoding2(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        // map必须以Trie为key，如果以数组的index为key，则相同字符串会被重复计算
        HashMap<Trie, Integer> map = new HashMap<>();
        Trie root = new Trie();
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            if (word == null) {
                continue;
            }
            // 每个字符串都从字典树根节点开始判断
            Trie cur = root;
            // 注意，要构建后缀字典树
            for (int i = word.length() - 1; i >= 0; i--) {
                cur = cur.get(word.charAt(i));
            }
            map.put(cur, j);
        }
        int len = 0;
        // 统计非后缀字符串的总长度
        for (Trie trie : map.keySet()) {
            int index = map.get(trie);
            if (trie.getCount() == 0) {
                len += words[index].length() + 1;
            }
        }
        return len;
    }

    static class Trie {

        // 字典树的范围要根据题意设计。这里是26个小写字母
        Trie[] children = new Trie[26];
        int count = 0;

        // 通过getCount判断是否是后缀字符串，如果getCount为0，则不是任何字符串的后缀串
        int getCount() {
            return count;
        }

        // 在查找时，更新字典树
        Trie get(char c) {
            int index = c - 'a';
            if (children[index] == null) {
                children[index] = new Trie();
                count++;
            }
            return children[index];
        }
    }

    /**
     * 对于每个单词，查找其所有可能的后缀是否在单词集合中，如果存在则移除。
     * @param words
     * @return
     */
    public int minimumLengthEncoding3(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (word == null) {
                continue;
            }
            for (int i = 1; i < word.length(); i++) {
                String suffix = word.substring(i);
                set.remove(suffix);
            }
        }
        int len = 0;
        for (String word : set) {
            if (word != null) {
                len += word.length() + 1;
            }
        }
        return len;
    }

    /**
     * 把单词反转，然后按照字典序排序。这样前面的元素如果是后面元素的前缀，他们必然相邻。
     * 一次遍历，把不是后面元素前缀的字符串计算长度，则得到最终结果。
     *
     * 理解了上面的想法，就不需要反转字符串，直接把字符串数组按照反转后的字符排序。
     * 一次遍历，把不是后面元素后缀的字符串统计长度。
     * @param words
     * @return
     */
    public int minimumLengthEncoding4(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        Arrays.sort(words, (o1, o2) -> {
            // 反转字符串的工具，使用StringBuilder来做
            String r1 = new StringBuilder(o1).reverse().toString();
            String r2 = new StringBuilder(o2).reverse().toString();
            return r1.compareTo(r2);
        });
        int len = 0;
        for (int i = 0; i < words.length - 1; i++) {
            if (!words[i + 1].endsWith(words[i])) {
                len += words[i].length() + 1;
            }
        }
        // 注意边界条件，把循环里没处理的最后一个字符串加入。
        len += words[words.length - 1].length() + 1;
        return len;
    }
}
