import com.alibaba.fastjson.JSONObject;
import com.dousnl.enums.Suggestion;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/25 20:44
 */
public class ShuSuTest {
    public static void main(String[] args) {

        System.out.println(Suggestion.PASS.name());
        System.out.println(Suggestion.PASS.toString());

        JSONObject jsonObject = JSONObject.parseObject("{\"code\":0,\"message\":\"ok\"}");

        System.out.println(jsonObject.get("code"));
    }
}
