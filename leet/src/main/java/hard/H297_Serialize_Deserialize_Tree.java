package hard;

import common.TreeNode;

import java.util.*;

public class H297_Serialize_Deserialize_Tree {
    public static void main(String[] args) {
        String testStr = "1,2,3,null,null,4,null,null,5,null,null";
        System.out.println(new H297_Serialize_Deserialize_Tree().serialize(new H297_Serialize_Deserialize_Tree().deserialize(testStr)));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        helperSerialize(root, list);
        StringBuilder sb = new StringBuilder();
        for (Integer in : list) {
            sb.append(in);
            sb.append(",");
        }
        String res = sb.toString();
        return res.substring(0, res.length() - 1);
    }

    private void helperSerialize(TreeNode root, List<Integer> list) {
        if (root == null) {
            list.add(null);
            return;
        }
        list.add(root.val);
        helperSerialize(root.left, list);
        helperSerialize(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = Arrays.asList(data.split(","));  //将数组转为list
        return helperDeserialize(list.iterator());
    }

    /**
     * 使用递归解法，序列化反序列化都需要helper。
     * 难理解的是反序列化解法。
     * 因为序列化时已经将空指针保存为null，所以在反序列化时使用递归顺序反序列化就可以。
     * 注意因为需要保存当前处理的位置，所以使用迭代器可以省去保存引用的麻烦。
     *
     * @param it
     * @return
     */
    private TreeNode helperDeserialize(Iterator<String> it) { //使用迭代器
        if (!it.hasNext())
            return null;
        String val = it.next();  //使用了next方法，迭代器就会自动向前
        if (val.equals("null"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = helperDeserialize(it);
        root.right = helperDeserialize(it);
        return root;
    }

    // Encodes a tree to a single string.
    public String serialize3(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper1(root, sb);
        return sb.toString();
    }

    void helper1(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        helper1(root.left, sb);
        helper1(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize3(String data) {
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return helper2(nodes);
    }

    TreeNode helper2(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        // 使用LinkedList
        String first = nodes.removeFirst();
        if (first.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = helper2(nodes);
        root.right = helper2(nodes);
        return root;
    }
}
