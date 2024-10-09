package com.dousnl.annotation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/9/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/9/2       hll    新增              1001
 ********************************************************************/
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, CONSTRUCTOR})
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumsValidator.class)
public @interface Enums {

    String message() default "参数不合法";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    int [] integers() default {};

    String [] strings() default {};

}
