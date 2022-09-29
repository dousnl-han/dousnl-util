import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.enums.NoteSourceEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/12/16
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/12/16       hanliangliang     新增              1001
 ********************************************************************/
public class BitSetTest {

    private static final Integer RADNOM_SIZE= 20;
    private static final Integer BITSET_SIZE= 100;

    /**
     * 月VIP产品
     */
    private static Map<String, Integer> appStoreProductIdPackageMonth = null;

    public static void toMap(){
        if (appStoreProductIdPackageMonth == null){
            //appStoreProductIdPackageMonth = JSON.parseObject( "", Map.class);
        }
        System.out.println(appStoreProductIdPackageMonth);
    }

    public static void main(String[] args) {

        List<Object> list=new ArrayList<>(BITSET_SIZE);
        Random random=new Random();
        BitSet bitSet=new BitSet(BITSET_SIZE);
        BitSet bitSetHash=new BitSet(BITSET_SIZE);
        bitSet.set(1);
        bitSetHash.set(2);
        bitSet.and(bitSetHash);

        //1.初始化一千万随机数，范围1亿内
        for (int i=0;i<RADNOM_SIZE;i++){
            int nextInt = random.nextInt(BITSET_SIZE);
            //2.放入list中
            list.add(nextInt);
            //3.放入bitset中
            bitSet.set(nextInt);
        }
        System.out.println(list);
        System.out.println("=======================");
        System.out.println("list不存在的数据");
        System.out.println("=======================");
        for (int i =0;i<BITSET_SIZE;i++){
            //4.从bitset中获取不存在的list中的数字
            if (!bitSet.get(i)){
                System.out.println(i);
            }
        }
        Date maxOfWeek = DateTime.now().dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate();
        Date date = DateTime.now().dayOfWeek().withMaximumValue().toDate();
        Date date1 = DateTime.now().dayOfWeek().getDateTime().toDate();
        Date dateTime = DateTime.now().plusWeeks(1).dayOfWeek().withMinimumValue().withTimeAtStartOfDay().toDate();
        System.out.println(DateUtil.formatDateTime(maxOfWeek));
        System.out.println(DateUtil.formatDateTime(date));
        System.out.println(DateUtil.formatDateTime(date1));
        System.out.println(DateUtil.formatDateTime(dateTime));
        String str=null;
        List<Integer> list1 = Arrays.asList(1, null, 2);
        list1.stream().filter(a->a!=null && a>1).forEach(a->list.add(a.toString()));
        System.out.println(list);

        List<User> list2=new ArrayList<>(BITSET_SIZE);
        list2.sort(Comparator.comparing(User::getAge).reversed());
        System.out.println(list2);
        LocalDateTime localDateTime = LocalDate.now().atTime(LocalTime.MAX);
        System.out.println(localDateTime);
        Date date2 = DateTime.now().centuryOfEra().withMaximumValue().toDate();
        System.out.println(DateUtil.formatDateTime(date2));

        Date date3 = DateTime.now().millisOfDay().withMaximumValue().toDate();
        System.out.println(DateUtil.formatDateTime(date3));

        String format = "#1年#2月打卡返活动，第%d周打卡成功，%s：%s".replaceFirst("#1","22").replaceFirst("#2","1");
        System.out.println(format);

        Date newStartDate = new DateTime(new Date()).plusWeeks(1).dayOfWeek().withMinimumValue().withTimeAtStartOfDay().toDate();
        Date newEndDate = new DateTime(new Date()).plusWeeks(1).dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate();
        System.out.println(DateUtil.formatDateTime(newStartDate));
        System.out.println(DateUtil.formatDateTime(newEndDate));
        System.out.println(DateUtil.formatDateTime(newEndDate));
        int step = 1;
        toMap();
        System.out.println(11111);
        java.sql.Date date4=new java.sql.Date(1673798399000l);
        Date date5 = date4;
        System.out.println(date5);
        Date date6=new Date(1673798399000l);
        User user = new User();
        //user.setDate(date6);
        System.out.println(user);
        System.out.println(DateUtil.formatDateTime(date5));
        ArrayList<Integer> objects = Lists.newArrayList(1,2,3,1);
        Map<Integer, Integer> collect = objects.stream().collect(Collectors.toMap(Integer::valueOf, e -> e, (v1, v2) -> v1));
        System.out.println(collect);

        System.out.println("1111"+ NoteSourceEnum.BOOK.name());
        System.out.println(NoteSourceEnum.BOOK);
        List<Integer> groupOrderScene = JSON.parseArray("[]", Integer.class);
        User user1=new User();
        user1.setAge(18);
        Map hashMap = JSON.parseObject(JSON.toJSONString(user1),Map.class);
        System.out.println(JSON.toJSON(hashMap));
        User t = JSON.toJavaObject((JSON) JSON.toJSON(hashMap), User.class);
        System.out.println(t);
    }


}
