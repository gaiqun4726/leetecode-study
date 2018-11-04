package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M935_Knight_Dialer {
    public static void main(String[] args) {
        System.out.println(new M935_Knight_Dialer().knightDialer(0));
    }

    /**
     * 未完成
     *
     * @param N
     * @return
     */
    public int knightDialer(int N) {
        int mod = new Double(Math.pow(10, 9)).intValue() + 7;
        return steps(N, N - 1) % mod;
    }

    public int steps(int N, int steps) {
        List<Integer> list = posiblePlace(N);
        if (list.isEmpty())
            return 0;
        if (steps == 0)
            return 1;
        if (steps == 1)
            return list.size();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            count += steps(list.get(i), steps - 1);
        }
        return count;
    }

    public List<Integer> posiblePlace(int n) {
        List<Integer> list = new ArrayList<Integer>();
        switch (n) {
            case 0:
                list = Arrays.asList(4, 6);
                break;
            case 1:
                list = Arrays.asList(6, 8);
                break;
            case 2:
                list = Arrays.asList(7, 9);
                break;
            case 3:
                list = Arrays.asList(4, 8);
                break;
            case 4:
                list = Arrays.asList(0, 3, 9);
                break;
            case 5:
                break;
            case 6:
                list = Arrays.asList(0, 1, 7);
                break;
            case 7:
                list = Arrays.asList(2, 6);
                break;
            case 8:
                list = Arrays.asList(1, 3);
                break;
            case 9:
                list = Arrays.asList(2, 4);
                break;
            default:
                break;
        }
        return list;
    }
}
