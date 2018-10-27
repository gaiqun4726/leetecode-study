public class E142_Cycle_Linked_List_Entrance {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        int slowSteps = 0;
        int fastSteps = 0;
        while (slow != fast) {
            if (fast == null || fast.next == null)
                return null;
            slow = slow.next;
            slowSteps++;
            fast = fast.next.next;
            fastSteps += 2;
        }
        int steps = fastSteps - slowSteps;
        ListNode entrance = head;
        while (steps-- != 0) entrance = entrance.next;
        return entrance;
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
