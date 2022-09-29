import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.AdviceCanel;
import com.dousnl.domain.User;
import com.dousnl.domain.UserDto;
import com.dousnl.domain.VaildDomain;
import com.dousnl.domain.fdds.VipLogSearchBO;
import com.dousnl.utils.StringUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.assertj.core.util.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/15 16:53
 */
public class InstofTest {

    private static Logger logger = LoggerFactory.getLogger(InstofTest.class);



    public static void main(String[] args) throws Exception {

        Long dae=null;
        new Date(dae);
        System.out.println(new Date(dae));
        System.out.println(spiltFeiFanBook("feiFanBook"));

        User u1=new User(1,"1");
        u1.setFragmentType(11);
        u1.setAge(null);
        Integer bookId = u1.getAge() == null ? u1.getFragmentType() : u1.getAge();
        System.out.println(bookId);
        accecp(Lists.newArrayList(new User(1,"1"),new User(2,"2")), new Consumer<User>() {
            @Override
            public void accept(User u) {
                System.out.println(JSON.toJSONString(u));
            }
        });
        System.out.println(1);

        VipLogSearchBO q = new VipLogSearchBO();
        q.setOffset(0);
        q.setPageSize(2);
        q.setPaid(true);
        q.setUserId(String.valueOf(1));
        q.setTimeUnit(0);
        Integer payCount = 0;
        if (q != null) {
            payCount = 1;
        }
        if (payCount > 1) {
            return;
        }
        q = new VipLogSearchBO();
        q.setUserId(String.valueOf(22));
        q.setOffset(0);
        q.setPageSize(23);
        q.setTimeUnit(2);
        q.setTimeValue(Arrays.asList(365));

    }

    private static String spiltFeiFanBook(String field) {
        if (StringUtil.contains(field,"feiFanBook")) {
            String[] split = field.split("_");
            if (split.length > 1) {
                return split[1];
            }
        }
        return field;
    }

    private static <User> void accecp(ArrayList<User> es, Consumer<User> consumer) {
        for (User u:es){
            consumer.accept(u);
        }
    }

    private static void accecp() {


    }

    private static <T> void push(T u) {
        if (u instanceof User) {
            User u1 = (User) u;
            System.out.println(JSON.toJSONString(u1));
        }
        if (u instanceof UserDto) {
            System.out.println(JSON.toJSONString(u));
        }
    }
}
