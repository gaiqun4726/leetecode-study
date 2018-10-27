public class E160_Intersect_linked_list {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 1;
        int lenB = 1;
        while (curA.next != null) {
            curA = curA.next;
            lenA++;
        }
        while (curB.next != null) {
            curB = curB.next;
            lenB++;
        }
        if (curA != curB)
            return null;
        ListNode first = lenA >= lenB ? headA : headB;
        ListNode second = lenA >= lenB ? headB : headA;
        int step = Math.abs(lenA - lenB);
        while (step > 0) {
            first = first.next;
            step--;
        }
        while (first != null) {
            if (first == second)
                break;
            first = first.next;
            second = second.next;
        }
        return first;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
