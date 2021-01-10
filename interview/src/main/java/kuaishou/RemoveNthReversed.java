package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2021/1/10
 */
public class RemoveNthReversed {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ListNode head = ListUtils.buildList(nums);
        RemoveNthReversed solution = new RemoveNthReversed();
        System.out.println(solution.removeNthFromEnd(head, 2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write code here
        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(-1);
        // 为了解决n个节点，删除倒数第n个的情形，即删除头结点的情形，使用了头结点。
        node.next = head;
        ListNode slow = node;
        ListNode fast = node;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return node.next;
    }
}
