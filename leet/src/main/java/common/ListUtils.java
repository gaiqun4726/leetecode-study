package common;

/**
 * @author gaiqun
 * @date 2020/4/26
 */
public class ListUtils {

    public static ListNode buildList(int[] nums) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        cur.next = null;
        return head.next;
    }
}
