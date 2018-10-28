import java.util.HashSet;
import java.util.Set;

public class E929_Email_Address {
    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        System.out.println(new E929_Email_Address().numUniqueEmails(emails));
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<String>();
        if (emails == null || emails.length == 0)
            return 0;
        for (String str : emails) {
            String[] strs = str.split("@");
            strs[0] = strs[0].replaceAll("\\.", "");
            int index = strs[0].indexOf('+');
            if (index > -1) {
                strs[0] = strs[0].substring(0, index);
            }
            String newStr = strs[0] + "@" + strs[1];
            set.add(newStr);
        }
        return set.size();
    }
}
