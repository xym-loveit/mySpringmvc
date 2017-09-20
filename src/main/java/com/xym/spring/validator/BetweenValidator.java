package com.xym.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * desc
 * 自定义校验
 *
 * @author xym
 */
public class BetweenValidator implements ConstraintValidator<Between, Integer> {

    int min;
    int max;

    public void initialize(Between between) {
        min = between.min();
        max = between.max();
    }

    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null && value > min && value < max) {
            return true;
        }
        return false;
    }
}