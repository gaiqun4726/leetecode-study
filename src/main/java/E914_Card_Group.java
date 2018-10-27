import java.util.HashMap;
import java.util.Map;

public class E914_Card_Group {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 0)
            return false;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < deck.length; i++) {
            int key = deck[i];
            if (map.containsKey(key)) {
                int count = map.get(key);
                map.put(key, ++count);
            } else {
                map.put(key, 1);
            }
        }
        Object[] keys = map.keySet().toArray();
        boolean res = false;
        for (int key = 2; key <= deck.length; key++) {
            int count = 0;
            for (int j = 0; j < keys.length; j++) {
                if (map.get((Integer) keys[j]) % key == 0)
                    count++;
            }
            if (count == keys.length) {
                res = true;
                break;
            }
        }
        return res;
    }
}
