package com.dousnl.domain.es;

import lombok.Data;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/12/25
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/12/25       hanliangliang     新增              1001
 ********************************************************************/
@Data
public class User {
    private String username;
    private Integer age;
    private String address;
}
