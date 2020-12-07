package easy;

import common.ListNode;
import common.ListUtils;

public class E206_Reverse_List {

    public static void main(String[] args) {
        ListNode head = ListUtils.buildList(new int[] {1, 2, 3, 4, 5});
        E206_Reverse_List solution = new E206_Reverse_List();
        head = solution.reverseList2(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 反转列表注意事项: 1.需要prev和cur两个指针 2.需要四步操作 3.注意最后一个节点不要断裂
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        // 防止断裂，判断条件要是cur
        while (cur != null) {
            // 从cur.next开始循环
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            // 最后回到tmp
            cur = tmp;
        }
        // 返回prev
        return prev;
    }
}