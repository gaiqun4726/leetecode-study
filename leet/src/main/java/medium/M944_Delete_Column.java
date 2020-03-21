package medium;

public class M944_Delete_Column {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length == 1)
            return 0;
        int N = A.length;
        int len = A[0].length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = 0; j < N - 1; j++) {
                if (A[j].charAt(i) > A[j + 1].charAt(i))
                    break;
                count++;
            }
            if (count != N - 1)
                res++;
        }
        return res;
    }
}
