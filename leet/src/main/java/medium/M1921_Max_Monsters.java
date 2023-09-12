package medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author gaiqun
 * @date 2023/9/3
 */
public class M1921_Max_Monsters {

    public static void main(String[] args) {
        M1921_Max_Monsters solution = new M1921_Max_Monsters();
        int[] dist = {1, 1, 2, 3};
        int[] speed = {1, 1, 1, 1};
        int res = solution.eliminateMaximum(dist, speed);
        System.out.println(res);
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] tag = new int[dist.length];
        Arrays.fill(tag, 1);
        int res = 0;
        while (isValid(dist, tag)) {
            int tmp = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < tag.length; i++) {
                if (tag[i] == 1) {
                    dist[i] -= speed[i];
                    if (dist[i] < tmp) {
                        index = i;
                        tmp = dist[i];
                    }
                }
            }
            if (index != -1) {
                tag[index] = 0;
                res++;
            }
        }
        return res;
    }

    public boolean isValid(int[] dist, int[] tag) {
        boolean hasMonster = false;
        for (int i = 0; i < tag.length; i++) {
            if (tag[i] == 1) {
                hasMonster = true;
                if (dist[i] <= 0) {
                    return false;
                }
            }
        }
        return hasMonster;
    }
}
