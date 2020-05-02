package com.kunning.springbootstarter;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomStarterImpl implements CustomStarter {

    @Autowired
    private CustomProperties customProperties;

    @Override
    public void welcome() {
        System.out.println(customProperties.getName() + "【Hello Spring Boot Srarter】");
    }
}
