package easy;

/**
 * @author gaiqun
 * @date 2023/9/3
 */
public class E374_Guess {

    public static void main(String[] args) {
        E374_Guess solution = new E374_Guess();
        int res = solution.guessNumber(2126753390);
        System.out.println(res);
    }

    public int guessNumber(int n) {
        return helper(1, n);
    }

    public int helper(int begin, int end) {
        int mid = begin + (end - begin) / 2; //虽然这里从公式上等于(begin+end)/2，但是不会出现begin+end越界的问题。因此二分法推荐用这种方式算mid
        if (guess(mid) == 0) {
            return mid;
        } else if (guess(mid) == -1) {
            return helper(begin, mid - 1);
        } else {
            return helper(mid + 1, end);
        }
    }

    private int guess(int num) {
        int pick = 1702766719;
        return Integer.compare(pick, num);
    }
}
