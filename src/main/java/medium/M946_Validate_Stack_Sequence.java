package medium;

import java.util.Stack;

public class M946_Validate_Stack_Sequence {
    public static void main(String[] args) {
        int[] pushed = {};
        int[] poped = {};
        System.out.println(new M946_Validate_Stack_Sequence().validateStackSequences(pushed, poped));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length)
            return false;
        if (pushed.length == 0 && popped.length == 0)
            return true;
        Stack<Integer> in = new Stack<>();
        int index1 = 0;
        int index2 = 0;
        in.push(pushed[index1++]);
        do {
            while (in.peek() != popped[index2] && index1 < pushed.length)
                in.push(pushed[index1++]);
            if (in.peek() != popped[index2] && index1 == pushed.length)
                return false;
            in.pop();
            index2++;
            if (in.isEmpty() && index1 != pushed.length)
                in.push(pushed[index1++]);
        } while (!in.isEmpty());
        return true;
    }
}
