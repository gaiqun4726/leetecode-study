package medium;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2020/5/3
 *
 * 总结
 *
 * 创建两个头结点，分别保存较小和较大的链表。一次遍历把链表拆分为两段，最后把较大链表接到较小链表结尾。
 * 注意不要只用一个头结点保存较大的，较小的还用原来的链表。这样在处理的时候交易出错。
 */
public class M86_Split_LinkedList {

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2, 5, 2};
        ListNode head = ListUtils.buildList(nums);
        M86_Split_LinkedList solution = new M86_Split_LinkedList();
        System.out.println(solution.partition(head, 3));
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(-1);
        ListNode big = new ListNode(-1);
        ListNode sCur = small;
        ListNode bCur = big;
        while (head != null) {
            if (head.val < x) {
                sCur.next = head;
                sCur = sCur.next;
            } else {
                bCur.next = head;
                bCur = bCur.next;
            }
            head = head.next;
        }
        // 较大链表循环结束时还未断开与原链表的连接。需要把尾指针置为null。
        bCur.next = null;
        sCur.next = big.next;
        return small.next;
    }
}
