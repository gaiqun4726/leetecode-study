package medium;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class M945_Minimum_Move {
    public static void main(String[] args) {
        int[] array = {3, 2, 1, 2, 1, 7};
        System.out.println(new M945_Minimum_Move().minIncrementForUnique3(array));
    }

    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length == 0 || A.length == 1)
            return 0;
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i : A) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
        }
        int moves = 0;
        Set<Integer> set = new HashSet<>();
        set.addAll(map.keySet());
        for (int i : set) {
            int count = 1;
            for (int j = 1; j < map.get(i); j++) {
                while (map.containsKey(i + count))
                    count++;
                moves += count;
                map.put(i + count, 1);
            }
        }
        return moves;
    }

    /**
     * Exceed time limit
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique2(int[] A) {
        if (A == null || A.length == 0 || A.length == 1)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
        }
        int moves = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : map.keySet()) {
            int count = map.get(i);
            int inc = 0; // 每次inc清零
            for (int j = 0; j < count; j++) {
                while (set.contains(i + inc))
                    inc++;  // 这里，每次都要从开始位置找到可用位置，会导致超时。有很多重复计算。直接保留最终位置可以避免重复计算。
                set.add(i + inc);
                moves += inc;
            }
        }
        return moves;
    }

    public int minIncrementForUnique3(int[] A) {
        if (A == null || A.length == 0 || A.length == 1)
            return 0;
        Arrays.sort(A); // 将数组排序
        int moves = 0;
        int last = A[0]; // last表示上一次放入set的最后元素值。通过维护last，每次放元素都是放在last的后面
        Set<Integer> set = new HashSet<>();
        for (int i : A) {
            if (set.contains(i)) {
                last++;
                set.add(last);
                moves += last - i; // 在计算move时，不需要一个个计算，直接计算距离就快了
            } else {
                set.add(i);
                last = i;
            }
        }
        return moves;
    }
}
