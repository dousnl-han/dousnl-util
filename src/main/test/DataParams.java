import java.io.Serializable;

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
}
