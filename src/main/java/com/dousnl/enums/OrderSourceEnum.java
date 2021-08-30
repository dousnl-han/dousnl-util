package com.dousnl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/5/19
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/5/19       hanliangliang     新增              1001
 ********************************************************************/
@Getter
@AllArgsConstructor
public enum OrderSourceEnum {

    SOURCE_EVENT(1,"活动容器");

    private Integer code;
    private String name;
}

