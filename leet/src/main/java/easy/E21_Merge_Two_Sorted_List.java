package easy;

import common.ListNode;
import common.ListUtils;

public class E21_Merge_Two_Sorted_List {

    public static void main(String[] args) {
        int[] nums1 = {1};
        ListNode l1 = ListUtils.buildList(nums1);
        int[] nums2 = {1, 3, 4};
        ListNode l2 = ListUtils.buildList(nums2);
        System.out.println(new E21_Merge_Two_Sorted_List().mergeTwoLists2(l1, l2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 直接把剩下的链表接过来就行了，不用循环，下面的解法太蠢了。
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return head.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        while (l1 != null) {
            cur.next = l1;
            // 别忘了cur和l1都得往前走
            cur = cur.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            cur.next = l2;
            cur = cur.next;
            l2 = l2.next;
        }
        return head.next;
    }
}
