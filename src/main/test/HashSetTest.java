import com.alibaba.fastjson.JSON;
import com.dousnl.utils.date.DateUtil;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/1 9:51
 */
public class HashSetTest {

     static class ContractEntity{
        private Date beginDate;
        private Date endDate;

        public Date getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(Date beginDate) {
            this.beginDate = beginDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

         @Override
         public String toString() {
             return super.toString();
         }
     }

    public static void main(String[] args) {

        ContractEntity entity=new ContractEntity();
        entity.setBeginDate(new Date());
        entity.setEndDate(new Date());
        reSetEntityDate(entity);

        System.out.println(JSON.toJSONString(entity));

        List<Object> list =new ArrayList<>();
        System.out.println(CollectionUtils.isEmpty(list));
        System.out.println(JSON.toJSONString(list));

        BigDecimal divide = new BigDecimal(0).divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR);
        System.out.println(divide);
        System.out.println(BigDecimal.ZERO.setScale(2));

        Date end=new Date();

        Date date = com.dousnl.utils.soybean.DateUtil.plusMonths(end, 1);
        String pattern = "yyyy年MM月dd日";
        System.out.println(com.dousnl.utils.soybean.DateUtil.formatDate(end,pattern));
        System.out.println(com.dousnl.utils.soybean.DateUtil.formatDate(date));
    }

    private static void reSetEntityDate(ContractEntity entity) {
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatNew=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            entity.setBeginDate(formatNew.parse(format.format(entity.getBeginDate())+" 00:00:00"));
            entity.setEndDate(formatNew.parse(format.format(entity.getEndDate())+" 23:59:59"));
        }catch (Exception e){
            throw new RuntimeException("合同日期有误....");
        }
    }
}
