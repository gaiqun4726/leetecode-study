package medium;

public class M59_Spiral_Matrix_II {
    public static void main(String[] args) {
        M59_Spiral_Matrix_II solution = new M59_Spiral_Matrix_II();
        System.out.println(solution.generateMatrix2(1));
    }
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

    public int[][] generateMatrix2(int n) {
        int[][] result = new int[n][n];
        int tR = 0;
        int tC = n-1;
        int bR = n-1;
        int bC = 0;
        int cR = 0;
        int cC = 0;
        int val = 1;
        while(tR < bR && tC > bC) {
            while(cC < tC) {
                result[cR][cC++] = val++;
            }
            while(cR < bR) {
                result[cR++][cC] = val++;
            }
            while(cC > bC) {
                result[cR][cC--] = val++;
            }
            while(cR > tR) {
                result[cR--][cC] = val++;
            }
            tR++;
            tC--;
            bR--;
            bC++;
            cR = tR;
            cC = bC;
        }
        if(tR==bR && tC==bC) {
            result[tR][tC] = val;
        }
        return result;
    }
}
