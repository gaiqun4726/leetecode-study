package com.example;

import common.ListNode;

/**
 * @author gaiqun
 * @date 2020/3/16
 *
 * 总结
 *
 * 使用两个链表，一个存小于目标元素的链表，一个存大于等于的。一次遍历以后，将两个链表拼接即可。
 * 时间复杂度O(n)，空间复杂度O(n)。
 *
 * 最开始的想法是原地变换链表，但是发现，找到可以放小于目标元素的位置，再找到可以放大于目标元素的位置，太麻烦了。
 * 而且逻辑会非常复杂。最终还是使用这种空间换时间的方法，事半功倍。
 */
public class P0204_Split_List {

    public ListNode partition(ListNode head, int x) {
        ListNode cur = head;
        // 定义头结点用于返回链表引用
        ListNode smallHead = new ListNode(-1);
        ListNode largeHead = new ListNode(-1);
        // 定义迭代结点用于更新新插入结点的位置
        ListNode smallCur = smallHead;
        ListNode largeCur = largeHead;
        // 判断条件是结点非空。如果判断结点的next非空，则会丢掉最后一个结点
        while (cur != null) {
            if (cur.val < x) {
                smallCur.next = cur;
                smallCur = smallCur.next;
            } else {
                largeCur.next = cur;
                largeCur = largeCur.next;
            }
            cur = cur.next;
        }
        // 两个链表拼接，注意较大元素的链表最后一个结点的next要置空
        smallCur.next = largeHead.next;
        largeCur.next = null;
        // 返回头结点的下一个结点
        return smallHead.next;
    }
}
