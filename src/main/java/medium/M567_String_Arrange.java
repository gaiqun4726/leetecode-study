package medium;

public class M567_String_Arrange {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "ba";
        System.out.println(new M567_String_Arrange().checkInclusion(s1, s2));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;
        int[] array1 = new int[26];
        int[] array2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            array1[s1.charAt(i) - 'a']++;
            array2[s2.charAt(i) - 'a']++;
        }
        if (isSame(array1, array2))
            return true;
        for (int i = s1.length(); i < s2.length(); i++) {
            array2[s2.charAt(i - s1.length()) - 'a']--;
            array2[s2.charAt(i) - 'a']++;
            if (isSame(array1, array2))
                return true;
        }
        return false;
    }

    public boolean isSame(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i])
                return false;
        }
        return true;
    }
}
