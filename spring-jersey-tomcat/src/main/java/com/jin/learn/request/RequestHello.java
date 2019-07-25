package com.jin.learn.request;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RequestHello {

    @NotEmpty(message = "name not emputy")
    private String name;

    @NotNull(message = "age not emputy")
    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
