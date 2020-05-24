package com.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author gaiqun
 * @date 2020/4/12
 *
 * 总结
 *
 * 如果随着数字入队列，保存最大值及其个数，在所有最大元素弹出后，无法知道下一个最大值。
 * 因此需要保留所有最大值。
 *
 * 思路1：使用一个TreeMap维护数字及其个数。每插入或弹出元素，维护这个map。
 * 插入删除，由于需要调整堆，因此时间复杂度是O(logN)，最大值复杂度为O(1)。
 *
 * 思路2：如果维护排序的数据结构，无法把复杂度降下来。参考官方解答
 * 维护一个最大值辅助队列，这个队列是单调递减的。存储数据的队列就正常操作，主要是辅助队列的操作。
 * 辅助队列，每次入队发现队尾元素比当前元素小，则队尾元素出队。
 * 每次出队如果队首值小于辅助队列队首，则不影响当前最大值；如果等于辅助队列队首，则队首元素跟着一起弹出。
 * 查询、删除，时间复杂度O(1)。插入辅助队列如果需要移除队尾较小元素，会多操作；否则操作一次。平均下来时间复杂度O(1)。
 *
 */
public class Q59_Max_In_Queue {

    static class MaxQueue {

        private LinkedList<Integer> list;
        private TreeMap<Integer, Integer> map;

        public MaxQueue() {
            list = new LinkedList<>();
            map = new TreeMap<>((o1, o2) -> o2 - o1);
        }

        public int max_value() {
            return list.isEmpty() ? -1 : map.firstKey();
        }

        public void push_back(int value) {
            list.offerLast(value);
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        public int pop_front() {
            if (list.isEmpty()) {
                return -1;
            } else {
                int value = list.pollFirst();
                int count = map.get(value);
                if (count == 1) {
                    map.remove(value);
                } else {
                    map.put(value, count - 1);
                }
                return value;
            }
        }
    }

    static class MaxQueue2 {

        private LinkedList<Integer> list;
        private Queue<Integer> queue;

        public MaxQueue2() {
            list = new LinkedList<>();
            queue = new LinkedList<>();
        }

        public int max_value() {
            return list.isEmpty() ? -1 : list.getFirst();
        }

        public void push_back(int value) {
            queue.offer(value);
            while (!list.isEmpty() && list.getLast() < value) {
                list.pollLast();
            }
            list.offerLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int value = queue.poll();
            if (list.getFirst() == value) {
                list.pollFirst();
            }
            return value;
        }
    }
}
