import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.dousnl.utils.http.HttpClentUtils;
import com.dousnl.utils.http.Result;
import com.dousnl.utils.response.AdviceResponseTms;
import com.dousnl.utils.response.Resp;
import com.dousnl.utils.response.RespStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/8 15:17
 */
public class TimeTest {

    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        long b=1599446785642l;
        String format1 = format.format(new Date(b));//1568334093000
        System.out.println("format1:"+format1);
        long a=1568334093000l;
        String format2 = format.format(new Date(a));
        System.out.println("format2:"+format2);
        long c=1568334083000l;
        String format3 = format.format(new Date(c));
        System.out.println("format3:"+format3);
        long d=1568334073000l;
        String format4 = format.format(new Date(d));//1568334063000
        System.out.println("format4:"+format4);
        long e=1568334063000l;
        String format5 = format.format(new Date(e));//1568334063000
        System.out.println("format5:"+format5);
        int count=0;

        String s = DateUtil.dateToString(new Date(), "yyMMdd");

        System.out.println("s:"+s);

        String s1 = "IDMSWX-" + DateUtil.dateToString(new Date(), "yyMMdd")
                +OrderSuffixSeq.genSuffixSeq("-", 5, 2345);
        System.out.println("s1:"+s1);

        String s2 = "IDMSAZDD-" + DateUtil.dateToString(new Date(), "yyyy-MM-dd")
                + OrderSuffixSeq.genSuffixSeq("-", 6, 2345);
        System.out.println("s2:"+s2);
        String sa="2";
        if (!("1".equals(sa) || "2".equals(sa))){
            System.out.println("sfsfsfd");
        }
        String str="a";
        try {
            int batteryName = Integer.parseInt("1");
            str=String.valueOf(batteryName + 1);
        } catch (Exception ex) {

        }
        System.out.println("str:"+str);
        String[] split = "04".split(",");
        List<String> userTypes = Arrays.asList(split);
        User u1=new User();
        User u2=new User();
        //u1.setAddress1("dsdsad");
        u1.setName("ada111");
        System.out.println("u1.getName():"+u1.getName());
        System.out.println("06".contains("06"));
        int a1=4;
        System.out.println((byte) a1);
        Resp resp = new Resp();
        RespStatus respStatus=new RespStatus("1","失败");
        resp.setStatus(respStatus);
        System.out.println("resp:" + JSON.toJSONString(resp));
        Resp r = JSON.parseObject(JSON.toJSONString(resp), Resp.class);
        System.out.println("R:" + JSON.toJSONString(r));

        User u3=JSON.parseObject(JSON.toJSONString(u1), User.class);
        System.out.println("u3:" + JSON.toJSONString(u3));
        AdviceResponseTms tms=AdviceResponseTms.failed("失败");
        AdviceResponseTms r1 = JSON.parseObject(JSON.toJSONString(tms), AdviceResponseTms.class);
        System.out.println("R1:" + JSON.toJSONString(r1));
        //推送方法.....
        JSONObject postData = new JSONObject();
        RestTemplate restTemplate=new RestTemplate();
        postData.put("id", "");
        postData.put("orderStatus", 1);
        String url = "http://10.100.81.190:8888/tms-repair/api/privatePileMaintenanceWorkOrders/pushStatus";
        long start=System.currentTimeMillis();
        ResponseEntity<JSONObject> postForEntity = restTemplate.postForEntity(url, postData, JSONObject.class);
        int statusCodeValue = postForEntity.getStatusCodeValue();
        long end=System.currentTimeMillis();
        System.out.println("耗时间："+(end-start));
        JSONObject body = postForEntity.getBody();
        System.out.println(">>>>>>"+JSON.toJSONString(body));
        System.out.println(statusCodeValue);

        Map<String, String> headers=new HashMap<String, String>();
        Map<String, String> params=new HashMap<String, String>();
        params.put("id", "");
        params.put("orderStatus", "1");
        long start1=System.currentTimeMillis();
        Result post = HttpClentUtils.post(url, headers, params, "utf-8");
        long end1=System.currentTimeMillis();
        System.out.println("耗时间http："+(end1-start1));
        //System.out.println(">>>>>>"+JSON.toJSONString(post));

        String adviceUrl="http://10.100.81.163:9101/advice/addFromTMS";
        JSONObject postDataadviceUrl = new JSONObject();
        long startdviceUrl=System.currentTimeMillis();
        ResponseEntity<JSONObject> postForadviceUrl= restTemplate.postForEntity(adviceUrl, postDataadviceUrl, JSONObject.class);
        int statusCode = postForadviceUrl.getStatusCodeValue();
        long endadviceUrl=System.currentTimeMillis();
        System.out.println(">>>>>>adviceUrl:"+JSON.toJSONString(postForadviceUrl.getBody()));
        System.out.println("耗时间adviceUrl:"+(end-start));
    }

    public static class OrderSuffixSeq {

        /**
         * 咨询订单(报修)
         * separator分隔符，digit序列位数，seq序列
         */
        public static String genSuffixSeq(String separator, int digit, int seq) {
            StringBuffer digitStr = new StringBuffer();
            digitStr.append("%0" + digit).append("d");
            return String.format(separator + digitStr, seq);
        }
    }
}
