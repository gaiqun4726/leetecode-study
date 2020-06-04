package com.example.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/6/2
 */
public class HeapOOM {
    static class OOM {

    }

    public static void main(String[] args) {
        List<OOM> list = new ArrayList<>();
        while(true) {
            list.add(new OOM());
        }
    }
}
