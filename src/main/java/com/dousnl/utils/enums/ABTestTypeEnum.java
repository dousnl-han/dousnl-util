package com.dousnl.utils.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * Description: 首页改版——顶部分类切换
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/4/7
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/4/7       hanliangliang     新增              1001
 ********************************************************************/

@AllArgsConstructor
public enum ABTestTypeEnum {
    A("a",1),
    B("b",2);
    private String type;
    private Integer code;

    public String getType() {
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public static ABTestTypeEnum getCodeByType(String type){
        return Arrays.stream(ABTestTypeEnum.values()).filter(e->e.getType().equals(type))
                .findFirst().orElse(A);
    }

    public static void main(String[] args) {
        Integer a = ABTestTypeEnum.getCodeByType("b").getCode();
        System.out.println(a);
        ABTestTypeEnum typeEnum = ABTestTypeEnum.getCodeByType("a");
        ABTestTypeEnum code = ABTestTypeEnum.B;
        System.out.println(ABTestTypeEnum.B==ABTestTypeEnum.getCodeByType("a"));
    }
}
