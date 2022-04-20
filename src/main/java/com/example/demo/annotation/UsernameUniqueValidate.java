package com.example.demo.annotation;

import com.example.demo.validator.UsernameUniqueValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = UsernameUniqueValidation.class)
public @interface UsernameUniqueValidate {

    String message() default "{default.not.unique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Documented
    @interface List {
        UsernameUniqueValidate value();
    }
}
