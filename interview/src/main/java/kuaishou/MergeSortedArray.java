package kuaishou;

/**
 * @author gaiqun
 * @date 2021/1/25
 */
public class MergeSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        int left = m - 1;
        int right = n - 1;
        int cur = m + n - 1;
        while (left != -1 && right != -1) {
            if (A[left] > B[right]) {
                A[cur] = A[left--];
            } else {
                A[cur] = B[right--];
            }
            cur--;
        }
        while (left != -1) {
            A[cur--] = A[left--];
        }
        while (right != -1) {
            A[cur--] = B[right--];
        }
    }
}
