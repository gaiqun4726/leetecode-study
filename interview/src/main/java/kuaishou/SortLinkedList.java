package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2021/1/12
 */
public class SortLinkedList {
    public static void main(String[] args) {
        SortLinkedList solution = new SortLinkedList();
        int[] nums = {1, 3, 2, 4, 5};
        ListNode head = ListUtils.buildList(nums);
        ListNode res = solution.sortInList(head);
        System.out.println(res);
    }

    public ListNode sortInList(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        int cnt = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            cnt++;
        }
        cnt = cnt / 2 - 1;
        cur = head;
        while (cnt != 0) {
            cur = cur.next;
            cnt--;
        }
        ListNode head2 = cur.next;
        cur.next = null;
        return merge(sortInList(head), sortInList(head2));
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode tmp = new ListNode(-1);
        ListNode cur = tmp;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }
        return tmp.next;
    }
}
