import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
@Slf4j
public class CollectorToMap {
    private static Short type=1;

    public static void main(String[] args) {
        User user = new User("zhang", 11, 11, false,null);
        User user2 = new User("zhang1", 10, 11, false,1);
        List<User> list= Lists.newArrayList();
        list.add(user);list.add(user2);
        Map<String, User> collect = list.stream().collect(Collectors.toMap(k -> k.getName(), t -> t));
        System.out.println(JSON.toJSONString(collect));
        doOverStackTest(1);
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put(1,null);
        map.put(2,null);
        //map.merge(1,null,(k1,k2)->k1);
        //Map<String, Integer> integerMap = list.stream().collect(Collectors.toMap(User::getName, User::getNum));
        //System.out.println(integerMap);
        Map map1= Optional.ofNullable(getMap()).orElse(Maps.newHashMap());
        System.out.println(map1.get(1));
        long l=5*60;
        long g=5*60L;
        System.out.println("l:"+l);
        System.out.println("g:"+g);
        System.out.println(type.equals(1));
        log.debug("test:{}",true);
        System.out.println((int)g*2);
        save((int)g*2);
        int i=568921352;
        user=null;
        //System.out.println(i.equals("1"));
        System.out.println(i);
        double v1 = (new Date().getTime() - 1000) / (24 * 60 * 60 * 1000.0);
        System.out.println("v1:"+v1);
        double v = Math.ceil(new Date().getTime() - 1000) / (24 * 60 * 60 * 1000.0);
        System.out.println("v:"+v);
        System.out.println((int)v);
        System.out.println(Math.ceil(113 * 1.0 / 2));
        System.out.println((long) Math.ceil(111 * 1.0 / 2));
        Date a = getDate();
        a = a != null ? a : null;
        System.out.println(a);
        Integer pageSize=15;
        double size = Math.ceil((double) pageSize / 2);
        Integer key = null;
        System.out.println(size);
        long ll=pageSize;
        int pa= (int) ll;
        System.out.println(ll);
        System.out.println(pa);

        final ArrayList<String> list1 = Lists.newArrayList("speaker_1", "speaker_2");
        List<Integer> speakerIds = list1.stream().map(s -> Integer.valueOf(s.replaceAll("speaker_", "")))
                .collect(Collectors.toList());
        System.out.println(speakerIds);


    }

    public static Integer processList(List<Object> data) {
        int listSize = data.size();
        int threadCount;

        // 动态设置线程数
        if (listSize < 100) {
            threadCount = 0;
        } else if (listSize < 1000) {
            threadCount = listSize / 10;
        } else {
            threadCount = listSize / 100;
        }
        return threadCount;
    }

    private static Date getDate() {
        return new Date();
    }

    private static void save(int i) {
        System.out.println(i);
    }

    private static Map getMap() {
        Map map=new HashMap();
        map.put(1,1);
        map=null;
        return map;
    }

    private static void doOverStackTest(int i) {
        overStackErrorTest(i);
    }

    private static void overStackErrorTest(int i) {
        System.out.println("第" + i + "次调用");
        //doOverStackTest(++i);
    }
}
