package easy;

/**
 * @author gaiqun
 * @date 2020/3/26
 *
 * 总结
 * 暴力求解，注意边界条件。
 * 第一步找到Rook的坐标；第二步，从Rook向四个方向找pawn
 *
 * 通过设置每一步坐标变化的数组，可以简化代码。
 */
public class E999_Rook_Capture {

    public static void main(String[] args) {
        E999_Rook_Capture solution = new E999_Rook_Capture();
        char[][] board = {{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'}, {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}};
        System.out.println(solution.numRookCaptures2(board));
    }

    public int numRookCaptures(char[][] board) {
        int rookX = 0;
        int rookY = 0;
        for (int i = 0; i < 8; i++) {
            boolean tag = false;
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    rookX = i;
                    rookY = j;
                    tag = true;
                    break;
                }
            }
            if (tag) {
                break;
            }
        }
        int up = rookX - 1;
        int down = rookX + 1;
        int left = rookY - 1;
        int right = rookY + 1;
        int cnt = 0;
        while (up >= 0 && board[up][rookY] == '.') {
            up--;
        }
        if (up >= 0 && board[up][rookY] == 'p') {
            cnt++;
        }
        while (down < 8 && board[down][rookY] == '.') {
            down++;
        }
        if (down < 8 && board[down][rookY] == 'p') {
            cnt++;
        }
        while (left >= 0 && board[rookX][left] == '.') {
            left--;
        }
        if (left >= 0 && board[rookX][left] == 'p') {
            cnt++;
        }
        while (right < 8 && board[rookX][right] == '.') {
            right++;
        }
        if (right < 8 && board[rookX][right] == 'p') {
            cnt++;
        }
        return cnt;
    }

    public int numRookCaptures2(char[][] board) {
        int rookX = 0;
        int rookY = 0;
        for (int i = 0; i < 8; i++) {
            boolean tag = false;
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    rookX = i;
                    rookY = j;
                    tag = true;
                    break;
                }
            }
            if (tag) {
                break;
            }
        }
        // 设置四个方向变化时，x、y坐标每一步的变化
        int[][] diff = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int cnt = 0;
        // 向四个方向去找pawn
        for (int i = 0; i < 4; i++) {
            int x = rookX, y = rookY;
            int step = 1;
            do {
                // 注意别写成 x += x + ...
                x = rookX + diff[i][0] * step;
                y = rookY + diff[i][1] * step;
                step++;
            } while (x >= 0 && x < 8 && y >= 0 && y < 8 && board[x][y] == '.');
            // 如果是pawn，则计数。
            if (x >= 0 && x < 8 && y >= 0 && y < 8 && board[x][y] == 'p') {
                cnt++;
            }
        }
        return cnt;
    }
}
