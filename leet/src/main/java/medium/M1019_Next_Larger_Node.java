package medium;

import common.ListNode;
import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/3/13
 *
 * 总结
 *
 * 链表问题，可以考虑用栈来辅助。链表问题在于，每次只能够从头结点开始遍历。需要重复遍历的问题复杂度都会很高。
 * 解决办法就是用栈来保存已经访问过的节点信息，避免重复访问。避免重复访问，可以用特征值、栈、链表等辅助数据结构。
 *
 * 遍历时，用栈把小于栈顶结点的结点入栈；大于栈顶结点时，让栈里的元素不断出栈，直到栈顶结点大于当前结点。
 * 注意栈为空时直接入栈；遍历结束后如果栈里还有元素，则剩余的元素都没有下一个更大节点。
 *
 * 时间复杂度O(n)，空间复杂度O(n)。
 */
public class M1019_Next_Larger_Node {

    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        int len = 0;
        // 获取链表长度
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        int[] res = new int[len];
        Stack<Node> stack = new Stack<>();
        int index = 0;
        temp = head;
        // 一次遍历
        while (temp != null) {
            // 把栈里小于当前结点的元素出栈
            if (!stack.isEmpty() && stack.peek().val < temp.val) {
                while (!stack.isEmpty() && stack.peek().val < temp.val) {
                    Node node = stack.pop();
                    res[node.index] = temp.val;
                }
            }
            // 把当前结点入栈
            stack.push(new Node(index, temp.val));
            index++;
            temp = temp.next;
        }
        // 如果栈不空，剩下的元素都没有下一个更大结点。
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res[node.index] = 0;
        }
        return res;
    }

    /**
     * 记录索引和结点值得数据结构
     */
    static class Node {

        private int index;
        private int val;

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}
