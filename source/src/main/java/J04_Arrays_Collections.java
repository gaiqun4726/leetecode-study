import com.google.common.collect.ImmutableList;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/11/29
 */
public class J04_Arrays_Collections {
    public static void main(String[] args) {

    }

    @Data
// 自定义类
    class SortDTO {
        private String sortTarget;

        public SortDTO(String sortTarget) {
            this.sortTarget = sortTarget;
        }
    }

    @Test
    public void testSort(){
        List<SortDTO> list = ImmutableList.of(
                new SortDTO("300"),
                new SortDTO("50"),
                new SortDTO("200"),
                new SortDTO("220")
        );
        // 我们先把数组的大小初始化成 list 的大小，保证能够正确执行 toArray
        SortDTO[] array = new SortDTO[list.size()];
        list.toArray(array);

        Arrays.sort(array, Comparator.comparing(SortDTO::getSortTarget));
    }
}
