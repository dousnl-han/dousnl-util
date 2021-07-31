import lombok.Data;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/1/7
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/1/7       hanliangliang     新增              1001
 ********************************************************************/
@Data
public class OpalModel {

    private String name;
    private OpalModelChild child;
}
@Data
 class OpalModelChild{
    private Integer add;
}
