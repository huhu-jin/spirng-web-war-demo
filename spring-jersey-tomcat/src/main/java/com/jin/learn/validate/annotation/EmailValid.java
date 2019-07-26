//------------------------------------------------------------------------------
// Copyright (c) 2013 Cisco Systems, Inc. All rights reserved.
//------------------------------------------------------------------------------
package com.jin.learn.validate.annotation;


import com.jin.learn.validate.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented()
public @interface EmailValid {

    String message() default "{ email e asdfasd rror }";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
