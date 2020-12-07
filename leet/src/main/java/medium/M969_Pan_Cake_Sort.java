package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/6/7
 *
 * 烧饼排序。利用递归求解，每次先把最大的饼翻到上面，再整体翻到下面。
 * 烧饼排序解法不唯一。这个解法可以通过，但是不是最少翻转次数。
 */
public class M969_Pan_Cake_Sort {
    public static void main(String[] args) {
        int[] A = {3, 2, 4, 1};
        M969_Pan_Cake_Sort solution = new M969_Pan_Cake_Sort();
        List<Integer> result = solution.pancakeSort(A);
        for (int val : result) {
            System.out.print(val + " ");
        }
    }

    List<Integer> result = new ArrayList<>();

    public List<Integer> pancakeSort(int[] A) {
        sort(A, A.length);
        return result;
    }

    private void sort(int[] A, int n) {
        if (n == 0) {
            return;
        }
        int index = 0, value = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] > value) {
                value = A[i];
                index = i;
            }
        }
        reverse(A, index);
        result.add(index + 1);
        reverse(A, n - 1);
        result.add(n);
        sort(A, n - 1);
    }

    private void reverse(int[] A, int index) {
        int i = 0, j = index;
        while (i < j) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
            i++;
            j--;
        }
    }
}
