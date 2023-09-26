package com.dousnl.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.util.Lists;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            int finalI = i;
            new Thread(new Runnable() {
                @Override public void run() {
                    AdviceCanel adviceCanel=new AdviceCanel();
                    adviceCanel.adviceNo="订单编码";
                    adviceCanel.breakRemark="终止原因备注";
                    try {
                        Thread.sleep(200 * finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String jsonString = JSON.toJSONString(adviceCanel);
                    System.out.println(jsonString);

                    AdviceCanel object = JSON.parseObject("{\"advice_no_id\":\"订单编码\",\"breakRemark\":\"\"}",AdviceCanel.class);
                    System.out.println(JSON.toJSONString(object));
                    getPrice(adviceCanel);
                    getOrder(adviceCanel);
                }
            }).start();
        }
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        for (int i = 1; i <= integers.size(); i++) {
            System.out.println(integers.get(i-1));
        }

        ArrayList<User> objects = Lists.newArrayList();
        User u =new User();
        u.setAge(18);
        objects.add(u);
        List<User> authorBOS =
                objects.stream().filter(e -> "111".equals(e.getName())).collect(Collectors.toList());
        System.out.println(authorBOS);
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
