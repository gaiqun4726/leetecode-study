package com.example;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2020/4/16
 *
 * 总结
 *
 * 反转链表，需要很熟
 */
public class Q24_Reverse_LinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        Q24_Reverse_LinkedList solution = new Q24_Reverse_LinkedList();
        System.out.println(solution.reverseList(head));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 两个指针，一个prev，一个cur。初始化
        ListNode prev = head;
        ListNode cur = head.next;
        // prev的next需要置为null，否则有环
        prev.next = null;
        while (cur != null) {
            // 类似于swap。保存cur.next，然后逐个赋值
            // 再定义一个next。next，prev，cur逐个赋值
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // cur为null，最终返回prev作为新链表的head
        return prev;
    }
}
