package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2021/1/10
 */
public class DetectCycle {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ListNode head = ListUtils.buildList(nums);
        ListNode slow = head;
        ListNode fast = head;
        int n = 3;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = slow;
        DetectCycle solution = new DetectCycle();
        System.out.println(solution.detectCycle(head).val);
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 找到入口点，必须保证fast指针走过距离是slow的二倍。因此从一开始就按照二倍的步数来走。
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast && fast != null && fast.next != null);
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
