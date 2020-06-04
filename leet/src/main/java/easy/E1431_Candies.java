package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/6/1
 */
public class E1431_Candies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxValue = 0;
        for(int candy: candies) {
            maxValue = Math.max(maxValue, candy);
        }
        List<Boolean> result = new ArrayList<>(candies.length);
        for(int candy: candies) {
            if(candy+extraCandies>=maxValue) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;

    }
}
