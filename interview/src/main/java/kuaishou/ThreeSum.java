package kuaishou;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2021/1/16
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] num = {-2, 0, 1, 1, 2};
        ArrayList<ArrayList<Integer>> result = solution.threeSum(num);
        System.out.println(result);
    }

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (num == null || num.length < 3) {
            return result;
        }
        int len = num.length;
        Arrays.sort(num);
        for (int i = 0; i < len - 2; i++) {
            int val = num[i];
            if (val > 0) {
                break;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (num[left] + num[right] + val == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(val);
                    list.add(num[left]);
                    list.add(num[right]);
                    result.add(list);
                    left++;
                } else if (num[left] + num[right] + val > 0) {
                    right--;
                } else if (num[left] + num[right] + val < 0) {
                    left++;
                }
            }
        }
        return result;
    }
}
