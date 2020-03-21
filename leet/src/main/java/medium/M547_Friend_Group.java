package medium;

import java.util.HashSet;
import java.util.Set;

public class M547_Friend_Group {
    public static void main(String[] args) {
        int[][] M = {{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}};
        System.out.println(new M547_Friend_Group().findCircleNum(M));
    }

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0)
            return 0;
        int len = M.length;
        int[] par = new int[len];
        int[] rank = new int[len];
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (M[i][j] == 1) {
                    union(par, rank, i, j);
                }
            }
        }
        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i < len; i++) {
            groups.add(par[i]);
        }
        return groups.size();
    }

    int find(int[] par, int x) {
        if (par[x] == x)
            return x;
        return par[x] = find(par, par[x]);
    }

    void union(int[] par, int[] rank, int x, int y) {
        x = find(par, x);
        y = find(par, y);
        if (x == y)
            return;
        if (rank[x] < rank[y])
            par[x] = y;
        else {
            par[y] = x;
            if (rank[x] == rank[y])
                rank[x]++;
        }
    }
}
