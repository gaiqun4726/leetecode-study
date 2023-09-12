package easy;

/**
 * @author gaiqun
 * @date 2023/9/8
 */
public class E643_Max_Average {

    public static void main(String[] args) {
        E643_Max_Average solution = new E643_Max_Average();
        int[] nums = {1, 12, -5, -6, 50, 3};
        double res = solution.findMaxAverage(nums, 4);
        System.out.println(res);
    }

    public double findMaxAverage(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int i = 0, j = 1;
        int sum = nums[i];
        while (j - i < k) {
            sum += nums[j++];
        }
        double val = (double) sum / k;
        while (j < nums.length) {
            sum -= nums[i++];
            sum += nums[j++];
            val = Math.max(val, (double) sum / k);
        }
        return val;
    }
}
