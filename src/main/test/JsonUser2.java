import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/9/29
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/9/29       hll    新增              1001
 ********************************************************************/
public class JsonUser2 {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static void main(String[] args) {
        JsonUser2 jsonUser2 = new JsonUser2();
        jsonUser2.setUserId("1");

        JsonUser1 jsonObject = JSONObject.parseObject(JSON.toJSONString(jsonUser2), JsonUser1.class);

        System.out.println(JSON.toJSONString(jsonObject));

        final long longValue = new BigDecimal(23000).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP).longValue();

        Long forward_income_amount = 0L;
        Long reverse_income_amount = 0L;
        for (int i = 0; i < 3; i++){
            forward_income_amount += 2354541L;
            reverse_income_amount += 5676571L;
        }
        Long netIncomeAmount = forward_income_amount - reverse_income_amount;
        System.out.println(netIncomeAmount);
        if (netIncomeAmount < 0){
            System.out.println(1);
        }
        
    }
}
