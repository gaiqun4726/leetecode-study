package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/5/17
 *
 * 总结
 *
 * 拓扑排序问题。使用BFS解决。
 *
 * 拓扑排序，是指有向无环图，转换为线性排序的一种排序。如果有环，无法得到拓扑排序。
 *
 * 排序的当前节点，应当是入度为零的节点。把节点选中后，依赖该节点的节点的入度会减一。
 * 使用队列保存当前入度为零的节点，每次从队列中选一个打印，并更新其余节点的入度，把入度为零的节点再加入队列。
 * 当队列为空时，如果所有结点都已打印，则得到一个拓扑排序；否则证明有环。
 */
public class M210_Schedule {

    public static void main(String[] args) {
        M210_Schedule solution = new M210_Schedule();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        System.out.println(Arrays.toString(solution.findOrder(numCourses, prerequisites)));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 入度为零的队列
        Queue<Integer> queue = new LinkedList<>();
        // 保存有向图的相邻衔接表
        Map<Integer, Set<Integer>> map = new HashMap<>();
        // 保存每个节点的入度
        int[] degree = new int[numCourses];
        if (numCourses <= 0) {
            return new int[0];
        }
        // 初始化相邻衔接表和入度表
        for (int[] pre : prerequisites) {
            // getOrDefault的妙用
            Set<Integer> set = map.getOrDefault(pre[1], new HashSet<>());
            set.add(pre[0]);
            degree[pre[0]]++;
            map.put(pre[1], set);
        }
        int[] result = new int[numCourses];
        int index = 0;
        // 把第一层节点入队列
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        // 打印入度为0的节点，并更新剩余节点的入度。有新的入度为零的节点，则加入队列
        while (!queue.isEmpty()) {
            int val = queue.poll();
            result[index++] = val;
            // 有可能set没初始化
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            for (int i : set) {
                degree[i]--;
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        if (index == numCourses) {
            return result;
        }
        // 还有未打印的节点，则有环
        return new int[0];
    }
}
