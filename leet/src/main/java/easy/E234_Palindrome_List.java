package easy;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2020/4/16
 */
public class E234_Palindrome_List {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        //ListNode node3 = new ListNode(2);
        //ListNode node4 = new ListNode(2);
        //ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = null;
        //node3.next = node4;
        //node4.next = null;
        //node5.next = null;
        E234_Palindrome_List solution = new E234_Palindrome_List();
        System.out.println(solution.isPalindrome(node1));
    }

    /**
     * 空间复杂度要求O(1)，因此不能把链表转成数组。但是单链表没法从后往前遍历，因此把链表的后半段反转。然后前半段后半段一起遍历
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        // 找链表中间结点，使用快慢指针。
        ListNode slow = head;
        ListNode fast = head;
        // slow指针到底指向哪个结点，不好判断。可以写两个链表长度为奇偶的用例，然后模拟一下遍历过程
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 循环结束时，slow指针指向的位置，如果链表长度为奇数，指向中间结点；偶数则指向后半段的第一个结点。
        ListNode prev = slow;
        ListNode cur = prev.next;
        // 反转以后的链表尾结点需要指向null。本题改不改都不影响后面遍历
        prev.next = null;
        // 链表反转的常见套路
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // 前后半段链表一起遍历。前半段遍历到后半段的结点则终止。前半段的长度小于等于后半段长度
        while (head != slow) {
            if (head.val != prev.val) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }
        return true;
    }
}
