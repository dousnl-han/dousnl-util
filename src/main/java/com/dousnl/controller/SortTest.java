package com.dousnl.controller;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2023/8/21
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2023/8/21       hll    新增              1001
 ********************************************************************/
@Data
public class SortTest {

    @JSONField(name = "sk")
    private String sortKey;

    @JSONField(name = "sp")
    private Integer sortPriority;

    @JSONField(name = "skt")
    private Integer sortKeyType;

    @JSONField(name = "so")
    private Integer sortOrder;
}
