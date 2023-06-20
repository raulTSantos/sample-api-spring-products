package com.company.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistingUserValidator.class) 
public @interface NotExistingUser {
    String message() default "Este nombre de usuario ya existe.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}