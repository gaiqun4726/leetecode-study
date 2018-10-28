import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        Queue<String> queue = new PriorityQueue<String>();
        String a = "yes";
        String b = "y" + "es";
        System.out.println(a == b);
    }
}
