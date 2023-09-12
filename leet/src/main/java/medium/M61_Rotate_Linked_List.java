package medium;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2023/8/12
 */
public class M61_Rotate_Linked_List {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tmp = head;
        int cnt = 0;
        //计算链表长度
        while (tmp != null) {
            tmp = tmp.next;
            cnt++;
        }
        int newK = k % cnt;
        ListNode slow = head;
        ListNode fast = head;
        int step = 0;
        //快慢指针
        while (step < newK) {
            fast = fast.next;
            step++;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //完成链表旋转
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
}
