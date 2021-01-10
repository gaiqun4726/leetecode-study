package medium;

import java.util.PriorityQueue;

public class M215_Kth_Biggest_Num {

    public static void main(String[] args) {
        //int[] nums = {3, 2, 1, 5, 6, 4};
//        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] nums = {1, 3, 5, 2, 2};
        M215_Kth_Biggest_Num solution = new M215_Kth_Biggest_Num();
//        solution.sort(nums);
//        System.out.println(nums[nums.length - 4]);

        System.out.println(solution.findKth(nums, 5, 3));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        for (int i = 0; i < k - 1; i++)
            queue.poll();
        return queue.peek();
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right) {
        // 递归要注意终止条件
        if (left >= right) {
            return;
        }
        int pos = partition(nums, left, right);
        // 对左右部分递归调quickSort
        quickSort(nums, left, pos - 1);
        quickSort(nums, pos + 1, right);
    }

    public int partition(int[] nums, int left, int right) {
        int val = nums[left];
        // 注意这种快排得到的是递增序列，按题意需要从后往前取值第k个值。
        while (left < right) {
            // 一轮partition，把pivot放在最终位置，因此每个值和pivot比较。
            // 防止越界
            while (left < right && nums[right] > val) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= val) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = val;
        return left;
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        int p = -1;
        int left = 0;
        int right = n - 1;
        do {
            p = partition2(a, left, right);
            if (p < K - 1) {
                left = p + 1;
            } else if (p > K - 1) {
                right = p - 1;
            }
        } while (p != K - 1);
        return a[p];
    }

    private int partition2(int[] a, int left, int right) {
        int pivot = a[left];
        while (left < right) {
            while (left < right && a[right] > pivot) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] <= pivot) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = pivot;
        return left;
    }
}
