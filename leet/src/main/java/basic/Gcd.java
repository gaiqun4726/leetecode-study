package basic;

/**
 * @author gaiqun
 * @date 2020/3/24
 */
public class Gcd {

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
