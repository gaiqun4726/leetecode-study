package medium;

public class M59_Spiral_Matrix_II {
    public int[][] generateMatrix(int n) {
        if (n <= 0)
            return null;
        int[][] matrix = new int[n][n];
        int tr = 0;
        int tc = 0;
        int br = n - 1;
        int bc = n - 1;
        int cr = tr;
        int cc = tc;
        int num = 1;
        while (tr < br && tc < bc) {
            while (cc < bc) matrix[cr][cc++] = num++;
            while (cr < br) matrix[cr++][cc] = num++;
            while (cc > tc) matrix[cr][cc--] = num++;
            while (cr > tr) matrix[cr--][cc] = num++;
            tr++;
            tc++;
            br--;
            bc--;
            cr = tr;
            cc = tc;
        }
        while (tr == br && tc <= bc) matrix[tr][tc++] = num++;
        while (tr <= br && tc == bc) matrix[tr++][tc] = num++;
        return matrix;
    }
}
