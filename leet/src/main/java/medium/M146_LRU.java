package medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2020/5/25
 *
 * 总结
 * 双向链表+hash求解。
 * 其实更快的方法，是Map的value存的是双线链表中节点的指针，这样每次查找链表中元素时就不用顺序查找了。
 * 但是，这样要自己实现双向链表和其中的操作，太麻烦了。所以直接用了LinkedList。
 */
public class M146_LRU {

    static class LRUCache {

        private Map<Integer, Integer> map;
        private LinkedList<Integer> list;
        private int capacity, cnt;

        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.list = new LinkedList<>();
            this.capacity = capacity;
            this.cnt = 0;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            list.remove(new Integer(key));
            list.addFirst(key);
            return map.get(key);
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                list.remove(new Integer(key));
                list.addFirst(key);
                map.put(key, value);
            } else {
                if (cnt == capacity) {
                    int last = list.removeLast();
                    map.remove(last);
                    list.addFirst(key);
                    map.put(key, value);
                } else {
                    list.addFirst(key);
                    map.put(key, value);
                    cnt++;
                }
            }
        }
    }
}
