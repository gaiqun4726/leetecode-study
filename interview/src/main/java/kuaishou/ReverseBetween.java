package kuaishou;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2021/1/10
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        ListNode front = head;
        while (m > 1) {
            front = front.next;
            m--;
        }
        n = n - m;
        ListNode tail = front;
        while (n != 0) {
            tail = tail.next;
            n--;
        }
        ListNode newHead = front.next;
        ListNode newTail = tail.next;
        tail.next = null;
        ListNode node = reverse(newHead);
        newHead.next = node;
        while (newHead.next != null) {
            newHead = newHead.next;
        }
        newHead.next = newTail;
        return head;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
