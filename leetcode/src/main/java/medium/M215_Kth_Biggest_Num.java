package medium;

import java.util.PriorityQueue;

public class M215_Kth_Biggest_Num {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        for (int i = 0; i < k - 1; i++)
            queue.poll();
        return queue.peek();
    }
}
