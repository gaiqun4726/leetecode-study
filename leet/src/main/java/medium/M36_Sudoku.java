package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/4/20
 *
 * 总结
 *
 * 思路1：暴力求解，检查行、列、九宫格，看是否满足数独要求。
 * 对行、列、九宫格遍历的时间复杂度都是O(n^2)。总的时间复杂度也是O(n^2)
 *
 * 思路2：对于每个位置，可以计算它所属的行、列、九宫格。每个行、列、九宫格都维护一个标识数字的map。
 * 如果map中已经有值，则返回不合法。
 * 一次遍历即可求解。时间复杂度O(n^2)。
 *
 * 两种解法，第二种稍快，因为一次遍历就行。第一种解法要遍历3次。但是时间复杂度是一样的。
 */
public class M36_Sudoku {

    public static void main(String[] args) {
        //char[][] board = {
        //        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        //        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        //        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        //        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        //        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        //        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        //        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        //        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        //        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        //};
        //char[][] board = {
        //        {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
        //        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        //        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        //        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        //        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        //        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        //        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        //        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        //        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        //};
        char[][] board = {
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        M36_Sudoku solution = new M36_Sudoku();
        System.out.println(solution.isValidSudoku2(board));
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (invalid(set, board[i][j])) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (invalid(set, board[j][i])) {
                    return false;
                }
            }
        }
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                Set<Integer> set = new HashSet<>();
                // 关键是检查九宫格时，容易出错。
                for (int i = m * 3; i < m * 3 + 3; i++) {
                    for (int j = n * 3; j < n * 3 + 3; j++) {
                        if (invalid(set, board[i][j])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean invalid(Set<Integer> set, char val) {
        if (!Character.isDigit(val)) {
            return false;
        }
        int value = val - '0';
        if (set.contains(value)) {
            return true;
        }
        set.add(value);
        return false;
    }

    public boolean isValidSudoku2(char[][] board) {
        Map<Integer, Integer>[] rowMap = new HashMap[9];
        Map<Integer, Integer>[] colMap = new HashMap[9];
        Map<Integer, Integer>[] subMap = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rowMap[i] = new HashMap<>();
            colMap[i] = new HashMap<>();
            subMap[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (!Character.isDigit(c)) {
                    continue;
                }
                int val = c - '0';
                if (rowMap[i].containsKey(val)) {
                    return false;
                } else {
                    rowMap[i].put(val, j);
                }
                if (colMap[j].containsKey(val)) {
                    return false;
                } else {
                    colMap[j].put(val, i);
                }
                int pos = (i / 3) * 3 + (j / 3);
                if (subMap[pos].containsKey(val)) {
                    return false;
                } else {
                    subMap[pos].put(val, pos);
                }
            }
        }
        return true;
    }
}
