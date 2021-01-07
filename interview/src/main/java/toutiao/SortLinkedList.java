package toutiao;

import common.ListNode;
import common.ListUtils;

/**
 * 单链表，奇数节点升序，偶数节点降序。重排链表使之有序
 * @author gaiqun
 * @date 2021/1/7
 */
public class SortLinkedList {

    public static void main(String[] args) {
        int[] nums = {1, 8, 2, 7, 3, 6};
        ListNode head = ListUtils.buildList(nums);
        SortLinkedList solution = new SortLinkedList();
        System.out.println(solution.sort(head));
    }

    ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode oddCur = odd;
        ListNode evenCur = even;
        int count = 1;
        while (head != null) {
            if (count % 2 == 1) {
                oddCur.next = head;
                oddCur = oddCur.next;
            } else {
                evenCur.next = head;
                evenCur = evenCur.next;
            }
            head = head.next;
            count++;
        }
        oddCur.next = null;
        evenCur.next = null;
        even.next = revert(even.next);
        return merge(odd.next, even.next);
    }

    /**
     * 利用递归反转链表
     * @param head
     * @return
     */
    ListNode revert(ListNode head) {
        // base case注意head.next为null的情况
        if (head == null || head.next == null) {
            return head;
        }
        // 递归
        ListNode res = revert(head.next);
        // 把反转后的尾结点指向head，head作为新的尾结点
        head.next.next = head;
        head.next = null;
        return res;
    }

    ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
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
        return head.next;
    }
}
