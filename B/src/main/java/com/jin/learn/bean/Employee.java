package com.jin.learn.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Employee {
    private Logger logger = LoggerFactory.getLogger(Employee.class);

    public void doGet(){
        logger.debug("in the Employee");
        logger.info("in the Employee");
        logger.warn("in the Employee");
        logger.error("in the Employee");
    }

}
