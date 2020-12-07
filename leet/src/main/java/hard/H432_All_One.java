package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/4/5
 */
public class H432_All_One {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("b");
        allOne.dec("a");
        allOne.getMaxKey();
        allOne.getMinKey();
    }

    static class AllOne {

        // 记录键值对
        private Map<String, Integer> map;
        // 记录每种个数对应的key集合
        private Map<Integer, Set<String>> indexMap;
        // 双向队列，左边是最小值，右边是最大值
        private LinkedList<Integer> list;

        /** Initialize your data structure here. */
        public AllOne() {
            map = new HashMap<>();
            indexMap = new HashMap<>();
            list = new LinkedList<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            int count = 1;
            Set<String> set = new HashSet<>();
            if (map.containsKey(key)) {
                count = map.get(key);
                // 原个数集合里移除当前key
                set = indexMap.get(count);
                set.remove(key);
                if (indexMap.get(list.getFirst()).size() == 0) {
                    list.removeFirst();
                }
                count++;
            }
            map.put(key, count);
            // 新个数map里添加当前key
            if (indexMap.containsKey(count)) {
                set = indexMap.get(count);
                set.add(key);
            } else {
                set.add(key);
                indexMap.put(count, set);
            }
            // 更新最大最小值
            if (list.isEmpty() || count > list.getLast()) {
                list.addLast(count);
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if (map.containsKey(key)) {
                int count = map.get(key);
                Set<String> set = indexMap.get(count);
                set.remove(key);
                if (set.isEmpty()) {
                    if (list.getFirst() == count) {
                        list.removeFirst();
                    }
                    if (list.getLast() == count) {
                        list.removeLast();
                    }
                }
                if (count == 1) {
                    map.remove(key);
                } else {
                    map.put(key, count - 1);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (list == null || list.isEmpty()) {
                return "";
            }
            Set<String> set = indexMap.get(list.getLast());
            if (set == null || set.isEmpty()) {
                return "";
            } else {
                return set.iterator().next();
            }
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (list == null || list.isEmpty()) {
                return "";
            }
            Set<String> set = indexMap.get(list.getFirst());
            if (set == null || set.isEmpty()) {
                return "";
            } else {
                return set.iterator().next();
            }
        }
    }
}
