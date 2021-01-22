import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/9/8
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/9/8       hanliangliang     新增              1001
 ********************************************************************/
public class CollectorToMap {

    public static void main(String[] args) {
        User user = new User("zhang", 11, 11, false);
        User user2 = new User("zhang1", 10, 11, false);
        List<User> list= Lists.newArrayList();
        list.add(user);list.add(user2);
        Map<String, User> collect = list.stream().collect(Collectors.toMap(k -> k.getName(), t -> t));
        System.out.println(JSON.toJSONString(collect));
        doOverStackTest(1);

    }

    private static void doOverStackTest(int i) {
        overStackErrorTest(i);
    }

    private static void overStackErrorTest(int i) {
        System.out.println("第" + i + "次调用");
        doOverStackTest(++i);
    }
}
