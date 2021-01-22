//package com.dousnl.controller.fdds;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Maps;
//import com.soybean.http.HttpKit;
//import com.soybean.http.common.HttpResult;
//import com.soybean.orderweb.vo.KeepChargeVO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.DigestUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//import java.util.Objects;
//
///**
// * Description: TODO
// * Company    : 上海黄豆网络科技有限公司
// *
// * @author : hanliangliang
// * Date       : 2020/9/17
// * Modify     : 修改日期          修改人员        修改说明          JIRA编号
// * v1.0.0       2020/9/17       hanliangliang     新增              1001
// ********************************************************************/
//
//@RestController
//@RequestMapping("/keep")
//public class KeepChargeController {
//    private static Logger logger = LoggerFactory.getLogger(KeepChargeController.class);
//
//    @PostMapping("/charge")
//    public String keepCharge(@RequestBody KeepChargeVO vo) {
//        Map<String, Object> param = Maps.newLinkedHashMap();
//        param.put("membershipType", Objects.nonNull(vo.getMembershipType()) ? vo.getMembershipType() : "MONTHLY_CARD");
//        param.put("phone", vo.getPhone());
//        param.put("sourceCode", "fandengdushu");
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        int length = timestamp.length();
//        String stamp = Integer.valueOf(timestamp.substring(0, length - 3)).toString();
//        param.put("timestamp", stamp);
//        param.put("transactionId", Objects.nonNull(vo.getTransactionId()) ? vo.getTransactionId() : "1557969946090000000");
//        StringBuffer buffer = new StringBuffer();
//        for (String key : param.keySet()) {
//            buffer.append(key + "=" + param.get(key) + "&");
//        }
//        buffer.deleteCharAt(buffer.length() - 1);
//        buffer.append("QRLDA3ntsAUDkZFoR1JgQgtePv3XlGWJ");
//        System.out.println("buffer:" + buffer);
//        param.put("sign", md5(buffer.toString()));
//        logger.info("============keep--请求参数---param:{}", param);
//        HttpResult<String> httpResult = HttpKit.postJson("https://api.pre.gotokeep.com/kprime/v1/charge", param);
//        String result = httpResult.getResult();
//        logger.info("============keep--响应结果---responseBody:{}", result);
//        JSONObject parse = JSONObject.parseObject(result);
//        //save result
//        if (Boolean.TRUE.equals(parse.get("ok"))) {
//            logger.info("============keep--直充成功");
//            return result;
//        }
//        logger.info("============keep--直充失败");
//
//        return result;
//    }
//
//    public static String md5(String text) {
//        String encodeStr = DigestUtils.md5DigestAsHex(text.getBytes());
//        return encodeStr;
//    }
//}
