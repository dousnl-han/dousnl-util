package com.dousnl.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.NotNull;
import java.util.Random;

/**
 * 维修订单终止参数
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/21 10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviceCanel {

    @ApiModelProperty(value = "订单编码")
    @JSONField(name="advice_no_id")
    private String adviceNo;

    @ApiModelProperty(value = "终止原因备注")
    private String breakRemark;
    private Boolean flag;


    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override public void run() {
                    AdviceCanel adviceCanel=new AdviceCanel();
                    adviceCanel.adviceNo="订单编码";
                    adviceCanel.breakRemark="终止原因备注";
                    String jsonString = JSON.toJSONString(adviceCanel);
                    System.out.println(jsonString);

                    AdviceCanel object = JSON.parseObject("{\"advice_no_id\":\"订单编码\",\"breakRemark\":\"\"}",AdviceCanel.class);
                    System.out.println(JSON.toJSONString(object));
                    getPrice(adviceCanel);

                    getOrder(adviceCanel);
                }
            }).start();
        }
    }

    private static void getOrder(AdviceCanel adviceCanel) {
        System.out.println(adviceCanel.getFlag());
    }

    private static void getPrice(AdviceCanel adviceCanel) {
        boolean flag = false;
        int i = RandomUtils.nextInt();
        System.out.println(i+" i%2=="+(i%2));
        if (i%2==0){
            flag = true;
        }
        adviceCanel.setFlag(flag);
    }
}
