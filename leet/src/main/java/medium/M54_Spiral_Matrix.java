package medium;

import java.util.ArrayList;
import java.util.List;

public class M54_Spiral_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(new M54_Spiral_Matrix().spiralOrder2(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return list;
        int row = matrix.length;
        int col = matrix[0].length;
        int tr = 0;
        int tc = 0;
        int br = row - 1;
        int bc = col - 1;
        int cr = 0;
        int cc = 0;
        while (tr < br && tc < bc) { // 打印外圈。最后一行或一列单独处理
            while (cc < bc) list.add(matrix[cr][cc++]);
            while (cr < br) list.add(matrix[cr++][cc]);
            while (cc > tc) list.add(matrix[cr][cc--]);
            while (cr > tr) list.add(matrix[cr--][cc]);
            tr++;
            tc++;
            br--;
            bc--;
            cr = tr;
            cc = tc;
        }
        while (tr == br && tc <= bc) list.add(matrix[tr][tc++]); // 剩中间一行
        while (tr <= br && tc == bc) list.add(matrix[tr++][tc]); // 剩中间一列
        return list;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int tRow = 0;
        int tCol = col -1;
        int bRow = row -1;
        int bCol = 0;
        List<Integer> result = new ArrayList<>();
        while(tRow<bRow && bCol<tCol) {
            int i=bCol;
            while(i<tCol) {
                result.add(matrix[tRow][i++]);
            }
            i=tRow;
            while(i<bRow) {
                result.add(matrix[i++][tCol]);
            }
            i=tCol;
            while(i>bCol) {
                result.add(matrix[bRow][i--]);
            }
            i=bRow;
            while(i>tRow) {
                result.add(matrix[i--][bCol]);
            }
            tRow++;
            tCol--;
            bRow--;
            bCol++;
        }
        if(tRow==bRow) {
            int i= bCol;
            while(i<=tCol) {
                result.add(matrix[tRow][i++]);
            }
            tCol--;
            bCol++;
        }
        if(tCol==bCol) {
            int i= tRow;
            while(i<=bRow) {
                result.add(matrix[i++][tCol]);
            }
            tRow--;
            bRow++;
        }
        return result;
    }
}
