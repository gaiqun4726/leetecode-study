package medium;

public class M11_Max_Container {

    /**
     * 容器是以最小的高度作为高度。所以板子越高，距离越远，容器越大。
     * 从数组首尾开始，矮的那个如果作为容器的边的化，最大面积就是当前，不可能有更大的。
     * 所以短的边继续向前。不论是首尾，只要较短，指针就向前。
     * 这样，一次遍历就可以找到最大面积。时间复杂度O(n)
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int maxArea = 0;
        while (start < end) {
            if (height[start] < height[end]) {
                int curArea = (end - start) * height[start];
                maxArea = maxArea > curArea ? maxArea : curArea;
                start++;
            } else {
                int curArea = (end - start) * height[end];
                maxArea = maxArea > curArea ? maxArea : curArea;
                end--;
            }
        }
        return maxArea;
    }

    /**
     * 暴力法，时间复杂度O(n^2)
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int maxWater = 0;
        if (height == null || height.length == 0 || height.length == 1) {
            return maxWater;
        }
        int len = height.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                maxWater = Math.max(maxWater, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return maxWater;
    }

    public int maxArea3(int[] height) {
        int maxWater = 0;
        if (height == null || height.length == 0 || height.length == 1) {
            return maxWater;
        }
        int len = height.length;
        int i = 0, j = len - 1;
        while (i != j) {
            maxWater = Math.max(maxWater, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxWater;
    }
}
