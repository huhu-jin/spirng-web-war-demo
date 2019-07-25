package com.jin.learn.service.impl;

import com.jin.learn.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String say() {
        return "hello world";
    }
}
