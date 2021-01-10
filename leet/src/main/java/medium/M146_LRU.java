package medium;

import java.util.*;

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

    public static void main(String[] args) {
        int[][] operators = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        M146_LRU solution = new M146_LRU();
        System.out.println(solution.LRU(operators, 3));
    }

    public int[] LRU(int[][] operators, int k) {
        // write code here
        Cache cache = new Cache(k);
        List<Integer> result = new ArrayList<>();
        for (int[] ops : operators) {
            int op = ops[0];
            if (op == 1) {
                cache.set(ops[1], ops[2]);
            }
            if (op == 2) {
                result.add(cache.get(ops[1]));
            }
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    static class Cache {
        private Map<Integer, Node> map = new HashMap<>();
        private LinkedList<Node> list = new LinkedList<>();
        private int k;

        public Cache(int k) {
            this.k = k;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            return node.get(key);
        }

        public void set(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                list.remove(node);
                node.set(value);
                list.addFirst(node);
            } else {
                Node node = new Node(key, value);
                if (list.size() >= k) {
                    Node tmp = list.removeLast();
                    map.remove(tmp.getKey());
                }
                list.addFirst(node);
                map.put(key, node);
            }
        }
    }

    static class Node {
        private int key;
        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public void set(int value) {
            this.value = value;
        }

        public int get(int key) {
            return this.value;
        }

        public int getKey() {
            return this.key;
        }
    }
}
