package com.example;

/**
 * @author gaiqun
 * @date 2020/2/14
 */
public class P0805_Recursive_Multiple {

    public static void main(String[] args) {
        P0805_Recursive_Multiple solution = new P0805_Recursive_Multiple();
        System.out.println(solution.multiply(1, 10));
        System.out.println(solution.multiply(3, 4));
    }

    public int multiply(int A, int B) {
        if (B > A) {
            int tmp = A;
            A = B;
            B = tmp;
        }
        if (B == 1) {
            return A;
        }
        return multiply(A, B - 1) + A;
    }
}
