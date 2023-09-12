package didi;

/**
 * @author gaiqun
 * @date 2023/9/4
 */
public class MyClass {

    public int cnt = 0;

    public static void main(String[] args) {
        String[][] input = {{"1", "1", "1", "1", "0"}, {"1", "1", "0", "1", "0"}, {"1", "1", "0", "0", "0"}, {"0", "0", "0", "0", "0"}};
        MyClass solution = new MyClass();
        int res = solution.findIsland(input);
        System.out.println(res);
    }

    public int findIsland(String[][] input) {
        int width = input.length;
        int height = input[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (input[i][j].equals("1")) {
                    cnt++;
                    trace(i, j, input);
                }
            }
        }
        return cnt;
    }

    public void trace(int i, int j, String[][] input) {
        int width = input.length;
        int height = input[0].length;
        if (i < 0 || i >= width || j < 0 || j >= height || !input[i][j].equals("1")) {
            return;
        }
        input[i][j] = "0";
        trace(i - 1, j, input);
        trace(i + 1, j, input);
        trace(i, j - 1, input);
        trace(i, j + 1, input);
    }
}
