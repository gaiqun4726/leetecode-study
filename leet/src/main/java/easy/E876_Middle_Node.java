package easy;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2020/3/23
 */
public class E876_Middle_Node {

    public ListNode middleNode(ListNode head) {
        ListNode cur = head;
        int cnt = 0;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        int mid = cnt / 2 + 1;
        cur = head;
        while (mid != 1) {
            mid--;
            cur = cur.next;
        }
        return cur;
    }
}
