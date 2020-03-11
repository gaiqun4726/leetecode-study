package medium;

import java.util.LinkedList;

public class M71_Simplify_Path {
    public static void main(String[] args) {
        String s = "/../";
        System.out.println(new M71_Simplify_Path().simplifyPath(s));
    }

    public String simplifyPath(String path) {
        LinkedList<String> list = new LinkedList<>();
        String[] paths = path.split("/");
        for (String p : paths) {
            if (p.equals("") || p.equals("."))
                continue;
            else if (p.equals("..")) {
                if (!list.isEmpty())
                    list.removeLast();
            } else {
                list.add("/" + p);
            }
        }
        if (list.isEmpty())
            list.add("/");
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

}
