import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 私桩数据集
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/17 10:41
 */
public class PrivatePileData implements Serializable {

    List<PrivatePile> privatePileData= new ArrayList();

    public List<PrivatePile> getPrivatePileData() {
        return privatePileData;
    }

    public void setPrivatePileData(List<PrivatePile> privatePileData) {
        this.privatePileData = privatePileData;
    }
}
