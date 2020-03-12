import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.utils.response.Resp;
import com.dousnl.utils.response.RespStatus;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        //String url = "http://localhost:9999/valid/push";
        String url = "http://10.64.19.85:7080/esb/comm/mq1";
        //String url = "http://10.16.17.140:9999/valid/push";
        long start=System.currentTimeMillis();
        //ResponseEntity<JSONObject> postForEntity = restTemplate.postForEntity(url, postData, JSONObject.class);
        //int statusCodeValue = postForEntity.getStatusCodeValue();
        long end=System.currentTimeMillis();


        JSONObject postData = new JSONObject();
        postData.put("repairOrderNo", "xxxxxx");//APP维修工单号
        postData.put("orderStatus", (byte) 0);//状态
        postData.put("repairNo", "IDMSWX310120191127000001");//维修单号
        postData.put("operator", "张三");//操作者
        postData.put("operatorMobile", "13112345678");//操作者联系电话
        System.out.println(JSON.toJSONString(postData));

        JSONObject postData1 = new JSONObject();
        postData1.put("repairOrderNo", "xxxxxx");//APP维修工单号
        postData1.put("orderStatus", (byte) 1);//状态
        postData1.put("repairNo", "IDMSWX310120191127000001");//维修单号
        postData1.put("realFinishTime", new Date());//操作者联系电话
        System.out.println(JSON.toJSONString(postData1));


        JSONObject postData2 = new JSONObject();
        postData2.put("repairOrderNo", "xxxxxx");//APP维修工单号
        postData2.put("orderStatus", (byte) 2);//状态
        postData2.put("repairNo", "IDMSWX310120191127000001");//维修单号
        postData2.put("realFinishTime", new Date());//操作者联系电话
        postData2.put("desc", "无法维修");//终止原因
        System.out.println(JSON.toJSONString(postData2));
        String error="维修终止，推送状态至ESB，异常....";

        ResponseEntity<JSONObject> postForEntity=null;
        try{
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            headers.add("requestId", UUID.randomUUID().toString());
            headers.add("sourceSystem", "AIEG");
            headers.add("serviceName", "S0000036B");
            HttpEntity entity = new HttpEntity(JSON.toJSONString(postData), headers);
            postForEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
            //ResponseEntity<JSONObject> postForEntity = restTemplate.postForEntity(url, postData, JSONObject.class);
            int statusCodeValue = postForEntity.getStatusCodeValue();
            if (HttpStatus.OK==postForEntity.getStatusCode()){
                HttpHeaders httpHeaders = postForEntity.getHeaders();
                List<String> statusFlag = httpHeaders.get("statusFlag");
                System.out.println(">>>>>>>>返回headers:"+JSON.toJSONString(statusFlag));
                JSONObject body = postForEntity.getBody();
                String esbCode = (String) body.get("esbCode");
                if ("000000".equals(esbCode)){
                    System.out.println(">>>>>>>>>返回body："+body.toString());
                    System.out.println("推送成功....!");
                }
            }
        }catch (Exception e){
            error += e.getClass().getName() + ":" + e.getMessage();
            System.out.println("error:"+error);
            e.printStackTrace();
            System.out.println(postForEntity==null?"":postForEntity.getStatusCodeValue());
        }
    }
}
