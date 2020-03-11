package com.example;

/**
 * @author gaiqun
 * @date 2020/2/14
 */
public class P0806_Hanoi_tower {

    public static void main(String[] args) {
        P0806_Hanoi_tower solution = new P0806_Hanoi_tower();
        int[] A = {0};
        int[] B = {};
        int[] C = {};
        solution.hanota(A, B, C);
        System.out.println(A.length);
        System.out.println(C.length);
    }

    public void hanota(int[] A, int[] B, int[] C) {
        C = A;
        A = B;
    }
}
