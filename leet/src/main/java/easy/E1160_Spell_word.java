package easy;

import java.util.HashMap;

/**
 * @author gaiqun
 * @date 2020/3/17
 *
 * 总结
 *
 * 最开始的思路是用哈希表来做，但是想到每次都需要另一个哈希表，感觉空间复杂度有点高，所以没写。
 *
 * 想用字符串的替换来求解。结果执行速度特别慢。分析原因，字符串的替换操作非常耗时，比哈希表慢多了。
 *
 * 所以还是用哈希表来做吧。
 *
 * 注意字符串、对象的拷贝需要用深拷贝。"=="用来判断地址是否相同，"equals"用来判断内容是否相同。
 *
 * 关键点：深拷贝，HashMap.clone()，
 */
public class E1160_Spell_word {

    public static void main(String[] args) {
        String[] words = {"boygirdlggnh"};
        String chars = "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp";
        E1160_Spell_word solution = new E1160_Spell_word();
        System.out.println(solution.countCharacters2(words, chars));
    }

    public int countCharacters(String[] words, String chars) {
        int count = 0;
        for (String word : words) {
            // 这里String传递的是值，不是引用。String在函数调用时和原始类型一样，都是传值。
            if (canSpell(word, chars)) {
                count += word.length();
            }
        }
        return count;
    }

    private boolean canSpell(String word, String chars) {
        for (char c : word.toCharArray()) {
            int index = chars.indexOf(c);
            if (index < 0) {
                return false;
            } else {
                // 字符串没有remove函数；replace函数只能替换字符串。
                chars = chars.replaceFirst(c + "", "");
            }
        }
        return true;
    }

    public int countCharacters2(String[] words, String chars) {
        HashMap<Character, Integer> map = new HashMap<>();
        // 用map存储每个元素的出现次数
        for (char c : chars.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        int count = 0;
        for (String word : words) {
            // 对象的函数调用传递的是引用，因此需要用深拷贝。HashMap才有深拷贝函数，所以不能用Map。
            if (canSpell(word, (HashMap<Character, Integer>) map.clone())) {
                count += word.length();
            }
        }
        return count;
    }

    private boolean canSpell(String word, HashMap<Character, Integer> map) {
        for (char c : word.toCharArray()) {
            Character charc = c;
            // 字典里余下的字符不够，则不能够拼写字符串
            if (!map.containsKey(charc) || map.get(charc) == 0) {
                return false;
            } else {
                map.put(charc, map.get(charc) - 1);
            }
        }
        return true;
    }
}
