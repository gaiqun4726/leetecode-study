package medium;

import common.ListNode;
import common.ListUtils;

/**
 * 总结
 *
 * 本题要找链表循环的入口点。我们知道快慢指针一定会重叠，那么怎么求入口点？
 *
 * 设快指针走的步数为f，慢指针走的步数为s。
 * 链表由两部分组成，未进入环的部分和环内部分。未进入环的链表节点数设为a，环内链表个数设为b。
 *
 * 快指针一次走两步，快慢指针重叠时，快指针走的步数是满指针的两倍，有f=2s
 * 快慢指针重叠时，慢指针一定走过环形部分的整数倍，有f=s+nb，n是整数
 * 因此可得f=2nb，s=nb。即快慢指针重叠时，快慢指针都走过环长度的整数倍。
 *
 * 已知未入环的长度为a，则a+nb意味着慢指针从重合点再走a步，会回到环的入口点。
 * 让快指针从链表头结点，慢指针从二者汇合点，二者一起向前走，每次走一步。
 * 快指针走a步到达入口点，满指针走a步到达入口点，此时二者重合。
 * 因此当快慢指针再次重合时，即使环的入口点。
 */
public class M142_Cycle_Linked_List_Entrance {

    public static void main(String[] args) {
        M142_Cycle_Linked_List_Entrance solution = new M142_Cycle_Linked_List_Entrance();
        int[] nums = {3, 2, 0, -4};
        ListNode head = ListUtils.buildList(nums);
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        System.out.println(solution.detectCycle2(head));
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
