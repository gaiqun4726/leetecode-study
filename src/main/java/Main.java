import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        Queue<String> queue = new PriorityQueue<String>();
        String a = "yes";
        String b = "y" + "es";
        System.out.println(a == b);
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(1);
        list.add(2);
        Collections.sort(list);
        Arrays.sort(args);
    }
}
