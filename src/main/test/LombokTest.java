import lombok.Data;
import lombok.Getter;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/1/3
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/1/3       hll    新增              1001
 ********************************************************************/
@Data
public class LombokTest {

    @Getter(lazy = true)
    private final int[] cached = expensive();

    private Integer id;

    private int[] expensive() {
        int[] result = new int[10];
        for (int i = 0; i < 10; i++) {
            result[i] = i;
            System.out.print(i);
        }
        System.out.println("cached 初始化完成！");
        return result;
    }

    public static void main(String[] args) {
        LombokTest obj = new LombokTest();
        obj.setId(1001);
        System.out.println("打印id：" + obj.getId());
        System.out.println("cached 还没有初始化哟。");
        // obj.getCached();
    }
}
