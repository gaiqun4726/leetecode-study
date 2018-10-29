package easy;

import java.util.HashSet;
import java.util.Set;

public class E141_Cycle_Linked_List {
    /**
     * 使用HashSet判断是否重复出现过，空间复杂度高
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        boolean res = false;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                res = true;
                break;
            }
            set.add(cur);
        }
        return res;
    }

    /**
     * 使用快慢双指针，当快指针追上慢指针时证明链表有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
