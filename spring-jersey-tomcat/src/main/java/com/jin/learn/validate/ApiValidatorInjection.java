package com.jin.learn.validate;

import com.cisco.webex.apis.supports.jersey.validator.ApiValidatorFactoryProvider;
import com.cisco.webex.apis.supports.jersey.validator.ApiValidatorUtils;
import com.sun.jersey.spi.container.JavaMethodInvokerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;

@Component
public class ApiValidatorInjection implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(com.sun.jersey.spi.container.ApiValidatorInjection.class);
    private ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        if (ApiValidatorUtils.isIncludeValidator()) {
            ApiValidatorFactoryProvider apiValidatorFactoryProvider = this.applicationContext.getBean(ApiValidatorFactoryProvider.class);
            ValidatorFactory validatorFactory = apiValidatorFactoryProvider.getValidatorFactory();
            try {
                Field field = JavaMethodInvokerFactory.class.getDeclaredField("defaultInstance");
                field.setAccessible(true);
                field.set(null, new ApiValidatorJavaMethodInvoker(validatorFactory));
            } catch (SecurityException var4) {
                this.logger.error("Init validator factory error.", var4);
            } catch (NoSuchFieldException var5) {
                this.logger.error("Init validator factory error.", var5);
            } catch (IllegalArgumentException var6) {
                this.logger.error("Init validator factory error.", var6);
            } catch (IllegalAccessException var7) {
                this.logger.error("Init validator factory error.", var7);
            }
        }

    }
}