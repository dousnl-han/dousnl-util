package com.dousnl.utils.math;

import com.dousnl.domain.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
 * Date       : 2021/3/29
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/3/29       hanliangliang     新增              1001
 ********************************************************************/
public class BigDecimallUtil {

    public static Integer yuanToFen(String yuan){
        return new BigDecimal(yuan).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
    }

    public static void main(String[] args) {
        System.out.println(BigDecimallUtil.yuanToFen("11.56735"));

        String getterMethodName = "get" + StringUtils.capitalize("name");
        System.out.println(getterMethodName);
        List<Integer> list = new ArrayList();
        User u = new User();
        //u.setList(list);
        list.addAll(Arrays.asList(1, 2, 3, 4));
        System.out.println(u);
        abTest(list, u);
        System.out.println(u);
        List<User> listUser = Lists.newArrayList();
        listUser.add(new User());
        Map<Integer, User> map = Maps.newHashMap();
        map.put(1, new User());
        User userOp = Optional.ofNullable(map.get(2)).orElse(new User());
        System.out.println(userOp.getAge());
        System.out.println(new User());
        u.setList(null);
        listUser = null;
        System.out.println(u);
        List list1 = Optional.ofNullable(u.getList()).orElse(Lists.newArrayList());
        list1.addAll(Arrays.asList(5,6,7));
        //u.setList(list1);
        System.out.println(u);
        User u1=null;
        saveU1(u1);
        if (u1!=null){
            System.out.println(u1.getAge());
        }
        List<User> jumpUrlList = Collections.emptyList();
        Map<String, String> jumpMap = jumpUrlList.stream().collect(Collectors.toMap(j -> j.getName().toString(), User::getAddress));
        //jumpMap=new HashMap<>();
        //if (!CollectionUtils.isEmpty(jumpMap)){
            u.setName(jumpMap.get("1"));
        //}
        System.out.println(u1);

        String format = MessageFormat.format("https://c.dushu365.com/sharePage/index_%s.html?id=%s", "111");
        System.out.println("format:"+format);
    }

    private static void savelist2(List list2) {
        list2=Arrays.asList(1);
    }

    private static void saveU1(User u1) {
        u1=new User();
    }


    private static void abTest(List<Integer> list, User u) {
        List<Integer> collect = list.stream().filter(e -> e.intValue() > 1).collect(Collectors.toList());
        list.stream().forEach(e->{
            list.stream().filter(v -> v.intValue() > 1).findFirst().orElse(null);
        });
        System.out.println(list);
        //u.setList(collect);

    }

}
