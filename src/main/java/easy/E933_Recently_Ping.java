package easy;

import java.util.LinkedList;
import java.util.Queue;

public class E933_Recently_Ping {
    public static void main(String[] args) {
        RecentCounter recentCounter = new E933_Recently_Ping().new RecentCounter();
        System.out.println(recentCounter.ping(39187));
        System.out.println(recentCounter.ping(45399));
        System.out.println(recentCounter.ping(50662));
        System.out.println(recentCounter.ping(70693));
        System.out.println(recentCounter.ping(72666));
        System.out.println(recentCounter.ping(84380));
        System.out.println(recentCounter.ping(105653));
        System.out.println(recentCounter.ping(129301));
        System.out.println(recentCounter.ping(156166));
        System.out.println(recentCounter.ping(156423));
        System.out.println(recentCounter.ping(158304));
        System.out.println(recentCounter.ping(211387));
    }

    /**
     * 用队列，删除队列首部不需要的元素。这样比从队尾查找节省空间和时间。
     */
    class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            this.queue = new LinkedList<Integer>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.size() > 0) {
                if (t - queue.peek() > 3000)
                    queue.poll();
                else
                    break;
            }
            return queue.size();
        }
    }
}
