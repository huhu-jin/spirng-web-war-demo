package com.jin.learn.validate;


import com.cisco.webex.apis.supports.jersey.validator.ApiValidatorFactoryProvider;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Aspect
@Configuration
public class ValidationAspect {
    private Validator validator;

    public ValidationAspect(ApiValidatorFactoryProvider apiValidatorFactoryProvider) {
        this.validator = apiValidatorFactoryProvider.getValidatorFactory().getValidator();
    }

    @Before("@annotation(javax.validation.Valid)")
    public void validate(JoinPoint joinPoint) {
        Map<String,Object> errorMessages = new HashMap<>(16);
        for(Object parameter : joinPoint.getArgs()) {
            Set<ConstraintViolation<Object>> violations = validator.validate(parameter);
            for(ConstraintViolation<Object> violation : violations) {
                errorMessages.put(((PathImpl)violation.getPropertyPath()).getLeafNode().getName(),violation.getMessage());
            }
        }
        if(errorMessages.size()>=1) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity(errorMessages)
                            .build());
        }
    }
}