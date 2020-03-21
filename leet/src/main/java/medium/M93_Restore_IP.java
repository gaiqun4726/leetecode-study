package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/19
 */
public class M93_Restore_IP {

    public static void main(String[] args) {
        M93_Restore_IP solution = new M93_Restore_IP();
        System.out.println(solution.restoreIpAddresses("25525511135"));
    }

    private static List<String> resList = new ArrayList<>();
    private static LinkedList<Integer> indexList = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backTrace(s, 0, 3);
        return resList;
    }

    private void backTrace(String s, int pos, int count) {
        int len = s.length();
        for (int i = 0; i < 4; i++) {
            indexList.addLast(pos + i);
            count--;
            if (count == 0) {
                int index = indexList.getLast();
                if (index <= len - 2 && index >= len - 4) {
                    resList.add(buildStr(s));
                }
                return;
            }
            backTrace(s, pos + i, count);
            indexList.removeLast();
            count++;
        }
    }

    private String buildStr(String s) {
        StringBuilder builder = new StringBuilder();
        builder.append(s, 0, indexList.get(0));
        for (int i = 1; i < 3; i++) {
            builder.append(".").append(s, indexList.get(i - 1), indexList.get(i));
        }
        return builder.toString();
    }
}
