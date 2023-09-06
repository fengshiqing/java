package com.kunning.springbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.custom") // yml文件中可以配置此属性
public class CustomProperties {

    private String name = "fengshiqing";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
