package com.dousnl.vo.sparrow.menu.vo;

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
public class DeleteConfig extends BackConfig {
    private String updateFields;
    private String conditions;

    private String templateAlias = "delete";
    private String name;

    private String tableName;
}
