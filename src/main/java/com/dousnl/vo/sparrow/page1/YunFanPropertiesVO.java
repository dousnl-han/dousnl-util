package com.dousnl.vo.sparrow.page1;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/6
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/6       hll    新增              1001
 ********************************************************************/
@Data
public class YunFanPropertiesVO {

    @JSONField(name = "功能描述")
    private String tablePrimaryKey;

    @JSONField(name = "数据库")
    private String dataSource;

    @JSONField(name = "表")
    private String tablename;

    @JSONField(name = "表字段")
    private String tableFields;

}
