package medium;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2020/3/29
 *
 * 总结
 *
 * 链表的排序，时间复杂度O(nlogn)，只能用归并排序了。
 *
 * 对于两个有序链表的二路归并排序我们很熟了。
 *
 * 对于链表的排序，就是把链表拆分到每个元素一个链表，然后两两合并；重复合并直到合并到最后。
 * 有两个关键方法：1. split方法把链表分为等长的两段，返回第二段的头结点；2. merge方法把两个有序链表合并为一个有序链表。
 * 归并排序递归进行，先是把链表分成两段，然后对两段分别递归排序，最后把排序后的链表合并成一个有序链表。
 */
public class M148_Sort_list {
    public static void main(String[] args) {
        M148_Sort_list solution = new M148_Sort_list();
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(0);
        n5.next = null;
        n4.next = n5;
        n3.next = n4;
        n2.next = n3;
        n1.next = n2;
        ListNode head = solution.sortList(n1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public ListNode sortList(ListNode head) {
        // 递归函数的跳出条件，只剩一个节点则返回。
        if (head == null || head.next == null) {
            return head;
        }
        int n = 0;
        ListNode cur = head;
        // 计算链表长度
        while (cur != null) {
            cur = cur.next;
            n++;
        }
        // 链表分割为等长的两段，如果为奇数个，后半段较长
        ListNode left = head;
        ListNode right = split(head, n / 2);
        // 对分割后的分别排序
        ListNode l1 = sortList(left);
        ListNode l2 = sortList(right);
        // 排序后合并
        return merge(l1, l2);
    }

    private ListNode split(ListNode head, int n) {
        ListNode cur = head;
        while (n > 1 && cur != null) {
            n--;
            cur = cur.next;
        }
        ListNode tail = cur.next;
        cur.next = null;
        return tail;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left != null ? left : right;
        return head.next;
    }
}
