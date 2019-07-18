package com.jin.learn.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Company {

    private Logger logger = LoggerFactory.getLogger(Company.class);

    public void doGet(){
        logger.debug("in the company ");
        logger.info("in the company ");
        logger.warn("in the company ");
        logger.error("in the company ");


    }


}
