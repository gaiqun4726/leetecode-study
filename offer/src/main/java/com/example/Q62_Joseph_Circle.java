package com.example;

import common.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/30
 *
 * 总结
 *
 * 最开始的思路，使用环形链表，每次移除链表中的相应结点，注意取模避免重复循环链表。时间复杂度O(m*n)
 * 但是提交，36个测试用例，第27个TLE。
 *
 * 除了使用链表，使用list也可以，每次算出下次需要移除的索引，然后移除即可。
 *
 * 约瑟夫环问题，是一道数学递推式归纳解决的问题。
 * http://www.360doc.com/content/19/0212/18/32116899_814506447.shtml
 * 如果需要知道整个移除结点的过程，则需要模拟整个过程；
 * 如果只是需要知道最后剩余的结点是谁，则直接用地推公式求解，时间复杂度为O(n)
 *
 * 对于长度为n，删除第m个元素的约瑟夫环问题。索引编号从零开始。
 * 则第一次移除的元素索引为(m-1)%n。移除1个元素以后，剩下的元素就变成了规模为n-1，删除第m个元素的约瑟夫问题。
 * 对于规模为1，删除第m个元素的问题，解必然为0。现在就是想要把当前元素的索引0，恢复为在规模为n时所在的索引。
 * 从规模为1的问题，逐步还原到规模为n的问题。
 * 对于每一次报数来说，从索引0开始算，报数m-1的会被删除，下次索引开始0的，必然是上次报数为m的元素。
 * 规模为1的问题已知，索引为0。那么在规模为2的问题中，该元素的报数必然为m，即规模为2时索引应当为(0+m)%2。
 * 规模为3时，应当为(m+m)%3。用数学符号表示即为：
 * f(i,m)表示规模为i，删除第m个元素时，最终剩余元素的索引。
 * f(1,m)=0
 * f(2,m)=(f(1,m)+m)%2
 * f(3,m)=(f(2,m)+m)%3
 * ...
 * f(n,m)=(f(n-1,m)+m)%n
 * 基于上面的递推式，自底向上可以算出只有一个元素的解，在规模为n时所在的索引
 */
public class Q62_Joseph_Circle {

    public static void main(String[] args) {
        Q62_Joseph_Circle solution = new Q62_Joseph_Circle();
        System.out.println(solution.lastRemaining2(5, 3));
    }

    /**
     * 链表模拟约瑟夫环删除的过程，超时
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < n; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        cur.next = head;
        while (n > 1) {
            int cnt = (m - 1) % n;
            while (cnt > 0) {
                cur = cur.next;
                cnt--;
            }
            cur.next = cur.next.next;
            n--;
        }
        return cur.val;
    }

    /**
     * 使用list，直接计算出移除的位置，可以AC，时间较慢
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        if (n <= 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            index = (index + m - 1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    /**
     * 递推公式 f(m,n) = (f(m-1,n)+m)%n, f(1,n) = 0
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining3(int n, int m) {
        if (n <= 0) {
            return -1;
        }
        int index = 0;
        for (int i = 1; i < n; i++) {
            index = (index + m) % (i + 1);
        }
        return index;
    }
}
