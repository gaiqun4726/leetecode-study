package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author gaiqun
 * @date 2020/4/7
 */
public class HeapSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while(scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        System.out.println(list);
    }
}
