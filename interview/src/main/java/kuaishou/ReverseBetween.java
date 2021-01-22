package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2021/1/10
 */
public class ReverseBetween {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = ListUtils.buildList(nums);
        ReverseBetween solution = new ReverseBetween();
        ListNode res = solution.reverseBetween(head, 2, 4);
        System.out.println(res);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        if (m == 1) {
            return reverse(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    ListNode next = null;

    private ListNode reverse(ListNode head, int n) {
        if (n == 1) {
            next = head.next;
            return head;
        }
        ListNode node = reverse(head.next, n - 1);
        head.next.next = head;
        head.next = next;
        return node;
    }
}
