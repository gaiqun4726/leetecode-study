package medium;

/**
 * @author gaiqun
 * @date 2020/4/2
 *
 * 总结
 *
 * 这道题需要注意的是，在遍历的时候不能根据规则直接改变数组的元素为0或1，否则下一个元素没法根据上个状态判断自己现在的状态。
 *
 * 一种思路是拷贝一个数组，即保留一份快照，根据快照更新数组。空间复杂度O(m*n)
 *
 * 题目要求空间复杂度为O(1)，就地修改。原来的思路就是在数组里保存上次和这次的状态，更新完后在删除上次的状态。
 * 但是数组元素是整型，没想出来怎么同时保留上次和这次的状态。
 *
 * 看题解知道，整型的取值范围还是很大的，不要被0、1限制住。可以用更大的数字表示更多的状态。
 * -1表示原来是1现在是0，2表示原来是0现在是1。遍历的时候可以知道原来的状态，遍历过后可更新为现在的状态。
 *
 * 类似求元素上下左右坐标的问题，都是定义一个x、y变化的数组，然后生成上下左右四个坐标。不要傻傻的去做i+1,i-1。
 *
 * 对于本地修改要求的题目，都可以参考这道题的思路。用更多的状态表示更多的语义。
 */
public class M289_Life_Game {

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int liveCount = 0;
                if (i - 1 >= 0) {
                    if (board[i - 1][j] == 1 || board[i - 1][j] == -1) {
                        liveCount++;
                    }
                    if (j - 1 >= 0) {
                        if (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == -1) {
                            liveCount++;
                        }
                    }
                    if (j + 1 < col) {
                        if (board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == -1) {
                            liveCount++;
                        }
                    }
                }
                if (i + 1 < row) {
                    if (board[i + 1][j] == 1 || board[i + 1][j] == -1) {
                        liveCount++;
                    }
                    if (j - 1 >= 0) {
                        if (board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == -1) {
                            liveCount++;
                        }
                    }
                    if (j + 1 < col) {
                        if (board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == -1) {
                            liveCount++;
                        }
                    }
                }
                if (j - 1 >= 0) {
                    if (board[i][j - 1] == 1 || board[i][j - 1] == -1) {
                        liveCount++;
                    }
                }
                if (j + 1 < col) {
                    if (board[i][j + 1] == 1 || board[i][j + 1] == -1) {
                        liveCount++;
                    }
                }
                if (liveCount < 2 && board[i][j] == 1) {
                    board[i][j] = -1;
                }
                if (liveCount > 3 && board[i][j] == 1) {
                    board[i][j] = -1;
                }
                if (liveCount == 3 && board[i][j] == 0) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
