package com.jin.learn.validate;

import com.cisco.webex.apis.common.ApiConstants;
import com.cisco.webex.apis.supports.jersey.validator.ApiValidatorFactoryProvider;
import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Locale;

//use the hibernate-validation
@Component
public class ApiHibernateValidatorFactoryProvider implements ApiValidatorFactoryProvider {
    private  static final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().messageInterpolator(new ApiHibernateValidatorFactoryProvider.ApiValidatorMessageInterpolator()).buildValidatorFactory();

    public ValidatorFactory getValidatorFactory() {
        return this.validatorFactory;
    }

    private static class ApiValidatorMessageInterpolator implements MessageInterpolator {
        private ApiValidatorMessageInterpolator() {
        }

        public String interpolate(String messageTemplate, Context context, Locale locale) {
            return messageTemplate;
        }

        public String interpolate(String messageTemplate, Context context) {
            return this.interpolate(messageTemplate, context, ApiConstants.LOCALE_DEFAULT);
        }
    }
}
