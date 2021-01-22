package kuaishou;

import common.ListNode;
import common.ListUtils;

/**
 * 删除链表中的重复元素。有两种题目，一种是重复元素去重，保留一个；一种是重复元素都删除，只保留没有重复元素的节点。
 * 两道题都用递归来解决。
 * @author gaiqun
 * @date 2021/1/10
 */
public class DeleteDuplicate {

    public static void main(String[] args) {
        DeleteDuplicate solution = new DeleteDuplicate();
        int[] nums = {1, 1, 2, 3, 3, 3, 4};
        ListNode head = ListUtils.buildList(nums);
        //head = solution.deleteDuplicates(head);
        head = solution.deleteDuplicates2(head);
        System.out.println(head);
    }

    /**
     * 只保留没有重复元素的节点
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        // 从头结点就开始重复，则找到不重复的节点作为头结点递归删除
        if (head.val == next.val) {
            while (next != null && head.val == next.val) {
                head = head.next;
                next = next.next;
            }
            return deleteDuplicates(next);
        } else {
            // 头结点不重复，则从头结点递归删除
            head.next = deleteDuplicates(next);
            return head;
        }
    }

    /**
     * 重复节点去重
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        while (next != null && head.val == next.val) {
            head = head.next;
            next = next.next;
        }
        // 找到不重复的节点，从下一个结点开始递归
        head.next = deleteDuplicates2(next);
        return head;
    }
}
