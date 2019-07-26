package com.jin.learn.request;


import com.jin.learn.validate.annotation.EmailValid;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RequestHello {

    @NotEmpty(message = "name not emputy")
    private String name;

    @NotNull(message = "age not emputy")
    private Integer age;

    @EmailValid
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
