package com.jin.learn.validate;

import com.cisco.webex.apis.common.exception.FrameworkException;
import com.cisco.webex.apis.core.annotation.ApiValidator;
import com.cisco.webex.apis.supports.jersey.validator.ApiValidatorUtils;
import com.sun.jersey.spi.container.JavaMethodInvoker;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// use this JavaMethodInvoker to replace the jersey JavaMethodInvoker
// validator every query request
public class ApiValidatorJavaMethodInvoker implements JavaMethodInvoker {
    private final ValidatorFactory validatorFactory;

    public ApiValidatorJavaMethodInvoker(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }
    private void handleViolations(Set<ConstraintViolation<Object>> violations) {
        if (!violations.isEmpty()) {
            Map<String,Object> errorMessages = new HashMap<>(16);
            for(ConstraintViolation<Object> violation : violations) {
                errorMessages.put(((PathImpl) violation.getPropertyPath()).getLeafNode().getName(), violation.getMessage());
            }
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                                .entity(errorMessages).build());
        }
    }

    public Object invoke(Method method, Object object, Object... parameters) throws InvocationTargetException, IllegalAccessException {
        Validator validator = this.validatorFactory.getValidator();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation ann : parameterAnnotations[i]) {
                Valid valid = AnnotationUtils.getAnnotation(ann, Valid.class);
                if (!Objects.isNull(valid)) {
                    handleViolations(validator.validate(parameters[i]));
                }
            }
        }
        return method.invoke(object, parameters);
    }
}