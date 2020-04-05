package hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/4/5
 *
 * 总结
 *
 * 思路1：使用PriorityQueue，每次把最不经常使用的结点放在队首，如果需要移除则从队首移除。
 * 小顶堆每次调整的时间复杂度为O(logN)，因此put复杂度为O(1)，get复杂度为O(logN)
 *
 * 思路2：HashMap可以O(1)的时间查找。去除最不经常使用的元素也需要O(1)的时间完成。
 * 如果使用队列，把最不经常使用的元素放在队首，就可以实现O(1)的时间去除元素。
 * 但是频次增加时，需要把元素放到队列的里频次的合适位置。如果使用支持排序的结构复杂度又上来了。
 * 因此考虑每个频次建立一个链表，每次同频次最新的元素都放到队尾，删除也是从队首删，这样复杂度就降下来了。
 *
 * 这题实在是太恶心了。无论什么思路，代码都是大量且易错的，没有什么减少代码量的技巧。基本不会在面试的时候写这个题。
 */
public class H460_LFUCache {

    public static void main(String[] args) {
        //LFUCache cache = new LFUCache(3);
        //cache.put(1, 1);
        //cache.put(2, 2);
        //cache.put(3, 3);
        //cache.put(4, 4);
        //cache.get(4);
        //cache.get(3);
        //cache.get(2);
        //cache.get(1);
        //cache.put(5, 5);
        //cache.get(1);
        //cache.get(2);
        //cache.get(3);
        //cache.get(4);
        //cache.get(5);

        LFUCache2 cache = new LFUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }

    static class LFUCache {

        private int capacity;
        private Map<Integer, Node> map;
        private Queue<Node> queue;
        // 最近访问用累加数字表示。开始用时间戳，但是long在比较函数中有问题
        private int idx = 1;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            // 小顶堆用PriorityQueue实现
            queue = new PriorityQueue<>((o1, o2) -> {
                if (o1.count == o2.count) {
                    return o1.idx - o2.idx;
                }
                return o1.count - o2.count;
            });
        }

        public int get(int key) {
            int value = -1;
            if (map.containsKey(key)) {
                Node node = map.get(key);
                value = node.value;
                queue.remove(node);
                node.count = node.count + 1;
                node.idx = idx++;
                map.put(key, node);
                queue.offer(node);
            }
            return value;
        }

        public void put(int key, int value) {
            if (capacity <= 0) {
                return;
            }
            if (map.containsKey(key)) {
                Node node = map.get(key);
                queue.remove(node);
                node.count = node.count + 1;
                node.value = value;
                node.idx = idx++;
                map.put(key, node);
                queue.offer(node);
            } else {
                if (map.size() == capacity) {
                    Node node = queue.poll();
                    map.remove(node.key);
                }
                Node node = new Node();
                node.key = key;
                node.value = value;
                node.count = 1;
                node.idx = idx++;
                map.put(key, node);
                queue.offer(node);
            }
        }

        static class Node {

            private int key;
            private int value;
            private int count;
            private int idx;
        }
    }

    static class LFUCache2 {

        private int capacity;
        private Map<Integer, Node> map;
        // 频次的map，值是存储元素的双向链表
        private Map<Integer, LinkedList<Node>> freqMap;
        // 维护最小频次
        private int minFreq;

        public LFUCache2(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            freqMap = new HashMap<>();
            minFreq = 0;
        }

        /**
         * get或put，都需要维护构造函数里的几个变量的值
         * @param key
         * @return
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 从当前频次链表移除当前元素
            Node node = map.get(key);
            LinkedList<Node> list = freqMap.get(node.freq);
            list.remove(node);
            // 链表空了，则需要从频次map里移除，并且更新最小频次
            if (list.isEmpty()) {
                // 如果当前元素所属的频次就是最小频次，则最小频次增加；否则最小频次不变
                if (minFreq == node.freq) {
                    minFreq++;
                }
                freqMap.remove(node.freq);
            }
            // 设置元素的访问频次
            node.freq++;
            // 新的频次链表队尾加入新元素
            if (freqMap.containsKey(node.freq)) {
                list = freqMap.get(node.freq);
                list.addLast(node);
            } else {
                list = new LinkedList<>();
                list.addLast(node);
                freqMap.put(node.freq, list);
            }
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity <= 0) {
                return;
            }
            // 已有元素，更新频次链表。同样的，更新最小频次
            if (map.containsKey(key)) {
                Node node = map.get(key);
                LinkedList<Node> list = freqMap.get(node.freq);
                list.remove(node);
                if (list.isEmpty()) {
                    if (minFreq == node.freq) {
                        minFreq++;
                    }
                    freqMap.remove(node.freq);
                }
                node.freq++;
                node.value = value;
                if (freqMap.containsKey(node.freq)) {
                    list = freqMap.get(node.freq);
                    list.addLast(node);
                } else {
                    list = new LinkedList<>();
                    list.addLast(node);
                    freqMap.put(node.freq, list);
                }
            } else {
                // 元素不存在，且已达到缓存容量，淘汰最小频次链表的第一个元素
                if (map.size() == capacity) {
                    LinkedList<Node> list = freqMap.get(minFreq);
                    Node node = list.pollFirst();
                    map.remove(node.key);
                    if (list.isEmpty()) {
                        freqMap.remove(node.freq);
                    }
                }
                // 新插入元素后，最小频次一定是1.
                minFreq = 1;
                Node node = new Node();
                node.key = key;
                node.value = value;
                node.freq = 1;
                if (freqMap.containsKey(node.freq)) {
                    LinkedList<Node> list = freqMap.get(node.freq);
                    list.addLast(node);
                } else {
                    LinkedList<Node> list = new LinkedList<>();
                    list.addLast(node);
                    freqMap.put(node.freq, list);
                }
                map.put(key, node);
            }
        }

        static class Node {

            private int key;
            private int value;
            private int freq;
        }
    }
}
