package com.gaiqun;

/**
 * @author gaiqun
 * @date 2020/5/19
 */
public class TryCatchStudy {

    public static void main(String[] args) {
        TryCatchStudy tryCatchStudy = new TryCatchStudy();
        System.out.println(tryCatchStudy.hello());
    }

    private String hello() {
        try {
            return "yes";
        } finally {
            System.out.println("hello");
        }
    }
}
