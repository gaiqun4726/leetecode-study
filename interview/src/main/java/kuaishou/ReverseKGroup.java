package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2021/1/13
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = ListUtils.buildList(nums);
        ReverseKGroup solution = new ReverseKGroup();
        ListNode res = solution.reverseKGroup(head, 2);
        System.out.println(res);
    }

    /**
     * k个一组反转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode a = head, b = head;
        // 如果剩余长度不足k，则不反转
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        // 反转[a,b)区间的链表
        ListNode res = reverse2(a, b);
        // 反转b开始的剩余链表
        a.next = reverseKGroup(b, k);
        return res;
    }

    /**
     * 反转[a,b)区间的链表，返回反转后的头结点，尾结点的next为null。迭代解法
     *
     * @param a
     * @param b
     * @return
     */
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode prev = null, cur = a;
        while (cur != b) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    /**
     * 反转[a,b)区间的链表，返回反转后的头结点，尾结点的next为null。递归解法
     *
     * @param a
     * @param b
     * @return
     */
    private ListNode reverse2(ListNode a, ListNode b) {
        if (a.next == b) {
            return a;
        }
        ListNode head = reverse2(a.next, b);
        a.next.next = a;
        a.next = null;
        return head;
    }
}
