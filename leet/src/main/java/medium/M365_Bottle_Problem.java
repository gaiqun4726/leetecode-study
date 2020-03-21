package medium;

import java.util.HashSet;
import java.util.Set;

public class M365_Bottle_Problem {
    private static boolean canSolve = false;
    private Set<Integer> set = new HashSet<>();

    public boolean canMeasureWater(int x, int y, int z) {
        mesure(0, 0, x, y, z);
        return canSolve;
    }

    private void mesure(int m, int n, int x, int y, int z) {
        if (set.contains(z)) {
            canSolve = true;
            return;
        }
        if (m <= x && n <= y) {
            set.add(m + n);
            mesure(0, n, x, y, z);
            mesure(m, 0, x, y, z);
            mesure(x, n, x, y, z);
            mesure(m, y, x, y, z);
            mesure(0, y, x, y, z);
            mesure(0, n, x, y, z);
            mesure(0, n, x, y, z);
            mesure(0, n, x, y, z);
        }
    }
}
