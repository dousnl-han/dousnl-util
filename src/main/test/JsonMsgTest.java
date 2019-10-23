import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.utils.response.Resp;
import com.dousnl.utils.response.RespStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/22 16:45
 */
public class JsonMsgTest {

    public static void main(String[] args) {
        Resp resp = new Resp();
        RespStatus respStatus=new RespStatus("1","失败");
        resp.setStatus(respStatus);
        System.out.println("resp:" + JSON.toJSONString(resp));
        Resp r = JSON.parseObject(JSON.toJSONString(resp), Resp.class);
        System.out.println("R:" + JSON.toJSONString(r));

        RestTemplate restTemplate=new RestTemplate();
        JSONObject postData = new JSONObject();
        String url = "http://10.16.17.65:8083/api/privatePileMaintenanceWorkOrders/pushStatus";
        long start=System.currentTimeMillis();
        ResponseEntity<JSONObject> postForEntity = restTemplate.postForEntity(url, postData, JSONObject.class);
        int statusCodeValue = postForEntity.getStatusCodeValue();
        long end=System.currentTimeMillis();
    }
}
