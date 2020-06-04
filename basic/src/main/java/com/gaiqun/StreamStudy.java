package com.gaiqun;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author gaiqun
 * @date 2020/6/2
 */
public class StreamStudy {

    /**
     * stream确实是返回一个新的流，但是流里的引用是没变的。因此流操作会有副作用。
     * 如果不想有副作用，需要用不可变对象。只是生命集合为final是不能保证不可变的。可以使用guava的不可变集合
     * @param args
     */
    public static void main(String[] args) {
        List<Trade> trades = Arrays.asList(new Trade(1), new Trade(2));
        Map<Integer, Trade> map = trades.stream().collect(Collectors.toMap(Trade::get, Function.identity()));
        // 由于副作用，修改Map中引用对应的对象的值，List中的值也被改变了。
        map.get(1).set(3);
        for(Trade trade: trades) {
            System.out.print(trade.get() + " ");
        }
    }

    static class Trade {
        Trade(int a) {
            this.a = a;
        }
        public int a;

        public int get() {
            return a;
        }

        public void set(int a) {
            this.a = a;
        }
    }
}
