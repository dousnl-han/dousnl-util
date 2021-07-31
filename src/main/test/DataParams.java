import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/17 14:39
 */
public class DataParams<T> implements Serializable {
    private String tenantId = "0";
    private T data;

    public DataParams() {
    }

    public DataParams(String tenantId, T data) {
        this.tenantId = tenantId;
        this.data = data;
    }

    public static <T> DataParams create(String tenantId, T data) {
        DataParams<T> dataParams = new DataParams();
        dataParams.tenantId = tenantId;
        dataParams.data = data;
        return dataParams;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) throws ParseException {
        Date start = DateUtil.parse("2021-01-30 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(start.getTime());
        Date end = DateUtil.parse("2021-03-12 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(end.getTime());

        if ("acrossSkuPackage".contains("Package")){
            System.out.println("Package");
        }
        System.out.println(DateUtil.formatTime(1615344596246l,"yyyy-MM-dd HH:mm:ss"));
        List<User> advertImageBOS=Lists.newArrayList();
        List<User> collect = advertImageBOS.stream().filter(o -> o.getAge() > 1).collect(Collectors.toList());
        System.out.println(collect);
        List ids=Arrays.asList(1,2,4);
        System.out.println(JSON.toJSONString(ids));
    }
}
