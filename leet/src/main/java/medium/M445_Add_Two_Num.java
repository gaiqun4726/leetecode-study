package medium;

import common.ListNode;
import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/14
 *
 * 总结
 * 两数相加，链表从高位到低位保存，不能改变原链表，因此想到用栈。
 *
 * 我的解法用了三个栈，分别保存两个数及计算结果。其实用两个栈就可以了，计算结果直接输出到新的节点，并连接旧的节点即可。
 */
public class M445_Add_Two_Num {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int val = stack1.pop() + stack2.pop() + carry;
            stack3.push(val % 10);
            carry = val / 10;
        }
        while (!stack1.isEmpty()) {
            int val = stack1.pop() + carry;
            stack3.push(val % 10);
            carry = val / 10;
        }
        while (!stack2.isEmpty()) {
            int val = stack2.pop() + carry;
            stack3.push(val % 10);
            carry = val / 10;
        }
        if (carry != 0) {
            stack3.push(carry);
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (!stack3.isEmpty()) {
            ListNode node = new ListNode(stack3.pop());
            cur.next = node;
            cur = cur.next;
        }
        cur.next = null;
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode cur = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = !stack1.isEmpty() ? stack1.pop() : 0;
            int b = !stack2.isEmpty() ? stack2.pop() : 0;
            int val = a + b + carry;
            carry = val / 10;
            ListNode node = new ListNode(val % 10);
            node.next = cur;
            cur = node;
        }
        return cur;
    }
}
