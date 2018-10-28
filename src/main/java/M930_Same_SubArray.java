public class M930_Same_SubArray {
    public static void main(String[] args) {
        int[] A = {0, 0, 0, 0, 0, 0};
        System.out.println(new M930_Same_SubArray().numSubarraysWithSum(A, 2));
    }

    /**
     * 没完成，有bug
     *
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum(int[] A, int S) {
        if (A == null || A.length == 0)
            return 0;
        if (A.length == 1)
            return A[0] == S ? 1 : 0;
        int start = 0;
        int end = 1;
        int count = 0;
        int sum = A[start] + A[end];
        while (end != A.length - 1) {
            if (sum == S) {
                while (end != A.length - 1 && sum == S) {
                    count++;
                    end++;
                    sum += A[end];
                }
                sum -= A[end];
                end--;
                count--;
                while (end != start && sum == S) {
                    count++;
                    sum -= A[start];
                    start++;
                }
            } else if (sum < S) {
                end++;
                sum += A[end];
            } else {
                sum -= A[start];
                start++;
            }
        }
        while (start != end) {
            if (sum == S) {
                count++;
            }
            sum -= A[start];
            start++;
        }
        return count;
    }
}
