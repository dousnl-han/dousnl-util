package com.dousnl.vo.sparrow.menu.enums;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/8
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/8       hll    新增              1001
 ********************************************************************/
public enum ConcatenateTypeEnum {
    LEFT_CONCATENATE("左拼接", 1),
    RIGHT_CONCATENATE("右拼接", 2);

    private final String description;
    private final int code;

    ConcatenateTypeEnum(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    // 根据描述获取 code
    public static Integer getCodeByDescription(String description) {
        for (ConcatenateTypeEnum type : ConcatenateTypeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        return null;
    }
}
