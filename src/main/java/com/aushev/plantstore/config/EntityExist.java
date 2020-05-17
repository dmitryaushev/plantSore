package com.aushev.plantstore.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntityExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityExist {

    String entity();
    String message() default "This value already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
