package toutiao;

/**
 *
 * 堆排序
 * @author gaiqun
 * @date 2021/1/7
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {10, 3, 1, 2, 7};
        HeapSort solution = new HeapSort();
        solution.sort(nums);
        for (int i : nums) {
            System.out.print(i + ",");
        }
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        // 建堆
        buildHeap(nums);
        // 每次把大顶堆堆顶节点和尾结点交换，然后向下调整大顶堆。
        for (int i = len - 1; i >= 1; i--) {
            swap(nums, 0, i);
            heapDown(nums, i, 0);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 递增排序，构建大顶堆。
     * 使用线性建堆法
     * @param nums
     */
    private void buildHeap(int[] nums) {
        int len = nums.length;
        int half = (len - 1) / 2;
        // 从半数结点往前，每个节点向下调整
        for (int i = half; i >= 0; i--) {
            heapDown(nums, len, i);
        }
    }

    /**
     * 自顶向下调整大顶堆。建堆法和堆排序都会用到此方法
     * @param nums
     * @param size
     * @param index
     */
    private void heapDown(int[] nums, int size, int index) {
        // 获得左右孩子索引
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int maxIndex = index;
        // 找到最大结点索引
        if (left < size && nums[left] > nums[index]) {
            maxIndex = left;
        }
        if (right < size && nums[right] > nums[index]) {
            maxIndex = right;
        }
        // 如果子节点较大，则交换结点。并递归向下调整
        if (maxIndex != index) {
            swap(nums, index, maxIndex);
            heapDown(nums, size, maxIndex);
        }
    }
}
