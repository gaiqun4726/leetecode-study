package com.example;

import common.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author gaiqun
 * @date 2020/3/18
 *
 * 总结
 *
 * 树的序列化与反序列化。背下来吧，就像快排一样。
 * 序列化与反序列化都用递归来做；序列化用深度遍历，反序列化也要用深度遍历。
 * 反序列化递归函数需要传迭代器，不能传索引。如果传索引，递归回退的时候获取的下一个元素会出问题。
 * 记住用迭代器。递归函数返回树的根节点。
 */
public class Q37_Serialize_Binary_Tree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        //TreeNode right = new TreeNode(3);
        root.left = null;
        root.right = right;
        //left.left = null;
        //left.right = null;
        right.left = null;
        right.right = null;
        Q37_Serialize_Binary_Tree solution = new Q37_Serialize_Binary_Tree();
        System.out.println(solution.serialize2(solution.deserialize("[1,2,3,null,null,4,5]")));
    }

    // Encodes a tree to a single string.
    // 可以用广度遍历序列化字符串，不过反序列化也要以广度的方式解析。尽量不要用迭代来做，会晕。
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur != null) {
                res.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                res.add(null);
            }
        }
        return res.toString();
    }

    // 深度遍历序列化，使用递归。
    public String serialize2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        serializer(root, list);
        return list.toString();
    }

    void serializer(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            list.add(null);
            return;
        }
        list.add(root.val);
        serializer(root.left, list);
        serializer(root.right, list);
    }

    // Decodes your encoded data to tree.
    // 深度优先反序列化，使用递归。
    public TreeNode deserialize(String data) {
        String str = data.substring(1, data.length() - 1);
        String[] strs = str.split(",");
        List<String> list = Arrays.stream(strs).map(String::trim).collect(Collectors.toList());
        return deserializer(list.iterator());
    }

    // 必须使用迭代器传参，返回根结点。
    public TreeNode deserializer(Iterator<String> it) {
        if (!it.hasNext()) {
            return null;
        }
        // 使用it.next()要注意，用完以后下次再用就是下一个值了。
        String val = it.next();
        if ("null".equals(val)) {
            return null;
        }
        // 深度遍历
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializer(it);
        root.right = deserializer(it);
        return root;
    }
}
