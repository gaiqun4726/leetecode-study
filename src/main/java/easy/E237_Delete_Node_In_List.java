package easy;

public class E237_Delete_Node_In_List {
    /**
     * 这道题的意思是，只给一个节点，删除这个节点。
     * 所以思路只能是，将后继节点的值付给当前节点，并将当前节点的next指向后继的next
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
