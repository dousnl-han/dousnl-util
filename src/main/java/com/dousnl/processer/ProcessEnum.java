package com.dousnl.processer;


/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/2/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/2/2       hll    新增              1001
 ********************************************************************/
public enum ProcessEnum {
    book_365("book_365"),
    feifan("feifan"),
    lilei("lilei");

    private String desc;

    ProcessEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
