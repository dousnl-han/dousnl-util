import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.User;
import com.dousnl.domain.UserDto;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/15 16:53
 */
public class InstofTest {

    public static void main(String[] args) throws Exception {
        HashMap<String,String> areaMap=new HashMap<String,String>();
        areaMap.put("",null);
        System.out.println(JSON.toJSONString(areaMap));
    }

    private static <T> void push(T u) {
        if (u instanceof User){
            User u1 = (User) u;
            System.out.println(JSON.toJSONString(u1));
        }
        if (u instanceof UserDto){
            System.out.println(JSON.toJSONString(u));
        }
    }
}
