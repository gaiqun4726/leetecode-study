package hard;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class H146_LRU_Cache {
    public static void main(String[] args) {
        LRUCache cache = new H146_LRU_Cache().new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

    /**
     * 未完成
     */
    class LRUCache1 {
        private int capacity;
        private Map<Integer, DualLinkedList> map;
        private DualLinkedList head;
        private DualLinkedList tail;

        public LRUCache1(int capacity) {
            this.capacity = capacity;
            map = new HashMap<Integer, DualLinkedList>();
            head = new DualLinkedList(null, null);
            head.prev = null;
            head.next = null;
            tail = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                moveToHead(map.get(key));
                map.put(key, head.next);
                return head.next.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (!map.containsKey(key) && map.size() < capacity) {
                putToHead(key, value);
                map.put(key, head.next);
            } else if (map.containsKey(key)) {
                moveToHead(map.get(key));
                map.put(key, head.next);
            } else {
                deleteLast();
                putToHead(key, value);
                map.put(key, head.next);
            }
        }

        private void putToHead(int key, int value) {
            DualLinkedList cur = new DualLinkedList(key, value);
            if (head.next != null) {
                head.next.prev = cur;
                cur.next = head.next;
                cur.prev = head;
                head.next = cur;
            } else {
                head.next = cur;
                cur.prev = head;
                cur.next = null;
                tail = cur;
            }
        }

        private void moveToHead(DualLinkedList cur) {
            if (head.next != cur) {
                tail = cur.prev;
                cur.prev.next = cur.next;
                if (cur.next != null)
                    cur.next.prev = cur.prev;
                cur.prev = head;
                cur.next = head.next;
                head.next.prev = cur;
                head.next = cur;
            }
        }

        private void deleteLast() {
            if (tail != head) {
                map.remove(tail.key);
                tail = tail.prev;
                tail.next.prev = null;
                tail.next = null;
            }
        }
    }

    class LRUCache {
        private int capacity;
        private LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<Integer, Integer>(this.capacity, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }


    class DualLinkedList {
        DualLinkedList prev;
        DualLinkedList next;
        Integer key;
        Integer value;

        DualLinkedList(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
