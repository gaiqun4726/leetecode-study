package medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/3/23
 *
 * 总结
 *
 * 最开始的想法，通过层数奇偶，在层次遍历的时候，决定是把左/右，还是右/左结点放入栈里。
 * 但是这样的想法是错误的，因为从第三层开始，上面的解法没办法从左到右遍历了。
 *
 * 为了能够z型遍历，还是需要保留层次遍历里结点在队列里的顺序。
 *
 * 解法1：使用两个双向链表。层次遍历的时候，第一个保存奇数层的结点，第二个保存偶数层的结点。
 * 两个队列分别遍历，并把孩子结点放入另一个队列。
 *
 * 奇数队列从左到右遍历并弹出，并把孩子结点放入偶数队列，遍历结束后打印结果；
 * 偶数队列从右到左遍历不弹出，遍历结束打印结果；
 * 再从左到右遍历偶数层并弹出，把孩子结点放入奇数队列。
 * 当两个队列都是空，则遍历结束。
 *
 * 解法2：使用一个双向链表，每一层的结点以null分隔。
 * 依然是层次遍历，但是在遍历奇数偶数层的结点时，把结果放入一个双向链表中，根据需要来头插或尾插。
 *
 * 关键点：
 * 1.还是需要层次遍历，保留结点间的顺序关系，然后根据需要调整
 * 2.需要使用双向链表，方便从右或从左遍历队列。
 * 3.结果List<List<Integer>>中的元素是List<Integer>，在插入以后，不能clear，需要新new一个List。
 * 一旦clear，结果集里的引用的对象也被clear了。
 * 4.使用null或其他字符区分层次的结束。
 * 5.LinkedList基本操作：pollFirst，addFirst，addLast...
 */
public class M103_Zigzag_Search {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        left.left = null;
        left.right = null;
        right.left = null;
        right.right = null;
        M103_Zigzag_Search solution = new M103_Zigzag_Search();
        System.out.println(solution.zigzagLevelOrder3(root));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 奇数层的队列
        Queue<TreeNode> odd = new LinkedList<>();
        // 偶数层的队列
        LinkedList<TreeNode> even = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        odd.offer(root);
        while (!odd.isEmpty()) {
            List<Integer> oddRes = new ArrayList<>();
            // 弹出每个节点，并把左右孩子放入偶数队列
            while (!odd.isEmpty()) {
                TreeNode cur = odd.poll();
                oddRes.add(cur.val);
                if (cur.left != null) {
                    even.add(cur.left);
                }
                if (cur.right != null) {
                    even.add(cur.right);
                }
            }
            // 把遍历结果放入结果集
            if (!oddRes.isEmpty()) {
                result.add(oddRes);
            }
            // 必须新new一个存放结果的list
            List<Integer> evenRes = new ArrayList<>();
            // 遍历偶数双向列表
            if (!even.isEmpty()) {
                // 从右向左遍历，得到结果
                for (int i = even.size() - 1; i >= 0; i--) {
                    evenRes.add(even.get(i).val);
                }
                result.add(evenRes);
                // 从左向右遍历，把左右孩子放入奇数队列，并弹出结点。
                while (!even.isEmpty()) {
                    TreeNode cur = even.poll();
                    if (cur.left != null) {
                        odd.add(cur.left);
                    }
                    if (cur.right != null) {
                        odd.add(cur.right);
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 双向链表保存层次遍历的结点，每一层用null分隔
        LinkedList<TreeNode> nodes = new LinkedList<>();
        // 放置第一层。
        nodes.addLast(root);
        nodes.addLast(null);
        // 表示从左到右，还是从右到左遍历
        boolean fromLeft = true;
        // 一层遍历的结果
        LinkedList<Integer> res = new LinkedList<>();
        while (!nodes.isEmpty()) {
            // 每一层都从左到右遍历。但是对于奇数层，遍历结果从左到右放置到res；偶数层遍历结果从右到左放置到res。
            TreeNode node = nodes.pollFirst();
            // 一层还没遍历完
            if (node != null) {
                if (fromLeft) {
                    // 奇数层，以尾插法放置，结果是从左到右的
                    res.addLast(node.val);
                } else {
                    // 偶数层，以头插法放置，结果是从右到左的
                    res.addFirst(node.val);
                }
                // 孩子结点还是要从左到右放置到nodes里
                if (node.left != null) {
                    nodes.addLast(node.left);
                }
                if (node.right != null) {
                    nodes.addLast(node.right);
                }
            } else {
                // 一层遍历完了，把结果放到结果集里
                result.add(res);
                // 需要new一个双向链表，不能复用之前的，或者把之前的置空。否则结果集里的结果就被改动了
                res = new LinkedList<>();
                // 更改遍历方向
                fromLeft = !fromLeft;
                // 遍历完了，孩子结点就也加完了。需要给nodes最后加个层结束标记
                if (!nodes.isEmpty()) {
                    nodes.addLast(null);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<TreeNode> list = new LinkedList<>();
            while (size != 0) {
                TreeNode node = queue.poll();
                list.addLast(node);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            List<Integer> res = new ArrayList<>();
            if (level % 2 == 1) {
                while (!list.isEmpty()) {
                    res.add(list.removeFirst().val);
                }
            } else {
                while (!list.isEmpty()) {
                    res.add(list.removeLast().val);
                }
            }
            result.add(res);
            level++;
        }
        return result;
    }
}
