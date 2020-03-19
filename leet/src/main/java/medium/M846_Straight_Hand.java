package medium;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author gaiqun
 * @date 2020/3/19
 *
 * 总结
 *
 * 利用TreeMap排序的map，计算每个数字的出现次数并排序。每次找到一手顺子，就把对应的数字减1，数字降为0就移除。
 * 当无法构成一手顺子的时候，就返回false。TreeMap排序，复杂度为O(nlogn)，后面不再需要排序，总时间复杂度为O(nlogn)。
 * 需要掌握TreeMap的数据结构，以及常用方法。
 */
public class M846_Straight_Hand {

    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return false;
        }
        int len = hand.length;
        if (len % W != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> o));
        for (int h : hand) {
            if (map.containsKey(h)) {
                map.put(h, map.get(h) + 1);
            } else {
                map.put(h, 1);
            }
        }
        // 循环条件是map不空
        while (!map.isEmpty()) {
            // 排序里的第一个数字
            int key = map.firstKey();
            // 尝试构建一手顺子
            for (int i = 0; i < W; i++) {
                if (map.containsKey(key + i)) {
                    int count = map.get(key + i);
                    count--;
                    if (count == 0) {
                        // 没有剩余可用，则移除key
                        map.remove(key + i);
                    } else {
                        map.put(key + i, count);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
