package com.kunning.SpringBoot.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {

    /**
     * ID
     */
    @Setter
    @Getter
    private int id;

    /**
     * 用户名
     */
    @Setter
    @Getter
    private String username;

    /**
     * 密码
     */
    @Setter
    @Getter
    private String password;

    /**
     * 昵称
     */
    @Setter
    @Getter
    private String nickname;

    /**
     * 邮箱
     */
    @Setter
    @Getter
    private String email;

}
