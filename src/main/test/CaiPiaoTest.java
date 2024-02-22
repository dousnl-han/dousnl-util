import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2023/12/13
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2023/12/13       hll    新增              1001
 ********************************************************************/
public class CaiPiaoTest {

    public static void main(String[] args) {
        Set<String> set = Sets.newHashSet();
        for (int i = 1; i <= 5; i++) {
            String test = new CaiPiaoTest().test();
            System.out.println(test);
        }
        List<String> redList = Lists.newArrayList("梁军", "段龙山", "高圣民", "连九生", "顾峰峰", "雷羽", "魏颖妮", "洪吉波", "汤鹏");
        System.out.println(redList.get(new Random().nextInt(redList.size())));

    }

    public String test() {
        List<Integer> redList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33);
        List<Integer> blueList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        int redSize = 6;
        int blueSize = 1;
        ArrayList<Integer> objects = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < redSize; i++) {
            int index = random.nextInt(redList.size());
            objects.add(redList.get(index));
            redList.remove(index);
        }
        objects.sort(Integer::compareTo);
        StringBuilder sbudiler = new StringBuilder(objects.toString().replace("[", "").replace("]", "") + " ");
        for (int i = 0; i < blueSize; i++) {
            int index = random.nextInt(blueList.size());
            sbudiler.append(blueList.get(index)).append(" ");
            blueList.remove(index);
        }
        return sbudiler.toString();
    }

}
