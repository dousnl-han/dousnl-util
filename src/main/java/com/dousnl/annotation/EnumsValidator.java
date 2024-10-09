package com.dousnl.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/9/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/9/2       hll    新增              1001
 ********************************************************************/
public class EnumsValidator implements ConstraintValidator<Enums, Object> {

    private int [] integers;

    @Override public void initialize(Enums annotation) {
        integers = annotation.integers();
    }

    @Override public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o != null && o instanceof Integer) {
            for (Integer integer : integers) {
                if (integer.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }
}
