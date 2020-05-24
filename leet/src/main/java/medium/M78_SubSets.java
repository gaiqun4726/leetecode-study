package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/4/25
 *
 * 总结
 *
 * 思路1：先把包含所有元素的子集加入结果集。然后每次循环从结果集中拿一个list，从中尝试移除一个元素，并把结果加入结果集。
 * 能得到结果，但是包含重复结果。比如结果集中有两个list：{1,3},{2,3}，分别移除1，2后都得到{3}。
 * 这种集合的去重比较麻烦。因此思路1不可行
 *
 * 思路2：参考leet官方解答。先把空集加入结果集。循环遍历每个数字，每次把数字加入到当前已有的结果中，并插入结果集。
 * 第一次结果集有{}。把1添加到结果集中的每个结果，得到{1}。结果集中共有{},{1}
 * 把2添加，得到{2},{1,2}，结果集得到{},{1},{2},{1,2}
 * 这种方法就不会得到重复结果。
 *
 * 其实从全集中减和空集中加都能得到结果。第一种解法没解出来，是因为每次尝试移除每个元素。
 * 其实如果只尝试移除数组中的下一个元素，就变成和思路2一样的反向操作，就可以解出来了。
 * 见解法3。
 */
public class M78_SubSets {

    public static void main(String[] args) {
        M78_SubSets solution = new M78_SubSets();
        int[] nums = {1, 2, 3};
        System.out.println(solution.subsets3(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        result.add(list);
        int index = 0;
        while (index < result.size()) {
            list = result.get(index);
            for (int i = 0; i < list.size(); i++) {
                List<Integer> copy = new ArrayList<>(list);
                copy.remove(i);
                result.add(copy);
            }
            index++;
        }
        return result;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(list);
        int index = 0;
        for (int num : nums) {
            int count = 0;
            for (int i = 0; i <= index; i++) {
                list = new ArrayList<>(result.get(i));
                list.add(num);
                result.add(list);
                count++;
            }
            index += count;
        }
        return result;
    }

    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        result.add(list);
        int index = 0;
        for (Integer num : nums) {
            int count = 0;
            for (int i = 0; i <= index; i++) {
                list = new ArrayList<>(result.get(i));
                list.remove(num);
                result.add(list);
                count++;
            }
            index += count;
        }
        return result;
    }
}
