package easy;

public class E922_Sort_As_Even_Odd_II {
    public int[] sortArrayByParityII(int[] A) {
        int i = 0;
        int j = 1;
        int len = A.length;
        while (i < len && j < len) {
            if (A[i] % 2 == 1 && A[j] % 2 == 0) { // 如果奇数偶数位都错了，则互换
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            } else if (A[i] % 2 == 0 && A[j] % 2 == 1) { // 如果奇数偶数位都对了，则向前
                i += 2;
                j += 2;
            } else if (A[i] % 2 == 1) { // 如果偶数位错了，找下一个奇数位判断
                j += 2;
            } else { // 如果奇数位错了，找下一个偶数位判断
                i += 2;
            }
        }
        return A;
    }
}
