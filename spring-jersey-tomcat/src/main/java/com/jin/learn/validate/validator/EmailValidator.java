package com.jin.learn.validate.validator;


import com.jin.learn.validate.annotation.EmailValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

    private String regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public boolean isValid(String value, ConstraintValidatorContext
            context) {
        if(value==null){return true;}
        if( value.matches(regexp)){
            return true;
        }
        return false;
    }


    @Override
    public void initialize(EmailValid constraintAnnotation) {

    }

}
