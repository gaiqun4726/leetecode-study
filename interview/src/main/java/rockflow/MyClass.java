package rockflow;

/**
 * @author gaiqun
 * @date 2023/9/6
 */
public class MyClass {

    public static void main(String[] args) {
        MyClass solution = new MyClass();
        solution.backtrace(2);
        System.out.println(cnt);
    }

    public static int cnt = 0;
    private int[] choices = {1, 2};

    public void backtrace(int n) {
        if(n==0) {
            cnt++;
            return;
        }
        if(n<0) {
            return;
        }
        for(int choice: choices) {
            backtrace(n-choice);
        }
    }
}
