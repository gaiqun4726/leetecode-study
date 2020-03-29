package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/19
 * <p>
 * 总结
 * <p>
 * 回溯问题。
 * <p>
 * 回溯问题的基本结构：
 * v表示部分解，i表示当前检查的元素序号，xi表示当前元素在解空间内可能的取值，Xi表示i元素的解空间
 * <p>
 * 1: v <- {}  // 部分解初始化为空
 * 2: tag <- false // tag表示是否有解
 * 3: backtrace(1) // 调用回溯方法，从第一个元素开始深度遍历
 * 4: if tag then output v // 如果tag为true，则有可行解
 * 5: else output "no solution" // 否则没有可行解
 * <p>
 * procedure backtrace(k) // 递归函数
 * 1: for xk in Xk // 对于解空间中的每一个值
 * 2：  v <- xk // 把xk加入部分解
 * 3:   if v 是可行解 then tag <- true and exit // 如果加入当前值，得到可行解，则输出结果
 * 4:   else if v 是部分解 then backtrace(k+1) // 如果加入当前值，是部分解，则递归下一层
 * 5：  v -> xk // 把xk从部分解移除
 * 6: end for
 */
public class M93_Restore_IP {

    public static void main(String[] args) {
        M93_Restore_IP solution = new M93_Restore_IP();
        System.out.println(solution.restoreIpAddresses("0000"));
    }

    private List<String> resList = new ArrayList<>();
    private LinkedList<String> indexList = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backTrace(1, 0, s);
        return resList;
    }

    private void backTrace(int count, int index, String s) {
        int len = s.length();
        // 对于解空间中的每个可能，这里是从当前index最多可以取3位数字
        for (int i = 1; i < 4 && index + i <= len; i++) {
            String segment = s.substring(index, index + i);
            // 把当前元素加入部分解
            indexList.addLast(segment);
            // 如果是可行解，则输出。这里判断条件是，已经得到四段数字，总长度没有剩余，每段数字都小于255
            // 注意，数字010或00这种也是不符合IP约束的，需要过滤掉
            boolean valid = String.valueOf(Integer.parseInt(segment)).equals(segment);
            if (valid && count == 4 && index + i == len && Integer.parseInt(segment) < 256) {
                resList.add(String.join(".", indexList));
            } else if (valid && count < 4 && index + i < len && Integer.parseInt(segment) < 256) {
                // 如果是部分解，递归判断下一层的元素能不能得到可行解
                backTrace(count + 1, index + i, s);
            }
            // 不论是可行解、部分解、不是解，最后都把加入的元素回退出来。
            indexList.removeLast();
        }
    }
}
