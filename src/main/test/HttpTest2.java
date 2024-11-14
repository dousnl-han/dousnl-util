import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
public class HttpTest2 {

    public static void main(String[] args) {
        String url = "http://127.0.0.1:9999/redis/setList";
        Map<String, String> paramHeader = Maps.newHashMap();
        paramHeader.put("X-DUSHU-APP-VER", "5.28.0");
        paramHeader.put("Cookie", "smidV2=202104141334225c0ed202d866d18c72ae127ff915a3da00c89cfed5b4828f0; locale=zh-CN; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221894e09cd9e1072-01a57dd034208d-26021f51-1327104-1894e09cd9f100c%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%221894e09cd9e1072-01a57dd034208d-26021f51-1327104-1894e09cd9f100c%22%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfYW5vbnltb3VzX2lkIjoiMTg5NGUwOWNkOWUxMDcyLTAxYTU3ZGQwMzQyMDhkLTI2MDIxZjUxLTEzMjcxMDQtMTg5NGUwOWNkOWYxMDBjIiwiJGlkZW50aXR5X2Nvb2tpZV9pZCI6IjE4YTAyOThlMzA1ZTlhLTBjZDUzZWRiZDQ3NDk2OC0yNjAyMWY1MS0xMzI3MTA0LTE4YTAyOThlMzA2MTJhZCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%7D; cms_admin_infosoybean-cms.dushu365.com=eyJhbGciOiJIUzUxMiJ9.eyJ0b2tlbl91c2VySWQ6IjoiMjAwMDU0NjY0IiwidG9rZW5fcmFuZG9tSWQ6IjoiMjAwMDU0NjY0I18xNzI5NDc3MDM3OTMzIiwiaXNzIjoic295YmVhbiIsImlhdCI6MTcyOTQ3NzAzN30.mvR2hgQ8BpErH0dk_XqF_rkNskpOpMWzJqqn04kIAQMT3wjar45Ot49hPLPO2vo6zJyl_Zt2e-SyxNqHuHcKBA%7Chanliangliang%7C1669389181000%7Ctrue%7C7e0cec2159d314186c181dbd9abac0f1");
        paramHeader.put("Referer", "https://soybean-cms.dushu365.com/");
        paramHeader.put("Content-Type", "application/json");

        try {
            HttpResponse response = HttpRequest.get(url).headerMap(paramHeader, true).execute();
            String body = response.body();
            final int status = response.getStatus();
            System.out.println(status);
            System.out.println(JSONArray.parseArray(body).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
