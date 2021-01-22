package com.dousnl.domain.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/1/14
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/1/14       hanliangliang     新增              1001
 ********************************************************************/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminBuilder {
    private String name;
    private Integer age;
    private String address;

    public InnerTestClass inner(){
        class InnerTestClass{

        }
        return new AdminBuilder.InnerTestClass();
    }

    private class InnerTestClass {
    }
}

