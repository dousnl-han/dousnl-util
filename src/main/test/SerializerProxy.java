/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/29
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/29       hll    新增              1001
 ********************************************************************/
public class SerializerProxy {
    ISerializerProxy proxy = null;

    public SerializerProxy() throws Exception {
        this.proxy = SerializerManager.Create();
    }

    public String Serialize(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass().isPrimitive()) {
            return obj.toString();
        } else {
            return obj instanceof String ? obj.toString() : this.proxy.Serialize(obj);
        }
    }

    public Object Deserialize(String content, Class<?> type) {
        if (content != null && content.length() != 0) {
            return type.getName().equalsIgnoreCase("java.lang.string") ? content : this.proxy.Deserialize(content, type);
        } else if (!type.isPrimitive()) {
            return type.getClass();
        } else {
            return type.getName().equalsIgnoreCase("java.lang.String") ? content : null;
        }
    }
}
