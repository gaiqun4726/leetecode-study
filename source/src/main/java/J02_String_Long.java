import java.util.Arrays;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author gaiqun
 * @date 2020/11/28
 *
 * 1. String内部由数组实现。数组由final修饰，因此String是不可变的。
 * 2. String如果用不同的字符集序列化反序列化，可能出现乱码。
 * 3. split方法可接受limit参数，设置查分的字符串段个数。
 * 4. Long的缓存机制缓存了-128~127的数值。使用Long.valueOf方法可以使用缓存，使用parseLong就不会使用缓存机制。
 */
public class J02_String_Long {
    public static void main(String[] args) {
        // String是不可变对象
        String s = "I am gai.";
        String s1 = s.replace("am", "'m");
        System.out.println(s);
        // replace可以作为删除来用
        s1 = s.replace("a", "");
        System.out.println(s1);

        // 字符乱码
        String p = "你好";
//        byte[] bytes = p.getBytes(ISO_8859_1);
//        String p1 = new String(bytes, ISO_8859_1);
        byte[] bytes = p.getBytes(UTF_8);
        String p1 = new String(bytes, UTF_8);
        System.out.println(p1);
        String className = p1.getClass().getSimpleName();
        System.out.println(className);
        System.out.println(className.substring(0,1).toLowerCase()+className.substring(1));

        // 拆分与合并
        String m ="boo:and:foo";
        System.out.println(Arrays.toString(m.split(":")));
        System.out.println(Arrays.toString(m.split(":",1)));
        System.out.println(Arrays.toString(m.split(":",2)));
        System.out.println(Arrays.toString(m.split(":",10)));

        System.out.println(String.join(",", Arrays.asList("hello", "world")));

        // Long的缓存机制
        System.out.println(Long.valueOf("123"));
        System.out.println(Long.parseLong("123"));
    }
}
