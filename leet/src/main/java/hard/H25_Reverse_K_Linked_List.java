package hard;

import common.ListNode;
import common.ListUtils;

/**
 * @author gaiqun
 * @date 2020/5/16
 *
 * K个一组翻转链表。这题就是翻转链表的基础上来写，没有复杂算法，但要注意很多细节。
 */
public class H25_Reverse_K_Linked_List {

    public static void main(String[] args) {
        H25_Reverse_K_Linked_List solution = new H25_Reverse_K_Linked_List();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = ListUtils.buildList(nums);
        System.out.println(solution.reverseKGroup(head, 3));
    }

    private int K;

    public ListNode reverseKGroup(ListNode head, int k) {
        // k=1，不需翻转
        if (k <= 1) {
            return head;
        }
        K = k;
        // 结果中，第一个元素表示翻转后的头，第二个表示翻转后的尾。
        // 因为要在翻转后，把之前的尾接到新的头上，所有需要返回头尾。
        // 先执行一次，获取整个翻转链表的头。然后剩下的部分循环执行翻转
        ListNode[] result = reverse(head);
        head = result[0];
        ListNode tail = result[1];
        // 用尾为null表示剩余部分不需翻转
        while (tail != null) {
            result = reverse(tail.next);
            // 翻转后，当前的尾需要接到新的头上
            tail.next = result[0];
            tail = result[1];
        }
        return head;
    }

    private ListNode[] reverse(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[] {head, null};
        }
        ListNode temp = head;
        int n = K;
        // 判断剩余部分长度是不是大于k
        while (n > 1) {
            if (temp.next == null) {
                return new ListNode[] {head, null};
            }
            temp = temp.next;
            n--;
        }
        n = K;
        ListNode prev = head, cur = head.next, tail = prev;
        // 翻转链表
        while (n > 1) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
            n--;
        }
        // 注意原来的头需要接到尾部的下一个结点
        tail.next = cur;
        ListNode[] result = new ListNode[2];
        result[0] = prev;
        result[1] = tail;
        return result;
    }
}
