package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2021/1/10
 */
public class DeleteDuplicate {
    public static void main(String[] args) {
        DeleteDuplicate solution = new DeleteDuplicate();
        int[] nums = {1, 2, 2};
        ListNode head = ListUtils.buildList(nums);
        System.out.println(solution.deleteDuplicates(head));
    }

    public ListNode deleteDuplicates(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode right = head.next;
        while (right != null) {
            while (right != null && left.val != right.val) {
                left = left.next;
                right = right.next;
            }
            while (right != null && left.val == right.val) {
                left.next = right.next;
                right = right.next;
            }
            if (right == null) {
                left = null;
            } else {
                left.val = right.val;
                left.next = right.next;
            }
        }
        return head;
    }
}
