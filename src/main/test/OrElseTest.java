import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/12/18
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/12/18       hanliangliang     新增              1001
 ********************************************************************/
public class OrElseTest {

    public static void main(String[] args) {
        List<Integer> list=Arrays.asList(1,2,3);
        Integer integer = list.stream().filter(e -> Objects.equals(3, e)).findFirst().orElse(null);
        System.out.println(integer);
    }
}
