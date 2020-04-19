package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/4/19
 */
public class E412_Fizz_Buzz {

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
}
