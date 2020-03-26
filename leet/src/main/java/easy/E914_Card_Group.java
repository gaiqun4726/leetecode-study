package easy;

import java.util.HashMap;
import java.util.Map;

public class E914_Card_Group {

    /**
     * 暴力法，尝试所有可能整除元素个数的数字。
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 0)
            return false;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < deck.length; i++) {
            int key = deck[i];
            if (map.containsKey(key)) {
                int count = map.get(key);
                map.put(key, ++count);
            } else {
                map.put(key, 1);
            }
        }
        Object[] keys = map.keySet().toArray();
        boolean res = false;
        for (int key = 2; key <= deck.length; key++) {
            int count = 0;
            for (int j = 0; j < keys.length; j++) {
                if (map.get((Integer) keys[j]) % key == 0)
                    count++;
            }
            if (count == keys.length) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * 其实就是求各个卡牌个数的最大公约数是不是大于等于2.
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX2(int[] deck) {
        if(deck==null||deck.length==0) {
            return false;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i: deck) {
            if(map.containsKey(i)) {
                map.put(i, map.get(i)+1);
            } else {
                map.put(i, 1);
            }
        }
        int val = map.get(deck[0]);
        for(int j: map.values()) {
            val = gcd(j, val);
        }
        return val>=2;
    }
    int gcd(int a, int b) {
        if(b==0) {
            return a;
        }
        return gcd(b, a%b);
    }
}
