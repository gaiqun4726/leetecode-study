package hard;

/**
 * @author gaiqun
 * @date 2020/6/2
 */
public class H37_Solve_Sudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        H37_Solve_Sudoku solution = new H37_Solve_Sudoku();
        solution.solveSudoku(board);
        System.out.println(board);
    }

    private final static int N = 9;
    private final static int M = 3;

    public void solveSudoku(char[][] board) {
        backtrace(board, 0, 0);
    }

    private void backtrace(char[][] board, int row, int col) {
        if (col == N) {
            row++;
            col = 0;
        }
        if (row == N) {
            return;
        }
        if (Character.isDigit(board[row][col])) {
            backtrace(board, row, col + 1);
        }
        for (int i = 1; i <= N; i++) {
            if (isValid(board, row, col, i)) {
                board[row][col] = (char) (i + '0');
                backtrace(board, row, col + 1);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int val) {
        for (int i = 0; i < N; i++) {
            char c = board[i][col];
            if (Character.isDigit(c) && val == c - '0') {
                return false;
            }
        }
        for (int i = 0; i < N; i++) {
            char c = board[row][i];
            if (Character.isDigit(c) && val == c - '0') {
                return false;
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                char c = board[(row / 3) * 3 + i][(col / 3) * 3 + j];
                if (Character.isDigit(c) && val == c - '0') {
                    return false;
                }
            }
        }
        return true;
    }
}
