package hard;

import common.ListNode;

import common.ListUtils;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 两个解法的时间复杂度都是O(n*klogk)，但是执行时间解法2更快
 */
public class H23_Merge_K_Sorted_List {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        ListNode l1 = ListUtils.buildList(nums1);
        int[] nums2 = {4, 5, 6};
        ListNode l2 = ListUtils.buildList(nums2);
        ListNode[] lists = {l1, l2};
        System.out.println(new H23_Merge_K_Sorted_List().mergeKLists2(lists));
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
            if (tmp.next != null) {
                queue.offer(tmp.next);
            }
        }
        return result.next;
    }

    /**
     * 多路归并，迭代解法。
     * 多路归并比逐个归并要快，时间复杂度为O(n*klogk)，其中k为数组长度，n为数组中元素的平均值
     * 每次把两个链表合并，直到只剩一个链表。
     * 为了方便在迭代中计算合并的两个链表，每次合并数组中收尾两个链表，并向中间靠拢两个索引。
     * 一轮结束以后，更新数组的结尾。合并的链表放在第一个链表所在的数组位置。
     * 因为并没有使用额外的空间，空间复杂度为O(1)。
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int end = lists.length - 1;
        while (end != 0) {
            int i = 0, j = end;
            while (i < j) {
                lists[i] = merge(lists[i], lists[j]);
                i++;
                j--;
            }
            end = j;
        }
        return lists[0];
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return head.next;
    }
}
