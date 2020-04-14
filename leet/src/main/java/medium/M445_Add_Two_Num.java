package medium;

import common.ListNode;
import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/14
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
}
