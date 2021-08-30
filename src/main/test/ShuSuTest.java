import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.enums.Suggestion;
import com.dousnl.utils.date.DateUtil;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/25 20:44
 */
public class ShuSuTest {
    public static void main(String[] args) throws ParseException {

        System.out.println(Suggestion.PASS.name());
        System.out.println(Suggestion.PASS.toString());

        JSONObject jsonObject = JSONObject.parseObject("{\"code\":0,\"message\":\"ok\"}");

        System.out.println(jsonObject.get("code"));
        List list=null;
        if (true){
            list=Arrays.asList(1,2);
        }
        System.out.println(list);
        Map<String, Integer> context = new ConcurrentHashMap<>();
        context.put("13",3);
        if (context.containsKey("11")){
            context.remove("11");
        }
        System.out.println(context);
        Integer integer = context.get("11");
        System.out.println(integer);
        Date a=DateUtil.parse("2020-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date b=DateUtil.parse("2020-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        System.out.println(a.getTime()==b.getTime());

        String str="{\"ok\":true,\"errorCode\":0,\"text\":null,\"moreInfo\":null,\"now\":\"2020-11-16T10:22:27.292Z\",\"version\":\"1.0.0\",\"data\":{\"userId\":\"5c84c5697511ce17b2bba91d\",\"phone\":\"15755582909\",\"transactionId\":\"1762582980232304015\",\"orderNo\":\"1605522147099852585\"}}";
        JSONObject object = JSON.parseObject(str);
        if (Objects.nonNull(object) && Boolean.TRUE.equals(object.get("ok"))){
            JSONObject object1 = (JSONObject) object.get("data");
            System.out.println(object1.get("orderNo"));
        }
        System.out.println(object);
    }
}
