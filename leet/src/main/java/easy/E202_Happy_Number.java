package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/4/30
 */
public class E202_Happy_Number {

    public static void main(String[] args) {
        E202_Happy_Number solution = new E202_Happy_Number();
        System.out.println(solution.isHappy(2142));
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int N = n;
        while (N != 1) {
            set.add(N);
            int cur = 0;
            while (N != 0) {
                cur += Math.pow(N % 10, 2);
                N /= 10;
            }
            N = cur;
            if (set.contains(N)) {
                return false;
            } else {
                set.add(N);
            }
        }
        return true;
    }
}
