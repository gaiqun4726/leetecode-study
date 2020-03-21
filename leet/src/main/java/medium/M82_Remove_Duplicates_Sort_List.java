package medium;

import common.ListNode;

public class M82_Remove_Duplicates_Sort_List {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(1);
        ListNode head3 = new ListNode(2);
        ListNode head4 = new ListNode(2);
        ListNode head5 = new ListNode(3);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        ListNode res = new M82_Remove_Duplicates_Sort_List().removeDuplicate(head1);
        while(res!=null) {
            System.out.println(res.val);
            res=res.next;
        }
    }

    ListNode removeDuplicate(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode temp = new ListNode(-1);
        temp.next = head;
        boolean first = false;
        if(head.val==head.next.val) {
            ListNode temp2 = new ListNode(-2);
            temp.next = temp2;
            temp2.next = head;
            first = true;
        }
        ListNode start = head;
        ListNode end = head;

        while(end!=null) {
            end = end.next;
            if(start.val==end.val) {
                while(end!=null&&start.val==end.val) {
                    start.next = end.next;
                    end = end.next;
                }
                start.val = end.val;
                start.next = end.next;
            }
            if(end==null) {
                start = null;
            } else {
                start = start.next;
                end = start;
            }
        }
        return first?temp.next.next:temp.next;
    }
}
