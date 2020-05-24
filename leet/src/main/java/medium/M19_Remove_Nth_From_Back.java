package medium;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2020/4/26
 *
 * 总结
 * 双指针法，找到倒数第N个结点的前一个节点。
 * 处理总共N个元素，删除倒数第N个元素的情形比较麻烦，因此引入头结点。
 */
public class M19_Remove_Nth_From_Back {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return head;
        }
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        ListNode cur = tmp, end = tmp;
        while (n != 0) {
            end = end.next;
            n--;
        }
        while (end.next != null) {
            cur = cur.next;
            end = end.next;
        }
        cur.next = cur.next.next;
        return tmp.next;
    }
}
