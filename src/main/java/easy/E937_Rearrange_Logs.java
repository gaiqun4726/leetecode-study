package easy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class E937_Rearrange_Logs {
    public static void main(String[] args) {
        String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        System.out.println(new E937_Rearrange_Logs().reorderLogFiles(logs));
    }

    public String[] reorderLogFiles(String[] logs) {
        List<String> logs1 = new LinkedList<>();
        List<String> logs2 = new LinkedList<>();
        for (String log : logs) {
            String[] chars = log.split(" ");
            if (chars[1].charAt(0) <= '9' && chars[1].charAt(0) >= '0')
                logs2.add(log);
            else
                logs1.add(log);
        }
        Collections.sort(logs1, (o1, o2) -> {
            int index1 = o1.indexOf(" ");
            int index2 = o2.indexOf(" ");
            return o1.substring(index1 + 1).compareTo(o2.substring(index2 + 1));
        });
        int i = 0;
        for (String log : logs1) {
            logs[i] = log;
            i++;
        }
        for (String log : logs2) {
            logs[i] = log;
            i++;
        }
        return logs;
    }
}
