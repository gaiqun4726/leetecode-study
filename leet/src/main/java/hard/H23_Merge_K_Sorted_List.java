package hard;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class H23_Merge_K_Sorted_List {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        l1.next = l11;
        ListNode l12 = new ListNode(4);
        l11.next = l12;
        l12.next = null;
        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        l2.next = l21;
        ListNode l22 = new ListNode(4);
        l21.next = l22;
        l22.next = null;
        ListNode[] lists = {l1, l2};
        System.out.println(new H23_Merge_K_Sorted_List().mergeKLists(lists));
    }

    /**
     * 从每个链表的表头挑选最小的，放入新链表，然后将被移除表头的链表向后移动。
     * 很显然想到使用小顶堆。java中PriorityQueue就是小顶堆的一种实现。
     * 不能使用TreeMap，因为没法处理相同val的情形。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            if (head != null) {
                queue.offer(head);
                lists[i] = head.next;
            }
        }
        ListNode result = new ListNode(-1);
        ListNode cur = result;
        while (!queue.isEmpty()) {
            ListNode tmp = queue.poll();
            cur.next = tmp;
            cur = tmp;
            if (tmp.next != null)
                queue.offer(tmp.next);
        }
        return result.next;
    }
}
