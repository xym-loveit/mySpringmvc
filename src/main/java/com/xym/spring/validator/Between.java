package com.xym.spring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by xym on 2017/9/20.
 */
@Documented
@Constraint(
        validatedBy = {BetweenValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Between {

    String message() default "必须在{min}和{max}之间";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max();

    int min();

}
