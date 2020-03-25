package basic;

/**
 * @author gaiqun
 * @date 2020/3/24
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort solution = new QuickSort();
        int[] array = {5, 2, 2, 3, 1, 7};
        solution.sort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    public void sort(int[] array, int low, int high) {
        // 递归继续的条件是low<high
        if (low < high) {
            int pos = partition(array, low, high);
            // 左右两个部分递归的排序
            sort(array, low, pos - 1);
            sort(array, pos + 1, high);
        }
    }

    /**
     * 最关键的是partition函数。方法执行后，pivot被放在最终位置，左边的都比pivot小，右边的都比pivot大
     * 返回pivot所在索引。为了方便，pivot最开始选第一个元素
     * @param array
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] array, int low, int high) {
        int pivot = array[low];
        while (low < high) {
            while (low < high && pivot <= array[high]) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= pivot) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = pivot;
        return low;
    }
}
