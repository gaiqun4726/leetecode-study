package easy;

public class E942_Inc_Dec_Str {
    public int[] diStringMatch(String S) {
        if (S == null || S.length() == 0)
            return new int[0];
        int len = S.length();
        int start = 0;
        int end = len;
        int[] res = new int[len + 1];
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == 'I') {
                res[i] = start;
                start++;
            } else {
                res[i] = end;
                end--;
            }
        }
        res[len] = start;
        return res;
    }
}
