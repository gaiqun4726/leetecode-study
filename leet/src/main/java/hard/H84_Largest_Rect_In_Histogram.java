package hard;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/3/28
 *
 * 总结
 *
 * 解法有：暴力法，分治法，单调栈法
 */
public class H84_Largest_Rect_In_Histogram {

    public static void main(String[] args) {
        H84_Largest_Rect_In_Histogram solution = new H84_Largest_Rect_In_Histogram();
        int[] array = {2, 1, 2};
        System.out.println(solution.largestRectangleArea3(array));
    }

    /**
     * 暴力法，从左到右，对于每一个柱子，计算向右扩展时可以形成最大的矩形面积。
     * 共两层循环，时间复杂度O(n^2)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int levels = heights.length;
        for (int i = 0; i < levels; i++) {
            int minHeight = heights[i];
            int count = 1;
            for (int j = i; j < levels; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * count++);
            }
        }
        return maxArea;
    }

    /**
     * 分治法
     * 对于柱子里最低的柱子，其可形成的最大矩形是最低柱子的高度乘以所有柱子的宽度。
     * 对于全局计算这个矩形，然后对这个柱子左右的柱子区间，递归的计算最大矩形。
     * 分治法的时间复杂度为O(nlogn)
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        return helper(heights, 0, heights.length - 1);
    }

    private int helper(int[] height, int left, int right) {
        if (left > right) {
            return 0;
        }
        // 这里只需要存最小的索引就可以了。整形最大值比较的时候会报StackOverFlow错误
        int minIndex = left;
        for (int i = left; i <= right; i++) {
            if (height[i] < height[minIndex]) {
                minIndex = i;
            }
        }
        int area = height[minIndex] * (right - left + 1);
        int leftArea = helper(height, left, minIndex - 1);
        int rightArea = helper(height, minIndex + 1, right);
        return Math.max(Math.max(area, leftArea), rightArea);
    }

    /**
     * 单调栈
     * 鉴于分治的思路，以每个柱子为最小高度的矩形里，面积最大的就是我们要找的解。
     * 利用单调递增栈，一旦新元素比栈顶小，就不断弹出栈元素并计算矩形面积，直到栈顶元素比新元素小，然后把新元素压栈。
     * 由于栈是单调递增的，所以栈里的元素肯定比栈顶的元素小。
     * 不断的弹出元素，就相当于求得每个柱子左右比它小的边界，即可以求出以当前柱子为最小高度的最大矩形面积。
     *
     * 对于单调递增栈遍历的框架代码为
     * Stack<Integer> stack;
     * int[] array;
     * for(int a: array) {
     *     while(!stack.isEmpty()&&stack.peek()>=a) {
     *         Integer top = stack.pop();
     *         // 一些操作
     *     }
     *     stack.push(a);
     * }
     *
     * 这种解法最快，时间复杂度为O(n)，空间复杂度为O(n)。
     * 但是，这个问题思考清除，边界的处理都很容易出错。在实际面试中，不推荐使用
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // 为避免处理边界的复杂问题，使用双哨兵
        int[] newHeight = new int[heights.length + 2];
        // 数组的复制函数System.arraycopy()
        System.arraycopy(heights, 0, newHeight, 1, heights.length);
        newHeight[0] = 0;
        newHeight[heights.length + 1] = 0;
        // 单调栈
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < newHeight.length; i++) {
            // 栈顶小于等于新元素的时候，新元素都入栈
            while (!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]) {
                int index = stack.pop();
                // 这里要注意，计算宽度时，必须是使用新元素的索引和栈顶元素索引的差值
                maxArea = Math.max(maxArea, (i - stack.peek() - 1) * newHeight[index]);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
