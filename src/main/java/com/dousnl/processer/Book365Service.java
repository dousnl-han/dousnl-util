package com.dousnl.processer;

import org.springframework.stereotype.Component;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/2/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/2/2       hll    新增              1001
 ********************************************************************/
@Component(value = Book365Service.name)
public class Book365Service {

    public static final String name = "book_365";
    private static final int base = 8 ^ 2 | 8 ^ 1;
    private static final int base1 = 1 << 6;

    public void test() {
        final String desc = ProcessEnum.book_365.toString();
        System.out.println(desc);
    }

}
