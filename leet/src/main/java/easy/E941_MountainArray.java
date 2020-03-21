package easy;

public class E941_MountainArray {
    public boolean validMountainArray(int[] A) {
        boolean increase = false;
        boolean decrease = false;
        if (A == null || A.length < 3)
            return false;
        int i = 0;
        while (i < A.length - 1) {
            if (A[i] < A[i + 1]) {
                increase = true;
                i++;
            } else
                break;
        }
        while (i < A.length - 1) {
            if (A[i] > A[i + 1]) {
                decrease = true;
                i++;
            } else
                break;
        }
        return increase && decrease && (i == A.length - 1);
    }
}
