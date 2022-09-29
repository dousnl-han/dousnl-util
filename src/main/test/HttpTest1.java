import com.alibaba.fastjson.JSON;
import com.dousnl.utils.http.HttpClentUtils;
import com.dousnl.utils.http.Result;
import com.google.common.collect.Maps;

import java.io.*;
import java.util.Map;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/12/10
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/12/10       hanliangliang     新增              1001
 ********************************************************************/
public class HttpTest1 {

    public static void main(String[] args) {
        String url = "https://gateway-api.dushu365.com/fandeng-orch/fanbuy/clockin/period";
        Map<String, String> param = Maps.newHashMap();
        param.put("rightsId", "100352");
        param.put("token", "202004179R9kcUvgcZinHV478Xr");
        Map<String, String> paramHeader = Maps.newHashMap();
        paramHeader.put("X-DUSHU-APP-VER", "5.28.0");

        try {
            Result post = HttpClentUtils.post(url, paramHeader, param, "utf-8");
            String body = post.getBody();
            int statusCode = post.getStatusCode();
            System.out.println(statusCode);
            System.out.println(JSON.toJSONString(body));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
